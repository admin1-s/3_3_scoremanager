package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // フォームから受け取るパラメータ
        int entYear = Integer.parseInt(request.getParameter("entYear"));  // ★追加
        String no = request.getParameter("studentNo");                    // ★追加
        String classNum = request.getParameter("classNum");
        String name = request.getParameter("studentName");
        boolean isAttend = request.getParameter("isAttend") != null;     // チェックボックス用

        // セッションから学校情報を取得（使っていないが念のため）
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");

        // 学生オブジェクトにセット
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setClassNum(classNum);
        student.setName(name);
        student.setAttend(isAttend);                 // ★在学情報も更新
        student.setSchool(teacher.getSchool());

        // DAOを使って更新処理
        StudentDao dao = new StudentDao();
        dao.update(student);

        return "../main/studentUpdateDone.jsp";  // 更新完了ページに遷移
    }
}
