import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import TodoDAO.*;

@WebServlet("/add")
public class TodoAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	
        request.setCharacterEncoding("utf-8");
	String text = request.getParameter("text");
	int userId = -1;
	for (Cookie c : request.getCookies()) {
	    if (c.getName().equals("userId")) {
		userId = Integer.parseInt(c.getValue());
	    }
	}
	if (0 < userId && text != null) {
	    System.out.println("add: userId="+userId);
	    System.out.println("add: text="+text);
	    try {
		Database db = new MySQLDatabase();
		try {
		    db.addTodo(userId, text);
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
