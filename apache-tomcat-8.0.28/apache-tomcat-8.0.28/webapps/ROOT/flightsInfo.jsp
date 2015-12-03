<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 28.11.2015
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Flight Information</title>
</head>
<h1>Information about flights</h1>

<table border="1">
    <%--<c:out value="${requestScope.date}"/>--%>
    <tr>
        <th>Flight ID</th>
        <th>City of departure</th>
        <th>City of destination</th>
        <th>Date of flight</th>
        <th>Time</th>
        <th>Flight duration</th>
        <th>Team number</th>
    </tr>
    <c:forEach items="${requestScope.allFlights}" var="flight">
        <tr>
            <td>${flight.flightId}</td>
            <td>${flight.cityOfDeparture}</td>
            <td>${flight.cityOfDestination}</td>
            <td>${flight.date}</td>
            <td>${flight.time}</td>
            <td>${flight.duration}</td>
            <td><a href="./WorkerServlet?teamId=${flight.teamId}">${flight.teamId}</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<table cellspacing="0" cellpadding="4">
    <tr>
        <td>
            <form action="login.html" method="POST">
                <input type="submit" value="Back">
            </form>
        </td>
        <td>
            <form action="LogoutServlet" method="POST">
                <input type="submit" value="Logout">
            </form>
        </td>
    </tr>
</table>
</html>
