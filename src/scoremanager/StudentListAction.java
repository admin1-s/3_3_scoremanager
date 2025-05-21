package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        //未ログインの時
      	if (teacher == null){
      		System.out.println("teacher is null.");
      		return "../main/login-in.jsp";
      	}

        SchoolDao sdao = new SchoolDao();
        School school = new School();

        School s = sdao.get(teacher.getSchool_cd());
        school.setCd(s.getCd());
        school.setName(s.getName());

        teacher = new Teacher();
        teacher.setSchool(school);

        // ローカル変数の初期化
        String entYearStr = ""; // 入学年度（文字列）
        String classNum = "";   // クラス番号
        String isAttendStr = null; // 在学フラグ（リクエストの文字列）
        Integer entYear = null;    // 入学年度（nullまたは整数）
        Boolean isAttend = null;   // 在学フラグ（null:条件なし、true/false:条件あり）
        List<Student> students = null;

        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        isAttendStr = request.getParameter("f3");

        // 入学年度の変換
        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                entYear = null;
            }
        }

        // 在学フラグの変換
        if (isAttendStr != null && !isAttendStr.isEmpty()) {
            isAttend = "t".equals(isAttendStr);  // "t" → true、それ以外 → false
            request.setAttribute("f3", isAttendStr); // 選択状態を維持するためセット
        } else {
            isAttend = null; // 指定なしの場合
            request.setAttribute("f3", null);
        }

        // 入学年度のセレクト用リスト
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year + 1; i > year - 11; i--) {
            entYearSet.add(i);
        }

        // クラス番号一覧取得
        List<String> list = cNumDao.filter(teacher.getSchool());

        // 検索条件によって学生リスト取得
        if (entYear==null && !classNum.equals("0")) {
            students = sDao.search(entYear, classNum, isAttend);
        } else if ("0".equals("entYear") && classNum.equals("0")) {
            students = sDao.search(entYear, null, isAttend);
        } else if (entYear == null && (classNum == null || classNum.equals("0"))) {
            students = sDao.search(null, null, isAttend);
        } else{
        	request.setAttribute("errors", "クラスを指定する場合は入学年度を指定してください");
            students = sDao.search(null, null, isAttend);
        }

        // JSPに渡すデータをセット
        request.setAttribute("f1", entYear);          // 入学年度
        request.setAttribute("f2", classNum);         // クラス
        request.setAttribute("students", students);   // 検索結果
        request.setAttribute("class_num_set", list);  // クラス選択肢
        request.setAttribute("ent_year_set", entYearSet); // 入学年度選択肢

        // JSPへフォワード
        request.getRequestDispatcher("../main/studentlist.jsp").forward(request, response);

        return null; // forward なので null
    }
}
