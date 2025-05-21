package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class MenuAction extends Action{
	@Override
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

    	//メニュー画面に遷移
        return "../main/index.jsp";
    }

}
