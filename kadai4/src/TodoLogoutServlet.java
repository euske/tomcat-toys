import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import TodoDAO.*;

@WebServlet("/logout")
public class TodoLogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	
	Cookie c = new Cookie("userId", null);
	c.setMaxAge(0);
	response.addCookie(c);
	response.sendRedirect("index.jsp");
    }
}
