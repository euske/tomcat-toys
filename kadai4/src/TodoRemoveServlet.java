import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TodoRemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	String todoId = request.getParameter("todo");
	int userId = -1;
	for (Cookie c : request.getCookies()) {
	    if (c.getName().equals("userId")) {
		userId = Integer.parseInt(c.getValue());
	    }
	}
	if (0 < userId && todoId != null) {
	    System.out.println("remove: userId="+userId);
	    System.out.println("remove: todoId="+todoId);
	    String url = "jdbc:sqlite:..\\webapps\\kadai4\\WEB-INF\\var\\todo.db";
	    try {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(url);
		try {
		    String sql1 = "DELETE FROM todo WHERE TodoId = ? AND UserId = ?;";
		    PreparedStatement stmt1 = conn.prepareStatement(sql1);
		    stmt1.setInt(1, Integer.parseInt(todoId));
		    stmt1.setInt(2, userId);
		    stmt1.executeUpdate();
		} finally {
		    conn.close();
		}
	    } catch (ClassNotFoundException e) {
		System.out.println(e);
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	}
	response.sendRedirect("show.jsp");
    }
}
