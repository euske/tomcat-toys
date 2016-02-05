<%@page import="java.sql.*" %>
<%@page import="TodoDAO.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>My Todo</title>
<body>
<h1>My Todo</h1>

<c:choose>
 <c:when test="${entries == null}">
  <p> <a href="index.jsp">Not logged in.</a>
 </c:when>

 <c:otherwise>
  <form method="POST" action="logout">
    <div align=right>
    <input type=submit value="Log out">
    </div>
  </form>
  <div>
    <table border class=listbox>
    <tr><th>TODO</th></tr>
    <c:forEach var="entry" items="${entries}">
      <tr><td class=itembox>
        <a href="detail?todo=${entry.todoId}">
        <c:out value="${entry.todoText}" />
        </a>
      </td></tr>
    </c:forEach>
    </table>
  </div>
  <form method="POST" action="add">
  <div class=addbox>
    <textarea name="text" cols="80" rows="4"></textarea><br>
    <input type=submit value="Add" />
  </div>
  </form>
 </c:otherwise>
</c:choose>

</body>
</html>
