package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		School school=teacher.getSchool();

		String cd=request.getParameter("cd");
		SubjectDao dao=new SubjectDao();
		Subject subject=dao.get(cd, school);

		request.setAttribute("subject", subject);

		return "../main/subject_delete.jsp";

	}
}
