<%@page import="java.sql.*" %>
<%@page import="TodoDAO.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Details</h1>

<p>
<a href="show">Back</a>

<c:choose>
 <c:when test="${entry == null}">
  <p>Not logged in.
 </c:when>
 
 <c:otherwise>
  <form method="POST" action="change">
  <input type=hidden name="todo" value="${entry.todoId}" />
  <textarea name="text"><c:out value="${entry.todoText}" /></textarea>
  <input type=submit value="Change" />
  </form>

  <form method="POST" action="remove">
  <input type=hidden name="todo" value="${entry.todoId}" />
  <input type=submit value="Remove" />
  </form>
 </c:otherwise>
</c:choose>

</body>
</html>
