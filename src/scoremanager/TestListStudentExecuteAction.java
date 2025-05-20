package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.TestDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	//sessionからteacher情報の取得
    	HttpSession session = req.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("teacher");
    	School school=teacher.getSchool();

        // 入力された学生番号を取得
        String studentNo = req.getParameter("student_no");

        // 入力チェック
        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("message", "このフィールドを入力してください。");
            return "../main/test_list.jsp";
        }

        // 成績データ取得
        TestDao tDao = new TestDao();
        List<Test> tests = tDao.searchByStudentNo(studentNo, school.getCd());

        StudentDao stuDao=new StudentDao();
        Student student=stuDao.findByNo(studentNo);

        // リクエストに結果を格納
        req.setAttribute("tests", tests);
        req.setAttribute("student", student);
        req.setAttribute("student_no", studentNo);

        return "../main/test_list_student.jsp";
    }
}
