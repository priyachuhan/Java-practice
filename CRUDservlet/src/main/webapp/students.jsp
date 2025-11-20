<%@ page import="java.util.*" %>
<%
    List<String[]> students = (List<String[]>) request.getAttribute("students");
%>
<html>
<head>
    <title>All Students</title>
</head>
<body>
    <h2>Students</h2>
    <table border="1">
        <tr><th>ID</th><th>Name</th><th>Age</th><th>Actions</th></tr>
        <%
            for (String[] s : students) {
        %>
        <form action="UpdateStudentServlet" method="post">
            <tr>
                <td><input type="text" name="id" value="<%= s[0] %>" readonly></td>
                <td><input type="text" name="name" value="<%= s[1] %>"></td>
                <td><input type="text" name="age" value="<%= s[2] %>"></td>
                <td>
                    <input type="submit" value="Update">
                    <a href="DeleteStudentServlet?id=<%= s[0] %>">Delete</a>
                </td>
            </tr>
        </form>
        <%
            }
        %>
    </table>
    <br>
    <a href="index.html">Back to Add</a>
</body>
</html>
