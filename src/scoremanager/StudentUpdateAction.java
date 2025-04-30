package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{


	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		String no=request.getParameter("no");
		StudentDao dao=new StudentDao();
		Student student=dao.findByNo(no);

		request.setAttribute("student", student);

		return "../main/student_update.jsp";

	}

}
