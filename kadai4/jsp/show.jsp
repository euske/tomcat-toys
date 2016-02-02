<%@page import="java.sql.*" %>
<%@page import="TodoDAO.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>My TODO</h1>

<c:choose>
 <c:when test="${entries == null}">
  <p>Not logged in.
 </c:when>

 <c:otherwise>
  <p>
  UserId=${userId}
  <form method="POST" action="logout">
  <input type=submit value="Log out">
  </form>
  <table border>
  <tr><th>TODO</th></tr>
  <c:forEach var="entry" items="${entries}">
   <tr><td>
   <a href="detail?todo=${entry.todoId}">
    <c:out value="${entry.todoText}" />
   </a></td></tr>
  </c:forEach>
  </table>
  <p>
  <form method="POST" action="add">
  <input type=input name="text" />
  <input type=submit value="Add" />
  </form>
 </c:otherwise>
</c:choose>

</body>
</html>
