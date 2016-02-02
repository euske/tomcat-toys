import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import TodoDAO.Database;

public class TodoChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	
	String todoText = request.getParameter("text");
	String todoId = request.getParameter("todo");
	int userId = -1;
	for (Cookie c : request.getCookies()) {
	    if (c.getName().equals("userId")) {
		userId = Integer.parseInt(c.getValue());
	    }
	}
	if (0 < userId && todoText != null && todoId != null) {
	    System.out.println("change: userId="+userId);
	    System.out.println("change: todoText="+todoText);
	    System.out.println("change: todoId="+todoId);
	    try {
		Database db = new Database();
		try {
		    db.changeTodo(userId, Integer.parseInt(todoId), todoText);
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
