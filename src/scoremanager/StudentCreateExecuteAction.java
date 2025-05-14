package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // パラメータの取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String classNum = request.getParameter("class_num");

        // 学校コード取得
        School school = teacher.getSchool();

        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setSchool(school);
        student.setAttend(true);

        StudentDao dao = new StudentDao();

        // 学生番号の重複チェック
        Student existing = dao.findByNoSchool(school, no);
        if (existing != null) {
            // 重複していた場合、エラーと初期値をセットしてJSPにフォワード
            request.setAttribute("error", "学生番号が重複しています");

            // 選択肢データを再設定
            request.setAttribute("entYears", dao.selectEntYears());
            request.setAttribute("classList", dao.selectClassNums());

            return "../main/student_create.jsp";
        }

        // 保存実行
        System.out.print(existing);
        dao.save(student);

        return "../main/student_create_done.jsp";
    }
}
