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
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

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

        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("yearList", yearList);

      //ここから下が検索後

        String entYearStr=request.getParameter("f1");
        String classNum=request.getParameter("f2");
        String subjectCd=request.getParameter("f3");
        String countStr=request.getParameter("f4");

        if (entYearStr != null && classNum != null && subjectCd != null && countStr != null &&
        	!entYearStr.isEmpty() && !classNum.isEmpty() && !subjectCd.isEmpty() && !countStr.isEmpty()){
        	try{
        		int entYear = Integer.parseInt(entYearStr);
        		int count = Integer.parseInt(countStr);

        		//学生一覧取得
        		StudentDao studentDao=new StudentDao();
        		List<Student> studentList=studentDao.getStudentList(school, classNum,entYear);

        		//成績取得
        		TestDao testDao=new TestDao();
        		Test testList=testDao.get(classNum, subjectCd, school);

        		//科目名取得
        		Subject subject=subjectDao.get(subjectCd, school);

        		//JSPに渡す
        		request.setAttribute("studentList", studentList);
        		request.setAttribute("testList", testList);
        		request.setAttribute("subjectName", subject.getName());
        		request.setAttribute("f1", entYear);
        		request.setAttribute("f2", classNum);
        		request.setAttribute("f3", subjectCd);
        		request.setAttribute("f4", count);
        	} catch (NumberFormatException e){
        		request.setAttribute("message", "検索条件が正しくありません");
        	}
        }else if (entYearStr == null || classNum == null || subjectCd == null || countStr == null){
        	//最初にtest_regist.jspに遷移したとき
        }else{
        	//入力のいずれかが未入力の場合（検索ボタンが押されたが未入力）
        	request.setAttribute("message", "入学年度とクラスと科目と回数を選択してください");
        }




        //test_regist.jspへ遷移
		return "../main/test_regist.jsp";

	}
}
