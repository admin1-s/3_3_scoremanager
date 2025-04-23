package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        try {
            String id = request.getParameter("id");
            String password = request.getParameter("password");

            TeacherDao dao = new TeacherDao();
            Teacher teacher = dao.search(id, password); // ← ここでDB接続エラーが起こる可能性あり

            if (teacher != null) {
                session.setAttribute("teacher", teacher);
                return "../main/index.jsp";
            }

            return "../main/login-error.jsp"; // 認証失敗
        } catch (Exception e) {
            e.printStackTrace(); // ログに出す
            return "../main/error.jsp"; // ← ここでエラーページへ飛ばす
        }
    }
}
