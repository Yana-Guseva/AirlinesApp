<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 28.11.2015
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Information about team</title>
</head>
<h1>Information about team</h1>
<body>
<table border="1">
  <tr>
    <th>ID</th>
    <th>ID post</th>
    <th>Name</th>
    <th>Year</th>
    <th>Experience</th>
  </tr>
  <c:forEach items="${requestScope.allWorkers}" var="worker">
    <tr>
      <td>${worker.workerId}</td>
      <td>${worker.postId}</td>
      <td>${worker.name}</td>
      <td>${worker.year}</td>
      <td>${worker.experience}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
