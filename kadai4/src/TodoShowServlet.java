import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import TodoDAO.*;

@WebServlet("/show")
public class TodoShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
			 HttpServletResponse response)
        throws ServletException, IOException {
	int userId = -1;
	for (Cookie c : request.getCookies()) {
	    if (c.getName().equals("userId")) {
		userId = Integer.parseInt(c.getValue());
	    }
	}
	if (0 < userId) {
	    System.out.println("show: userId="+userId);
	    request.setAttribute("userId", userId);
	    try {
		Database db = new MySQLDatabase();
		try {
		    request.setAttribute("entries", db.getTodos(userId));
		} finally {
		    db.close();
		}
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	}
	ServletContext context = getServletContext();
	RequestDispatcher dispatcher = context.getRequestDispatcher("/show.jsp");
	dispatcher.forward(request, response);
    }
}
