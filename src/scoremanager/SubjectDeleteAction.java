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

		//sessionからteacher情報を取得
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		School school=teacher.getSchool();

		//値の取得
		String cd=request.getParameter("cd");
		SubjectDao dao=new SubjectDao();
		Subject subject=dao.get(cd, school);

		request.setAttribute("subject", subject);

		//subject_delete.jspに遷移
		return "../main/subject_delete.jsp";

	}
}
