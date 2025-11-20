<%@ page import="com.example.StudentDao" %>
<%@ page import="java.util.Map" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Map<String, String> student = StudentDao.getStudentById(id);
%>
<!DOCTYPE html>
<html>
<head><title>Edit Student</title></head>
<body>
    <h2>Edit Student</h2>
    <form method="post">
        Name: <input type="text" name="name" value="<%= student.get("name") %>" required><br><br>
        Email: <input type="email" name="email" value="<%= student.get("email") %>" required><br><br>
        Course: <input type="text" name="course" value="<%= student.get("course") %>" required><br><br>
        <input type="submit" value="Update Student">
    </form>

    <%
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String course = request.getParameter("course");
            StudentDao.update(id, name, email, course);
            response.sendRedirect("index.jsp");
        }
    %>
</body>
</html>
