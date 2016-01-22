<%@page import="java.sql.*" %>
<html>
<body>
<h1>My TODO</h1>

<%
   int userId = -1;
   for (Cookie c : request.getCookies()) {
     if (c.getName().equals("userId")) {
       userId = Integer.parseInt(c.getValue());
     }
   }
   if (0 < userId) {
        System.out.println("show: userId="+userId);
%>
<p>
UserId=<%= userId %>
<form method="POST" action="logout">
<input type=submit value="Log out">
</form>
<table border>
<tr><th>TODO</th></tr>
<%
	String url = "jdbc:sqlite:..\\webapps\\kadai4\\WEB-INF\\var\\todo.db";
	try {
	    Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection(url);
	    try {
		String sql1 = "SELECT TodoId,TodoText FROM todo WHERE UserId = ?;";
		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		stmt1.setInt(1, userId);
		ResultSet rs1 = stmt1.executeQuery();
		while (rs1.next()) {
		    int todoId = rs1.getInt(1);
		    String todoText = rs1.getString(2);
		    System.out.println("todo: todoId="+todoId);
		    System.out.println("todo: todoText="+todoText);
%>
<tr><td>
<a href="detail.jsp?todo=<%= todoId %>">
<%= todoText %>
</td></tr>
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
%></table>
<p>
<form method="POST" action="add">
<input type=input name="text" />
<input type=submit value="Add" />
</form>
<%
   } else {
%>
<p>Not logged in.
<%
   }
%>

</body>
</html>
