<%@page import="java.sql.*" %>
<%@page import="TodoDAO.Database" %>
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
    try {
	Database db = new Database();
	try {
	    ResultSet rs = db.getTodos(userId);
	    while (rs.next()) {
		int todoId = rs.getInt(1);
		String todoText = rs.getString(2);
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
	    db.close();
	}
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
