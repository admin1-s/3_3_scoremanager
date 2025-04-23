package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
		Teacher teacher = (Teacher) session.getAttribute("user");

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
		String isAttendStr = ""; // 在学フラグ
		int entYear = 0;         // 入学年度（整数）
		boolean isAttend = false; // 在学フラグ
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

		if (Objects.nonNull(entYearStr) && !entYearStr.isEmpty()) {
			entYear = Integer.parseInt(entYearStr);
		}

		// 入学年度のセレクト用リスト
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year + 1; i > year - 11; i--) {
			entYearSet.add(i);
		}

		// クラス番号一覧取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		// 在学フラグの確認
		if (isAttendStr != null) {
			isAttend = true;
			request.setAttribute("f3", isAttendStr);
		}

		// 条件によって学生リスト取得
		if (entYear != 0 && !classNum.equals("0")) {
			students = sDao.search(entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			students = sDao.search(entYear, null, isAttend);
		} else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
			students = sDao.search(null, null, isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度を指定してください");
			request.setAttribute("errors", errors);
			students = sDao.search(null, null, isAttend);
		}

		// JSPに渡すデータをセット
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);

		// JSPへフォワード
		request.getRequestDispatcher("../main/studentlist.jsp").forward(request, response);

		return null; // forwardしたので戻り値は不要
	}
}
