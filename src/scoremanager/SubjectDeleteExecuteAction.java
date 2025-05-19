package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{

		//値の取得
		String cd=request.getParameter("cd");
		String name=request.getParameter("name");

		//sessionからteacher情報を取得
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		School school=teacher.getSchool();

		SubjectDao sdao=new SubjectDao();
		Subject existing=sdao.get(cd, school);
		//既に削除されている場合
		if (existing.getCd() == null){
			return "../main/error1.jsp";
		}

		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		SubjectDao dao=new SubjectDao();

		//入力された科目情報の削除
		dao.delete(subject);

		//subject_delete_done.jspへ遷移
		return "../main/subject_delete_done.jsp";
	}

}
