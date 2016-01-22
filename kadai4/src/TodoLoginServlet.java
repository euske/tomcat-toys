import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TodoLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String url = "jdbc:sqlite:..\\webapps\\kadai4\\WEB-INF\\var\\todo.db";
	try {
	    Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection(url);
	    try {
		String sql1 = "SELECT UserId FROM user WHERE username = ?;";
		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, username);
		ResultSet rs1 = stmt1.executeQuery();
		if (rs1.next()) {
		    int userId = rs1.getInt(1);
		    System.out.println("login: userId="+userId);
		    Cookie c = new Cookie("userId", Integer.toString(userId));
		    response.addCookie(c);
		    response.sendRedirect("show.jsp");
		    return;
		}
	    } finally {
		conn.close();
	    }
	} catch (ClassNotFoundException e) {
	    System.out.println(e);
	} catch (SQLException e) {
	    System.out.println(e);
	}
	response.sendRedirect("index.jsp");
    }
}
