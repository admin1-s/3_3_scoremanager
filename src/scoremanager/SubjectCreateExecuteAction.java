package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		//sessionからteacher情報を取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher) session.getAttribute("teacher");
		School school=teacher.getSchool();

		//値の取得
		String cd=request.getParameter("cd");
		String name=request.getParameter("name");

		//インスタンス生成
		Subject subject=new Subject();
		SubjectDao dao=new SubjectDao();

		//3文字かチェック
		if (cd == null || cd.length() != 3){
			request.setAttribute("error", "科目コードは3文字で入力してください");
			return "SubjectCreate.action";
		}

		Subject existing=dao.get(cd, school);
		//既に科目コードが存在している場合
		if (existing.getCd() != null){
			request.setAttribute("error", "科目コードが重複しています");
			return "SubjectCreate.action";
		}

		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		//入力された科目情報の追加
		dao.save(subject);

		//subject_create_done.jspに遷移
		return "../main/subject_create_done.jsp";

	}

}
