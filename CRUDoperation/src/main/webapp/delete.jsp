<%@ page import="com.example.StudentDao" %>
<%
    String idStr = request.getParameter("id");
    if (idStr != null && !idStr.trim().isEmpty()) {
        try {
            int id = Integer.parseInt(idStr.trim());
            StudentDao.delete(id);
        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Invalid student ID.</p>");
            return; // stop processing
        }
    } else {
        out.println("<p style='color:red;'>Student ID is missing.</p>");
        return; // stop processing
    }
    response.sendRedirect("index.jsp");
%>
