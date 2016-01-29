<%@page import="java.sql.*" %>
<%@page import="TodoDAO.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    request.setAttribute("entries", db.getTodos(userId));
%>
<c:forEach var="entry" items="${entries}">
 <tr><td>
 <a href="detail.jsp?todo=${entry.todoId}">
  <c:out value="${entry.todoText}" />
 </a></td></tr>
</c:forEach>
<%
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
