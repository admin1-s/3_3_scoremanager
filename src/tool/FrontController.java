package tool;

import java.io.IOException;

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
        try {
            String path = request.getServletPath().substring(1);
            System.out.println(path);
            String name = path.replace(".a", "A").replace('/', '.');

            System.out.println("★ servlet path ->"+request.getServletPath());
			System.out.println("★ class name ->"+name);

            Action action = (Action) Class.forName(name)
                .getDeclaredConstructor().newInstance();

            String view = action.execute(request, response);
            System.out.println(view);

            request.getRequestDispatcher(view).forward(request, response);

        } catch (Exception e) {
        	e.printStackTrace();
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
