package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//sessionからteacher情報の取得
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("teacher");

    	//未ログインの時
    	if (teacher == null){
    		System.out.println("teacher is null.");
    	    return "../main/login-in.jsp";
    	}

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

    	//あ
    	request.setAttribute("classList", classList);
    	request.setAttribute("subjectList", subjectList);
    	request.setAttribute("yearList", yearList);

    	//test_list.jspへ遷移
    	return "../main/test_list.jsp";
    }
}
