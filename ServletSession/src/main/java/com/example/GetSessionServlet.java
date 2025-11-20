package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/GetSessionServlet")
public class GetSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Do not create if it doesn't exist

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session != null && session.getAttribute("username") != null) {
            out.println("<h2>Session attribute 'username' is: " + session.getAttribute("username") + "</h2>");
        } else {
            out.println("<h2>No session found or attribute 'username' not set.</h2>");
        }
        out.println("<a href='SetSessionServlet'>Go to SetSessionServlet</a>");
    }
}


