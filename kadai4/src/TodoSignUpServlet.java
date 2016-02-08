import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import TodoDAO.*;

public class TodoSignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {

	String username = request.getParameter("username");
	String password = request.getParameter("password");
	try {
	    Database db = new MySQLDatabase();
	    try {
		db.signup(username);
	    } finally {
		db.close();
	    }
	} catch (SQLException e) {
	    System.out.println(e);
	}
	response.sendRedirect("index.jsp");
    }
}
