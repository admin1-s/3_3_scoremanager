package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        //未ログインの時
      	if (teacher == null){
      		System.out.println("teacher is null.");
      		return "../main/login-in.jsp";
      	}

        // ローカル変数の初期化
        String entYearStr = ""; // 入学年度（文字列）
        String classNum = "";   // クラス番号
        String studentNo = "";
        String studentName = "";
        String subject = "";
        Integer entYear = null;    // 入学年度（nullまたは整数）
        List<Student> students = null;

        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        subject = request.getParameter("f3");
        studentNo = request.getParameter("f4");

        // 入学年度の変換
        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                entYear = null;
            }
        }

        // 入学年度のセレクト用リスト
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year + 1; i > year - 11; i--) {
            entYearSet.add(i);
        }

        // クラス番号一覧取得
        List<String> list = cNumDao.filter(teacher.getSchool());

        // 検索条件によって学生リスト取得
        if (entYear != null && !classNum.equals("0")) {
            students = sDao.search(entYear, classNum, subject);
        } else if (entYear != null && classNum.equals("0")) {
            students = sDao.search(entYear, null, subject);
        } else if (entYear == null && (classNum == null || classNum.equals("0"))) {
            students = sDao.search(null, null, subject);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度を指定してください");
            request.setAttribute("errors", errors);
            students = sDao.search(null, null, subject);
        }

        // JSPに渡すデータをセット
        request.setAttribute("f1", entYear);          // 入学年度
        request.setAttribute("f2", classNum);         // クラス
        request.setAttribute("students", students);   // 検索結果
        request.setAttribute("class_num_set", list);  // クラス選択肢
        request.setAttribute("ent_year_set", entYearSet); // 入学年度選択肢

        // JSPへフォワード
        request.getRequestDispatcher("../main/test_list.jsp").forward(request, response);

        return null; // forward なので null
    }
}
