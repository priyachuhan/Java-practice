<%@ page import="com.example.StudentDao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
    <h2>Add Student</h2>
    <form method="post" action="add.jsp">
        Name: <input type="text" name="name" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Course: <input type="text" name="course" required><br><br>
        <input type="submit" value="Add">
    </form>

    <%
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String course = request.getParameter("course");

            out.println("Form Data: " + name + ", " + email + ", " + course);

            int result = StudentDao.save(name, email, course);
            if (result > 0) {
                response.sendRedirect("index.jsp");
            } else {
                out.println("<p style='color:red;'>❌ Failed to save student!</p>");
            }
        }
    %>
</body>
</html>
