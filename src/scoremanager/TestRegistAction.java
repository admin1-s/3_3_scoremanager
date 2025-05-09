package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{
		HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school=teacher.getSchool();

        ClassNumDao classDao=new ClassNumDao();
        List<ClassNum> classList=classDao.getClassNum(school);

        SubjectDao subjectDao=new SubjectDao();
        List<Subject> subjectList=subjectDao.filter(school);

        int currentYear=java.time.Year.now().getValue();
        int[] yearList=new int[10];
        for (int i=0 ; i<10 ; i++){
        	yearList[i]=currentYear-i;
        }

        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("yearList", yearList);

        String entYearStr=request.getParameter("entYear");
        String classNum=request.getParameter("classNum");
        String subjectCd=request.getParameter("subjectCd");
        String countStr=request.getParameter("count");

        if (entYearStr != null && classNum != null && subjectCd != null && countStr != null &&
        	!entYearStr.isEmpty() && !classNum.isEmpty() && !subjectCd.isEmpty() && !countStr.isEmpty()){
        	try{
        		int entYear = Integer.parseInt(entYearStr);
        		int count = Integer.parseInt(countStr);

        		//学生一覧取得
        		StudentDao studentDao=new StudentDao();
        		List<Student> studentList=studentDao.getStudentList(school, classNum,entYear);

        		//成績取得
        		TestDao testDao=new TestDao();
        		Test testList=testDao.get(classNum, subjectCd, school);

        		//科目名取得
        		Subject subject=subjectDao.get(subjectCd, school);

        		//JSPに渡す
        		request.setAttribute("studentList", studentList);
        		request.setAttribute("testList", testList);
        		request.setAttribute("subjectName", subject.getName());
        		request.setAttribute("selectedYear", entYear);
        		request.setAttribute("selectedClass", classNum);
        		request.setAttribute("selectedSubject", subjectCd);
        		request.setAttribute("selectedCount", count);
        	} catch (NumberFormatException e){
        		request.setAttribute("message", "検索条件が正しくありません");
        	}
        } else if (request.getParameter("entYear") != null){
        	//入力のいずれかが未入力の場合（検索ボタンが押されたが未入力）
        	request.setAttribute("message", "すべての項目を選択してください");
        }



		return "../main/test_regist.jsp";

	}
}
