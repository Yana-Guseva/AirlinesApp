<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 28.11.2015
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customTag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Flight Information</title>
</head>
<h1>Information about flghts</h1>
<form action="FlightServlet" method="POST">
    <table>
        <tr>
            <td>Flight ID</td>
            <td><input type="text" name="flightId" value="${flight.flightId}"/></td>
        </tr>
        <tr>
            <td>City of departure</td>
            <td><input type="text" name="cityOfDepart" value="${flight.cityOfDeparture}"/></td>
        </tr>
        <tr>
            <td>City of destination</td>
            <td><input type="text" name="cityOfDest" value="${flight.cityOfDestination}"/></td>
        </tr>
        <tr>
            <td>Date of flight</td>
            <td><input type="text" name="date" value="<calendar:formatDate value="${flight.date}" pattern="yyyy-MM-dd"/>"/></td>
        </tr>
        <tr>
            <td>Time</td>
            <td><input type="text" name="time" value="<calendar:formatDate value="${flight.time}" pattern="HH:mm"/>"/></td>
        </tr>
        <tr>
            <td>Duration</td>
            <td><input type="text" name="duration" value="<calendar:formatDate value="${flight.duration}" pattern="HH:mm"/>"/></td>
        </tr>
        <tr>
            <td>Team number</td>
            <td><input type="text" name="teamId" value="${flight.teamId}"/></td>
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
<form action="login.html" method="POST">
    <input type="submit" value="Back">
</form>
<form action="LogoutServlet" method="POST">
    <input type="submit" value="Logout">
</form>
</html>
