<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<h3>Hi, Login successful.
</h3>
<br>
<%--<a href="CheckoutPage.jsp">Checkout Page</a>--%>

<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>