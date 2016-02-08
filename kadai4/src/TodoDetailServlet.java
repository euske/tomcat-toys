import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import TodoDAO.*;

public class TodoDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
			 HttpServletResponse response)
        throws ServletException, IOException {
	int userId = -1;
	for (Cookie c : request.getCookies()) {
	    if (c.getName().equals("userId")) {
		userId = Integer.parseInt(c.getValue());
	    }
	}
	String todoId = request.getParameter("todo");
	if (0 < userId && todoId != null) {
	    System.out.println("detail: userId="+userId);
	    System.out.println("detail: todoId="+todoId);
	    try {
		Database db = new MySQLDatabase();
		try {
		    TodoEntry entry = db.getTodo1(userId, Integer.parseInt(todoId));
		    System.out.println("detail: todoText="+entry.getTodoText());
		    request.setAttribute("entry", entry);
		} finally {
		    db.close();
		}
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	} else {
	    request.setAttribute("entry", null);
	}
	ServletContext context = getServletContext();
	RequestDispatcher dispatcher = context.getRequestDispatcher("/detail.jsp");
	dispatcher.forward(request, response);
    }
}
