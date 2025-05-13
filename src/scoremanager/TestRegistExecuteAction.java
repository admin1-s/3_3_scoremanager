package scoremanager;

import java.util.List;

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

		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		School school=teacher.getSchool();

		int entYear=Integer.parseInt(request.getParameter("entYear"));
		String classNum=request.getParameter("classNum");
		String subjectCd=request.getParameter("subject");
		int No=Integer.parseInt(request.getParameter("count"));

		StudentDao studentDao=new StudentDao();
		List<Student> studentList=studentDao.getStudentList(school, classNum, entYear);

		TestDao testDao=new TestDao();

		for (Student student : studentList){
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

			if (pointStr.isEmpty()){
				testDao.delete(test);
				continue;
			}

			int point=Integer.parseInt(pointStr);

			if (point<0 || 100<point){
				request.setAttribute("error", "0〜100の範囲で入力してください");
				request.setAttribute("studentList", studentList);
				request.setAttribute("entYear", entYear);
				request.setAttribute("classNum", classNum);
				request.setAttribute("subjectCd", subjectCd);
				//科目一覧
				SubjectDao subjectDao=new SubjectDao();
		        List<Subject> subjectList=subjectDao.filter(school);
        		request.setAttribute("subjectList", subjectList);
        		//科目名取得
        		Subject subject=subjectDao.get(subjectCd, school);
        		request.setAttribute("subjectName", subject.getName());


				return "TestRegist.action";
		    }

			test.setPoint(point);
			testDao.save(test);

		}

		return "../main/test_regist_done.jsp";
	}

}
