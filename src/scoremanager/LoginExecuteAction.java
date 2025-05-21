package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        try {
            String id = request.getParameter("id");
            String password = request.getParameter("password");

            TeacherDao dao = new TeacherDao();
            Teacher teacher = dao.search(id, password); // ← ここでDB接続エラーが起こる可能性あり

            if (teacher != null) {
                // 学校情報を取得して teacher にセット
                SchoolDao sdao = new SchoolDao();
                School school = sdao.get(teacher.getSchool_cd());
                teacher.setSchool(school);

                session.setAttribute("teacher", teacher);
                return "Menu.action";
            }


            return "../main/login-error.jsp"; // 認証失敗
        } catch (Exception e) {
            e.printStackTrace(); // ログに出す
            return "../main/error.jsp"; // ← ここでエラーページへ飛ばす
        }
    }
}
