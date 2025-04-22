package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;

@WebServlet(urlPatterns = { "/scoremanager.main/studentlist3" })
public class StudentList2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			// フィルタ項目の取得
			String f1 = request.getParameter("f1"); // 入学年度
			String f2 = request.getParameter("f2"); // クラス
			String f3 = request.getParameter("f3"); // 在学中チェック

			// 条件処理
			Integer entYear = (f1 != null && !f1.equals("0") && !f1.isEmpty()) ? Integer.parseInt(f1) : null;
			String classNum = (f2 != null && !f2.equals("0") && !f2.isEmpty()) ? f2 : null;
			Boolean isAttend = (f3 != null && f3.equals("t")) ? true : null;

			StudentDAO dao = new StudentDAO();

			List<Student> list;

			// 条件がすべて未指定の場合、全件取得
			if (entYear == null && classNum == null && isAttend == null) {
				list = dao.selectAll();
			} else {
				list = dao.search(entYear, classNum, isAttend);
			}

			// 検索結果
			request.setAttribute("students", list);

			// セレクトボックス用データ
			request.setAttribute("ent_year_set", dao.selectEntYears());
			request.setAttribute("class_num_set", dao.selectClassNums());

			// 現在の検索条件
			request.setAttribute("f1", entYear);
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", isAttend != null ? "t" : null);

			// 画面遷移
			request.getRequestDispatcher("../common/studentlist.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
