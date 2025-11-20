<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple Interest Calculator</title>
</head>
<body>
    <h2>Simple Interest Calculator</h2>

    <form method="post">
        Principal (P): <input type="number" step="any" name="principal" required><br><br>
        Time (T in years): <input type="number" step="any" name="time" required><br><br>
        Rate (R %): <input type="number" step="any" name="rate" required><br><br>
        <input type="submit" value="Calculate">
    </form>

    <%
        String pStr = request.getParameter("principal");
        String tStr = request.getParameter("time");
        String rStr = request.getParameter("rate");

        if (pStr != null && tStr != null && rStr != null &&
            !pStr.trim().isEmpty() && !tStr.trim().isEmpty() && !rStr.trim().isEmpty()) {
            try {
                double p = Double.parseDouble(pStr.trim());
                double t = Double.parseDouble(tStr.trim());
                double r = Double.parseDouble(rStr.trim());

                double si = (p * t * r) / 100;

                out.println("<h3>Simple Interest = " + si + "</h3>");
            } catch (NumberFormatException e) {
                out.println("<p style='color:red;'>Please enter valid numeric values only.</p>");
            }
        }
    %>
</body>
</html>
