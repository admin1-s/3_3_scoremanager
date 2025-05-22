package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	//sessionからteacher情報の取得
    	HttpSession session = req.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("teacher");

        // 入力された学生番号を取得
        String f4 = req.getParameter("f4");

        // 入力チェック
        if (f4 == null || f4.isEmpty()) {
            req.setAttribute("message", "このフィールドを入力してください。");
            return "../main/test_list.jsp";
        }

        StudentDao stuDao=new StudentDao();
        Student student=stuDao.findByNo(f4);

        //存在しない学生番号が入力されたとき
        if (student == null) {
            req.setAttribute("mes", "学生が存在しませんでした");
            return "../main/test_list.jsp";
        }

        //成績データ取得
        TestListStudentDao tsDao=new TestListStudentDao();
        List<TestListStudent> list=tsDao.filter(student);

        //該当する学生の成績情報が登録されていない時
        if (list == null || list.isEmpty()) {
            req.setAttribute("mes", "成績情報が存在しませんでした");
            req.setAttribute("f4", f4);
            req.setAttribute("student", student);
            return "../main/test_list.jsp";
        }


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

    	req.setAttribute("classList", classList);
    	req.setAttribute("subjectList", subjectList);
    	req.setAttribute("yearList", yearList);


        // リクエストに結果を格納
        req.setAttribute("student", student);
        req.setAttribute("f4", f4);
        req.setAttribute("tstList", list);

        return "../main/test_list.jsp";
    }
}
