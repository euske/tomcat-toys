<%@page import="java.sql.*" %>
<%@page import="TodoDAO.*" %>
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
	try {
	    Database db = new Database();
	    try {
		TodoEntryBean entry = db.getTodo1(userId, Integer.parseInt(todoId));
		System.out.println("detail: todoText="+entry.getTodoText());
		request.setAttribute("entry", entry);
%>
<form method="POST" action="change">
<input type=hidden name="todo" value="${entry.todoId}" />
<input name="text" value="${entry.todoText}" />
<input type=submit value="Change" />
</form>

<form method="POST" action="remove">
<input type=hidden name="todo" value="${entry.todoId}" />
<input type=submit value="Remove" />
</form>
<%
	    } finally {
		db.close();
	    }
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
