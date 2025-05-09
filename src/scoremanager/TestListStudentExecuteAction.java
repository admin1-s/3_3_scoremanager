package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


        String f1 = req.getParameter("f1");
        String f2 = req.getParameter("f2");
        String f3 = req.getParameter("f3");

        Integer entYear = null;
        if (f1 != null && !f1.equals("0") && !f1.isEmpty()) {
            entYear = Integer.parseInt(f1);
        }

        String classNum = null;
        if (f2 != null && !f2.equals("0") && !f2.isEmpty()) {
            classNum = f2;
        }

        Boolean isAttend = null;
        if (f3 != null && f3.equals("t")) {
            isAttend = true;
        }


        StudentDao sDao = new StudentDao();
        List<Student> students = sDao.search(entYear, classNum, isAttend);


        List<Integer> entYearSet = sDao.selectEntYears();
        List<String> classNumSet = sDao.selectClassNums();


        req.setAttribute("students", students);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);


        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttend != null ? "t" : null);

        return "../main/test_list_student.jsp";
    }
}
