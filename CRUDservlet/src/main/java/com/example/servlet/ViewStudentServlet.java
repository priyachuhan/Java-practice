package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ViewStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String[]> students = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                students.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        String.valueOf(rs.getInt("age"))
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("students", students);
        RequestDispatcher rd = request.getRequestDispatcher("students.jsp");
        rd.forward(request, response);
    }
}

