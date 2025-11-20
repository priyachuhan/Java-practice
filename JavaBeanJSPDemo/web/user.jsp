<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.UserBean" %>
<jsp:useBean id="user" class="com.example.UserBean" scope="request" />
<jsp:setProperty name="user" property="*" />

<!DOCTYPE html>
<html>
<head>
    <title>JavaBean in JSP Example</title>
</head>
<body>
    <h2>Enter User Details</h2>
    <form method="post">
        Name: <input type="text" name="name"><br><br>
        Email: <input type="email" name="email"><br><br>
        <input type="submit" value="Submit">
    </form>

    <%
        if (request.getParameter("name") != null && request.getParameter("email") != null) {
    %>
        <h3>Entered Details:</h3>
        <p>Name: <jsp:getProperty name="user" property="name" /></p>
        <p>Email: <jsp:getProperty name="user" property="email" /></p>
    <%
        }
    %>
</body>
</html>
