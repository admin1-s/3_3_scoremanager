package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		HttpSession session=request.getSession();
		Teacher teacher=(Teacher) session.getAttribute("teacher");

		//未ログインの時
		if (teacher == null){
			System.out.println("teacher is null.");
			return "main/login-in.jsp";
		}


		request.setAttribute("teacher", teacher);
		return "../main/subject_create.jsp";

	}

}
