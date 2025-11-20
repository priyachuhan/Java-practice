package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SetCookieServlet")
public class SetCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Create a cookie
        Cookie cookie = new Cookie("username", "PriyaChauhan");

        // Set the max age (in seconds)
        cookie.setMaxAge(60 * 60); // 1 hour

        // Add cookie to response
        response.addCookie(cookie);

        // Output
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Cookie 'username' set to: PriyaChauhan</h2>");
        out.println("<a href='GetCookieServlet'>Go to GetCookieServlet</a>");
    }
}
