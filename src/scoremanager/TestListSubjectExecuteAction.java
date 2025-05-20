package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse res) throws Exception {

        // リクエストパラメータ取得
        String entYear = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");


        // 入力チェック
        if (entYear == null || classNum == null || subjectCd == null ||
            entYear.isEmpty() || classNum.isEmpty() || subjectCd.isEmpty()) {
            request.setAttribute("message", "すべての項目を選択してください。");
            return "../main/test_list.jsp";
        }

        // セッションから学校情報を取得
        Teacher Teacher = (Teacher) request.getSession().getAttribute("teacher");
        School School=Teacher.getSchool();


        // 成績データ取得
        TestDao testDao = new TestDao();
        List<Test> testList = testDao.searchByCondition(School.getCd(), Integer.parseInt(entYear), classNum, subjectCd);
        // 画面に渡すデータ設定
        request.setAttribute("testList", testList);
        request.setAttribute("entYear", entYear);
        request.setAttribute("classNum", classNum);
        request.setAttribute("subjectCd", subjectCd);
        System.out.println("z");

        // 成績一覧（科目）画面へ遷移
        return "../main/test_list_student.jsp";
    }
}
