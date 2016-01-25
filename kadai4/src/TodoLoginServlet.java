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
	    Connection conn = Database.getConnection();
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
	} catch (SQLException e) {
	    System.out.println(e);
	}
	response.sendRedirect("index.jsp");
    }
}
