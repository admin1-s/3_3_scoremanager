package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
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
        String f4 = req.getParameter("f4");

        // 入力チェック
        if (f4 == null || f4.isEmpty()) {
            req.setAttribute("message", "このフィールドを入力してください。");
            return "../main/test_list.jsp";
        }

        // 成績データ取得
        TestDao tDao = new TestDao();
        List<Test> tests = tDao.searchByStudentNo(f4, school.getCd());

        StudentDao stuDao=new StudentDao();
        Student student=stuDao.findByNo(f4);

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(school);


        // リクエストに結果を格納
        req.setAttribute("tests", tests);
        req.setAttribute("student", student);
        req.setAttribute("subject", subjectList);
        req.setAttribute("f4", f4);

        return "../main/test_list.jsp";
    }
}
