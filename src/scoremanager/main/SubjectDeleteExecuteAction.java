package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		request.setCharacterEncoding("UTF-8");

		String cd=request.getParameter("cd");
		String name=request.getParameter("name");

		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");

		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		SubjectDao dao=new SubjectDao();

		dao.delete(subject);

		return "../main/subject_delete_done.jsp";
	}

}
