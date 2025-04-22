package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;

@WebServlet(urlPatterns={"/scoremanager.main/studentAdd"})
public class StudentAdd extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String no = request.getParameter("no");
            String name = request.getParameter("name");
            int entYear = Integer.parseInt(request.getParameter("entYear"));
            String classNum = request.getParameter("class_num");

            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(entYear);
            student.setClassNum(classNum);
            student.setAttend(true); // 登録時点では在学中とする

            StudentDAO dao = new StudentDAO();
            dao.insert(student); // DAOメソッド呼び出し

            // 成功後は成功画面へ遷移（リダイレクト）
            response.sendRedirect("../common/studentAddOut.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "登録に失敗しました。入力内容を確認してください。");
            doGet(request, response); // エラー時に再表示
        }
    }
}
