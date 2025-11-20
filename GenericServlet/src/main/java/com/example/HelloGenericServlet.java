package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;

public class HelloGenericServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // PrintWriter to write response
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Hello from GenericServlet!</h2>");
        out.println("<p>This servlet does not depend on HTTP protocol directly.</p>");
        out.println("</body></html>");
    }
}
