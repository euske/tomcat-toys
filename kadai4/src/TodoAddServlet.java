import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import TodoDAO.Database;

public class TodoAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {

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
		Connection conn = Database.getConnection();
		try {
		    String sql1 = "SELECT max(TodoId)+1 FROM todo;";
		    PreparedStatement stmt1 = conn.prepareStatement(sql1);
		    ResultSet rs1 = stmt1.executeQuery();
		    rs1.next();
		    int todoId = rs1.getInt(1);
		    String sql2 = "INSERT INTO todo VALUES (?, ?, ?);";
		    PreparedStatement stmt2 = conn.prepareStatement(sql2);
		    stmt2.setInt(1, todoId);
		    stmt2.setInt(2, userId);
		    stmt2.setString(3, text);
		    stmt2.executeUpdate();
		} finally {
		    conn.close();
		}
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	}
	response.sendRedirect("show.jsp");
    }
}
