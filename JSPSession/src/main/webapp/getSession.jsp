<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Get Session</title>
</head>
<body>
    <h2>Access Session Example</h2>

    <%
        String username = (String) session.getAttribute("user");

        if (username != null) {
            out.println("<p>Welcome back, <strong>" + username + "</strong>!</p>");
        } else {
            out.println("<p>No session found. Please <a href='setSession.jsp'>set the session here</a>.</p>");
        }
    %>
</body>
</html>
