import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TodoChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
			  HttpServletResponse response)
        throws ServletException, IOException {
	String todoText = request.getParameter("text");
	String todoId = request.getParameter("todo");
	int userId = -1;
	for (Cookie c : request.getCookies()) {
	    if (c.getName().equals("userId")) {
		userId = Integer.parseInt(c.getValue());
	    }
	}
	if (0 < userId && todoText != null && todoId != null) {
	    System.out.println("change: userId="+userId);
	    System.out.println("change: todoText="+todoText);
	    System.out.println("change: todoId="+todoId);
	    String url = "jdbc:sqlite:..\\webapps\\kadai4\\WEB-INF\\var\\todo.db";
	    try {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(url);
		try {
		    String sql1 = "UPDATE todo SET TodoText = ? WHERE TodoId = ? AND UserId = ?;";
		    PreparedStatement stmt1 = conn.prepareStatement(sql1);
		    stmt1.setString(1, todoText);
		    stmt1.setInt(2, Integer.parseInt(todoId));
		    stmt1.setInt(3, userId);
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
