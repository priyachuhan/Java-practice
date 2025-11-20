package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SetSessionServlet")
public class SetSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // Create or get existing session
        session.setAttribute("username", "PriyaChauhan");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Session attribute 'username' set to: " + session.getAttribute("username") + "</h2>");
        out.println("<a href='GetSessionServlet'>Go to GetSessionServlet</a>");
    }
}
