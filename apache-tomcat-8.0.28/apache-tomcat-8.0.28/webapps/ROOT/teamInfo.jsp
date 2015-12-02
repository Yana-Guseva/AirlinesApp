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
<form action="WorkerServlet" method="POST">
    <table>
        <tr>
            <td>ID worker</td>
            <td><input type="text" name="workerId" value="${worker.workerId}"/></td>
        </tr>
        <tr>
            <td>Post</td>
            <td><input type="text" name="post" value="${worker.post}"/></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${worker.name}"/></td>
        </tr>
        <tr>
            <td>Year</td>
            <td><input type="text" name="year" value="${worker.year}"/></td>
        </tr>
        <tr>
            <td>Experience</td>
            <td><input type="text" name="experience" value="${worker.experience}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="action" value="Add"/>
                <input type="submit" name="action" value="Edit"/>
                <input type="submit" name="action" value="Search"/>
                <input type="submit" name="action" value="Delete"/>
            </td>
        </tr>
    </table>
</form>
<br>
<%--<c:out value="${requestScope.teamId}"/>--%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Post</th>
        <th>Name</th>
        <th>Year</th>
        <th>Experience</th>
    </tr>
    <c:forEach items="${requestScope.workersInTeam}" var="worker">
        <tr>
            <td>${worker.workerId}</td>
            <td>${worker.post}</td>
            <td>${worker.name}</td>
            <td>${worker.year}</td>
            <td>${worker.experience}</td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="LogoutServlet" method="POST">
    <input type="submit" value="Logout">
</form>
<form action="FlightServlet" method="POST">
    <input type="submit" value="Back">
</form>
</body>
</html>
