<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Set Session</title>
</head>
<body>
    <h2>Set Session Example</h2>

    <form method="post">
        Enter Your Name: <input type="text" name="username" required>
        <input type="submit" value="Set Session">
    </form>

    <%
        String name = request.getParameter("username");

        if (name != null && !name.trim().isEmpty()) {
            session.setAttribute("user", name);
            out.println("<p>Session is set for user: <strong>" + name + "</strong></p>");
            out.println("<p><a href='getSession.jsp'>Go to getSession.jsp</a></p>");
        }
    %>
</body>
</html>
