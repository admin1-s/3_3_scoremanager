package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action{
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		//sessionからteacher情報の取得
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		School school=teacher.getSchool();

		//値の取得
		int entYear=Integer.parseInt(request.getParameter("entYear"));
		String classNum=request.getParameter("classNum");
		String subjectCd=request.getParameter("subject");
		int No=Integer.parseInt(request.getParameter("count"));

		//インスタンス化し、学生情報一覧を取得
		StudentDao studentDao=new StudentDao();
		List<Student> studentList=studentDao.getStudentList(school, classNum, entYear);

		Map<String, String> errorMap=new HashMap<>();
	    boolean hasError = false;

		TestDao testDao=new TestDao();

		for (Student student : studentList){
			//値の取得
			String paramName="score_" + student.getNo();
			String pointStr=request.getParameter(paramName);

			Test test=new Test();
			test.setStudent(student);
			Subject sub=new Subject();
			sub.setCd(subjectCd);
			sub.setSchool(school);
			test.setClassNum(classNum);
			test.setSubject(sub);
			test.setNo(No);

			//点数が未入力だった場合test情報を削除
			if (pointStr.isEmpty()){
				testDao.delete(test);
				continue;
			}

			int point=Integer.parseInt(pointStr);

			//点数が０未満100より大きい場合、エラーメッセージを渡す
			if (point<0 || 100<point){
				errorMap.put(student.getNo(), "0〜100の範囲で入力してください");
	            hasError = true;
	            continue;
		    }


			test.setPoint(point);
			testDao.save(test);

		}

			//点数が範囲外だった場合、該当箇所にエラーメッセージを飛ばす
			if (hasError){
				request.setAttribute("errorMap", errorMap);
				request.setAttribute("studentList", studentList);
				request.setAttribute("f1", entYear);
				request.setAttribute("f2", classNum);
				request.setAttribute("f3", subjectCd);
				//科目一覧
				SubjectDao subjectDao=new SubjectDao();
		        List<Subject> subjectList=subjectDao.filter(school);
        		request.setAttribute("subjectList", subjectList);
        		//科目名取得
        		Subject subject=subjectDao.get(subjectCd, school);
        		request.setAttribute("subjectName", subject.getName());
        		request.setAttribute("f4", No);

        		//TestRegistAction.javaに遷移
        		return "TestRegist.action";
			}

		//test_regist_done.jspに遷移
		return "../main/test_regist_done.jsp";
	}

}
