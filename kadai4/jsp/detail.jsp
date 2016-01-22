<%@page import="java.sql.*" %>
<html>
<body>
<h1>Details</h1>

<p>
<a href="show.jsp">Back</a>

<%
   int userId = -1;
   for (Cookie c : request.getCookies()) {
     if (c.getName().equals("userId")) {
       userId = Integer.parseInt(c.getValue());
     }
   }
   String todoId = request.getParameter("todo");
   if (0 < userId && todoId != null) {
        System.out.println("detail: userId="+userId);
        System.out.println("detail: todoId="+todoId);
	String url = "jdbc:sqlite:..\\webapps\\kadai4\\WEB-INF\\var\\todo.db";
	try {
	    Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection(url);
	    try {
		String sql1 = "SELECT TodoText FROM todo WHERE UserId = ? AND TodoId = ?;";
		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		stmt1.setInt(1, userId);
		stmt1.setInt(2, Integer.parseInt(todoId));
		ResultSet rs1 = stmt1.executeQuery();
		while (rs1.next()) {
		    String todoText = rs1.getString(1);
		    System.out.println("detail: todoText="+todoText);
%>
<form method="POST" action="change">
<input type=hidden name="todo" value="<%= todoId %>" />
<input name="text" value="<%= todoText %>" />
<input type=submit value="Change" />
</form>

<form method="POST" action="remove">
<input type=hidden name="todo" value="<%= todoId %>" />
<input type=submit value="Remove" />
</form>
<%
		}
	    } finally {
		conn.close();
	    }
	} catch (ClassNotFoundException e) {
	    System.out.println(e);
	} catch (SQLException e) {
	    System.out.println(e);
	}
   } else {
%>
<p>Not logged in.
<%
   }
%>

</body>
</html>
