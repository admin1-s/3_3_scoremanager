package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		request.setCharacterEncoding("UTF-8");

		String no=request.getParameter("cd");
		String name=request.getParameter("name");
		int ent_year=Integer.parseInt(request.getParameter("ent_year"));
		String class_num=request.getParameter("class_num");
		Boolean is_attend=Boolean.parseBoolean("is_attend");


		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");

		Student student=new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(ent_year);
		student.setClassNum(class_num);
		student.setAttend(is_attend);
		student.setSchool(teacher.getSchool());

		StudentDao dao=new StudentDao();

		dao.update(student);

		return "../main/student_update_done.jsp";
	}
}
