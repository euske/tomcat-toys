import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import TodoDAO.Database;

public class TodoLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	try {
	    Database db = new Database();
	    try {
		int userId = db.login(username);
		System.out.println("login: userId="+userId);
		Cookie c = new Cookie("userId", Integer.toString(userId));
		response.addCookie(c);
		response.sendRedirect("show");
		return;
	    } finally {
		db.close();
	    }
	} catch (SQLException e) {
	    System.out.println(e);
	}
	response.sendRedirect("index.jsp");
    }
}
