import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TodoSignUpServlet extends HttpServlet {
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
		String sql1 = "SELECT max(UserId)+1 FROM user;";
		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		ResultSet rs1 = stmt1.executeQuery();
		rs1.next();
		int userId = rs1.getInt(1);
		String sql2 = "INSERT INTO user VALUES (?, ?)";
		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, userId);
		stmt2.setString(2, username);
		stmt2.execute();
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
