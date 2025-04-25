package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ログインチェック
        if (teacher == null) {
            request.setAttribute("message", "ログインしてください");
            request.getRequestDispatcher("../main/login-in.jsp").forward(request, response);
            return null;
        }

        // 教師の所属学校情報取得
        SchoolDao sdao = new SchoolDao();
        School school = sdao.get(teacher.getSchool_cd());

        // 選択肢の準備
        LocalDate todaysDate = LocalDate.now();
        int currentYear = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear + 1; i > currentYear - 11; i--) {
            entYearSet.add(i);
        }

        ClassNumDao classDao = new ClassNumDao();
        List<String> classNumSet = classDao.filter(school);

        SubjectDao subjectDao = new SubjectDao();
        List<String> subjectSet = subjectDao.getNamesBySchool(school.getCd()); // 学校に紐づく科目名の取得

        // パラメータ取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");
        String studentNo = request.getParameter("f4");

        // 成績取得処理
        List<Test> testList = new ArrayList<>();
        TestDao testDao = new TestDao();

        if (studentNo != null && !studentNo.isEmpty()) {
            // 学生番号による検索
            testList = testDao.searchByStudentNo(studentNo, school.getCd());
        } else if (entYearStr != null && !entYearStr.isEmpty() && classNum != null && !classNum.equals("0") && subjectCd != null && !subjectCd.equals("0")) {
            // 入学年度 + クラス + 科目による検索
            int entYear = Integer.parseInt(entYearStr);
            testList = testDao.searchByCondition(entYear, classNum, subjectCd, school.getCd());
        } else {
            request.setAttribute("message", "科目情報を選択又は学生情報を入力し検索ボタンをクリックしてください");
        }

        // 各種属性セット
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("subject_set", subjectSet);

        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", studentNo);

        request.setAttribute("test_list", testList);

        request.getRequestDispatcher("../main/testreference.jsp").forward(request, response);
        return null;
    }
}
