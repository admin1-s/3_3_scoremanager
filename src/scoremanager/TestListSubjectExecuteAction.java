package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse res) throws Exception {
    	//sessionからteacher情報の取得
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("teacher");

        // リクエストパラメータ取得
        String entYear = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        // セッションから学校情報を取得
        Teacher Teacher = (Teacher) request.getSession().getAttribute("teacher");
        School School=Teacher.getSchool();

      //科目名を取得
        SubjectDao subDao = new SubjectDao();
        Subject sub = subDao.get(subjectCd, School);

        // 成績データ取得
        TestListSubjectDao tsDao = new TestListSubjectDao();
        List<TestListSubject> tsList = tsDao.filter(
            Integer.parseInt(entYear), classNum, sub, School
        );

     // 成績がなければメッセージを設定
        if (tsList.isEmpty()) {
            request.setAttribute("message", "学生情報が存在しませんでした");
        }


        // 画面に渡すデータ設定
        request.setAttribute("tsList", tsList);
        request.setAttribute("entYear", entYear);
        request.setAttribute("classNum", classNum);
        request.setAttribute("sub", sub);

        //検索後のセレクトボックスに値を入れるための処理
        School school=teacher.getSchool();

    	ClassNumDao classDao=new ClassNumDao();
    	List<ClassNum> classList=classDao.getClassNum(school);

    	SubjectDao subjectDao=new SubjectDao();
    	List<Subject> subjectList=subjectDao.filter(school);

    	int currentYear=java.time.Year.now().getValue();
    	int[] yearList=new int[10];
    	for (int i=0 ; i<10 ; i++){
    	   yearList[i]=currentYear-i;
    	}

    	request.setAttribute("classList", classList);
    	request.setAttribute("subjectList", subjectList);
    	request.setAttribute("yearList", yearList);

    	// 入力チェック
        if (entYear == null || classNum == null || subjectCd == null ||
            entYear.isEmpty() || classNum.isEmpty() || subjectCd.isEmpty()) {
            request.setAttribute("error", "入学年度とクラスと科目を選択してください");

        	request.setAttribute("classList", classList);
        	request.setAttribute("subjectList", subjectList);
        	request.setAttribute("yearList", yearList);
            return "../main/test_list.jsp";
        }


        // 成績一覧（科目）画面へ遷移
        return "../main/test_list.jsp";
    }
}
