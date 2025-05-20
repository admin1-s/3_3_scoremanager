package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 入力された学生番号を取得
        String f4 = req.getParameter("f4");

        // 入力チェック
        if (f4 == null || f4.isEmpty()) {
            req.setAttribute("message", "このフィールドを入力してください。");
            return "../main/test_list.jsp";
        }

        StudentDao stuDao=new StudentDao();
        Student student=stuDao.findByNo(f4);

        //存在しない学生番号が入力されたとき
        if (student == null) {
            req.setAttribute("mes", "学生が存在しませんでした");
            return "../main/test_list.jsp";
        }

        //成績データ取得
        TestListStudentDao tsDao=new TestListStudentDao();
        List<TestListStudent> list=tsDao.filter(student);

        //該当する学生の成績情報が登録されていない時
        if (list == null || list.isEmpty()) {
            req.setAttribute("mes", "成績情報が存在しませんでした");
            req.setAttribute("f4", f4);
            req.setAttribute("student", student);
            return "../main/test_list.jsp";
        }





        // リクエストに結果を格納
        req.setAttribute("student", student);
        req.setAttribute("f4", f4);
        req.setAttribute("tstList", list);

        return "../main/test_list.jsp";
    }
}
