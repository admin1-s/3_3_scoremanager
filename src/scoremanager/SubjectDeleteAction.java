package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		String cd=request.getParameter("cd");
		SubjectDao dao=new SubjectDao();
		Subject subject=dao.findByCd(cd);

		request.setAttribute("subject", subject);

		return "../main/subject_delete.jsp";

	}
}
