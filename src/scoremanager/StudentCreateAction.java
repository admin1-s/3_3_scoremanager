package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        return "../main/student_create.jsp";
    }
}
