<%@page import="java.sql.*" %>
<%@page import="TodoDAO.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Todo Details</title>
<body>
<h1>Todo Details</h1>

<c:choose>
 <c:when test="${entry == null}">
  <p> <a href="index.jsp">Not logged in.</a>
 </c:when>
 
 <c:otherwise>
  <div>
   <a href="show">Back</a>
  </div>
  
  <form method="POST" action="change">
  <div class=addbox>
    <input type=hidden name="todo" value="${entry.todoId}" />
    <textarea name="text" cols="80" rows="4"><c:out value="${entry.todoText}" /></textarea><br>
    <input type=submit value="Change" />
  </div>
  </form>

  <form method="POST" action="remove">
  <div class=delbox>
    <input type=hidden name="todo" value="${entry.todoId}" />
    <input type=submit value="Remove" />
  </div>
  </form>
 </c:otherwise>
</c:choose>

</body>
</html>
