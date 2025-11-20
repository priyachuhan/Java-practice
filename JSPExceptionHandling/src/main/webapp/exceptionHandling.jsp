<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Exception Handling in JSP</title>
</head>
<body>
    <h2>Divide Two Numbers</h2>

    <form method="post">
        Number 1: <input type="text" name="num1" required><br><br>
        Number 2: <input type="text" name="num2" required><br><br>
        <input type="submit" value="Divide">
    </form>

    <%
        String n1 = request.getParameter("num1");
        String n2 = request.getParameter("num2");

        if (n1 != null && n2 != null) {
            try {
                int a = Integer.parseInt(n1.trim());
                int b = Integer.parseInt(n2.trim());
                int result = a / b;

                out.println("<h3>Result: " + a + " / " + b + " = " + result + "</h3>");
            } catch (ArithmeticException ae) {
                out.println("<p style='color:red;'>Error: Division by zero is not allowed.</p>");
            } catch (NumberFormatException ne) {
                out.println("<p style='color:red;'>Error: Please enter valid integers.</p>");
            } catch (Exception e) {
                out.println("<p style='color:red;'>Unexpected error occurred: " + e.getMessage() + "</p>");
            }
        }
    %>
</body>
</html>
