import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import TodoDAO.Database;

public class TodoRemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	
	String todoId = request.getParameter("todo");
	int userId = -1;
	for (Cookie c : request.getCookies()) {
	    if (c.getName().equals("userId")) {
		userId = Integer.parseInt(c.getValue());
	    }
	}
	if (0 < userId && todoId != null) {
	    System.out.println("remove: userId="+userId);
	    System.out.println("remove: todoId="+todoId);
	    try {
		Database db = new Database();
		try {
		    db.removeTodo(userId, Integer.parseInt(todoId));
		} finally {
		    db.close();
		}
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	}
	response.sendRedirect("show");
    }
}
