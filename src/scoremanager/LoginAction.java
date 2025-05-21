package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action{
	@Override
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

    	//ログイン画面に遷移
        return "../main/login-in.jsp";
    }
}

