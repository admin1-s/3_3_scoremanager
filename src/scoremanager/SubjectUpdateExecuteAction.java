package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		request.setCharacterEncoding("UTF-8");

		String cd=request.getParameter("cd");
		String name=request.getParameter("name");

		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		School school=teacher.getSchool();

		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		SubjectDao dao=new SubjectDao();

		Subject existing=dao.get(cd, school);
		//科目コードがなくなっている場合
		if (existing.getCd() == null){
			request.setAttribute("error", "科目コードが存在していません");
			return "SubjectUpdate.action";
		}

		dao.save(subject);

		return "../main/subject_update_done.jsp";
	}

}
