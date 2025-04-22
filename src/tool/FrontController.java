package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {

    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String path = request.getServletPath().substring(1);

            String base="scoremanager.main.";

            String name =base + path.replace(".a", "A").replace('/', '.').replace(".action", "Action");

            System.out.println("★ servlet path ->"+request.getServletPath());
			System.out.println("★ class name ->"+name);

            Action action = (Action) Class.forName(name)
                .getDeclaredConstructor().newInstance();

          //遷移先URLを取得
			action.execute(request, response);

        } catch (Exception e) {
        	request.getRequestDispatcher("/main/error.jsp").forward(request, response);
			System.out.println(e);
        }


    }

    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        doPost(request, response);
    }
}
