<%@ page import="java.util.*, com.example.StudentDao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
</head>
<body>
    <h2>All Students</h2>

    <a href="add.jsp">Add New Student</a>
    <br><br>

    <%
        List<Map<String, String>> students = StudentDao.getAllStudents();
        if (students != null && !students.isEmpty()) {
    %>
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Actions</th>
            </tr>
            <%
                for (Map<String, String> s : students) {
            %>
                <tr>
                    <td><%= s.get("id") %></td>
                    <td><%= s.get("name") %></td>
                    <td><%= s.get("email") %></td>
                    <td><%= s.get("course") %></td>
                    <td>
                        <a href="edit.jsp?id=<%= s.get("id") %>">Edit</a> |
                        <a href="delete.jsp?id=<%= s.get("id") %>" onclick="return confirm('Are you sure?');">Delete</a>
                    </td>
                </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
            out.println("<p>No students found. Add one <a href='add.jsp'>here</a>.</p>");
        }
    %>
</body>
</html>
