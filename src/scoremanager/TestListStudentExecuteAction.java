package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 入力された学生番号を取得
        String studentNo = req.getParameter("student_no");

        // 入力チェック
        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("message", "このフィールドを入力してください。");
            return "../main/test_list.jsp";
        }

        // ログインユーザーの学校情報をセッションから取得
        School school = (School) req.getSession().getAttribute("user");

        // 成績データ取得
        TestDao tDao = new TestDao();
        List<Test> tests = tDao.searchByStudentNo(studentNo, school.getCd());

        // リクエストに結果を格納
        req.setAttribute("tests", tests);
        req.setAttribute("student_no", studentNo);

        return "../main/test_list_student.jsp";
    }
}
