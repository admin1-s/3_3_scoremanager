package scoremanager.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
//データベースから取得した情報をjspに渡す
@WebServlet(urlPatterns={"/scoremanager.main/studentInfo"})
public class StudentInfo extends HttpServlet {

    // 学生情報入力ページ表示用（GET）
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO dao = new StudentDAO();
        List<Student> students = new ArrayList<>();

        try {
            students = dao.selectAll();
            List<Integer> entYears = dao.selectEntYears();
            List<String> classList = dao.selectClassNums();
            request.setAttribute("entYears", entYears);
            request.setAttribute("classList", classList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("list", students);
        request.getRequestDispatcher("../common/studentAdd.jsp").forward(request, response);
    }
}

