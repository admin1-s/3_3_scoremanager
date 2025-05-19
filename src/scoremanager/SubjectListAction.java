package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		//sessionからteacher情報を取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher) session.getAttribute("teacher");

		//未ログインの時
		if (teacher == null){
			System.out.println("teacher is null.");
			return "../main/login-in.jsp";
		}

		//学校を取得
		School school=teacher.getSchool();

		SubjectDao dao=new SubjectDao();
		//対象の学校に対する科目一覧
		List<Subject> subjectlist=dao.filter(school);

		request.setAttribute("subjectList", subjectlist);

		//subject_list.jspへ遷移
		return "../main/subject_list.jsp";

	}
}

