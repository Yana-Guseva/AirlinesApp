<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 28.11.2015
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Flight Information</title>
</head>
<br>

<h1>Information about flghts</h1>

<table border="1">
    <%--<c:out value="${requestScope.size}"/>--%>
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
            <%--<td>${flight.teamId}</td>--%>
                <td><a href="/WorkerServlet">"${flight.teamId}">"${flight.teamId}"</a></td>
        </tr>
    </c:forEach>
</table>

<%--<form action="FlightServlet" method="POST">--%>
<%--<table>--%>
<%--<tr>--%>
<%--<td>Flight ID</td>--%>
<%--<td>type="text" name="flightId" value="${flight.flightId}"</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>City of departure</td>--%>
<%--<td>type="text" name="cityOfDepart" value="${flight.cityOfDepart}"</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>City of destination</td>--%>
<%--<td>type="text" name="cityOfDest" value="${flight.cityOfDest}"</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Date of flight</td>--%>
<%--<td>type="text" name="date" value="${flight.date}"</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Time in flight</td>--%>
<%--<td>type="text" name="time" value="${flight.time}"</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Team number</td>--%>
<%--<td>type="text" name="teamId" value="${flight.teamId}"</td>--%>
<%--</tr>--%>
<%--</table>--%>
</form>
</body>
</html>
