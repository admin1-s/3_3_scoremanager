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
		String subjectCd=request.getParameter("subjectCd");
		int No=Integer.parseInt(request.getParameter("no"));

		StudentDao studentDao=new StudentDao();
		List<Student> studentList=studentDao.getStudentList(school, classNum, entYear);

		TestDao testDao=new TestDao();

		for (Student student : studentList){
			String paramName="score_" + student.getNo();
			String pointStr=request.getParameter(paramName);

			if (pointStr != null && !pointStr.isEmpty()){
				int point=Integer.parseInt(pointStr);

				Test test=new Test();
				test.setStudent(student);
				Subject sub=new Subject();
				sub.setCd(subjectCd);
				sub.setSchool(school);
				test.setClassNum(classNum);
				test.setSubject(sub);
				test.setPoint(point);
				test.setNo(No);

				testDao.save(test, sub);
			}
		}

		return "../main/test_regist_done.jsp";
	}

}
