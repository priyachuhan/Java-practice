package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/GetCookieServlet")
public class GetCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String username = null;

        // Find the cookie with name 'username'
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    username = c.getValue();
                    break;
                }
            }
        }

        // Output
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (username != null) {
            out.println("<h2>Cookie 'username' found: " + username + "</h2>");
        } else {
            out.println("<h2>Cookie 'username' not found.</h2>");
        }

        out.println("<a href='SetCookieServlet'>Set Cookie Again</a>");
    }
}
