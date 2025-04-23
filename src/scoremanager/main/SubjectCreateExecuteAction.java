package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		HttpSession session=request.getSession();
		Teacher teacher=(Teacher) session.getAttribute("teacher");

		String cd=request.getParameter("cd");
		String name=request.getParameter("name");

		Subject subject=new Subject();
		subject.setCd(cd);
		System.out.println(cd);
		subject.setName(name);
		System.out.println(name);
		subject.setSchool(teacher.getSchool());
		System.out.println(teacher.getSchool());

		SubjectDao dao=new SubjectDao();
		dao.save(subject);

		return "../main/subject_create_done.jsp";

	}

}
