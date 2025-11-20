package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBUtil.getConnection()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));

            PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=?, age=? WHERE id=?");
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                response.sendRedirect("ViewStudentServlet");
            } else {
                response.getWriter().println("Update failed for ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
