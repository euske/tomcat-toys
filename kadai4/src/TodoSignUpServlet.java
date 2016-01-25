import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import TodoDAO.Database;

public class TodoSignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {

	String username = request.getParameter("username");
	String password = request.getParameter("password");
	try {
	    Connection conn = Database.getConnection();
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
	} catch (SQLException e) {
	    System.out.println(e);
	}
	response.sendRedirect("index.jsp");
    }
}
