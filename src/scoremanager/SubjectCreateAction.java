package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

    	//subject_create.jspに遷移
        return "../main/subject_create.jsp";
    }
}
