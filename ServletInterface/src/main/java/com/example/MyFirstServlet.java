package com.example;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


public class MyFirstServlet implements Servlet {
    private ServletConfig config;

    // Called only once when the servlet is first loaded
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        System.out.println("Servlet is initialized");
    }

    // Returns servlet config object
    public ServletConfig getServletConfig() {
        return config;
    }

    // The main method that handles client requests
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>Hello from Servlet Interface!</h1>");
        out.println("<p>This servlet was written by implementing the Servlet interface.</p>");
    }

    // Returns information about the servlet
    public String getServletInfo() {
        return "MyFirstServlet - Basic example using Servlet interface";
    }

    // Called only once when servlet is being destroyed
    public void destroy() {
        System.out.println("Servlet is destroyed");
    }
}
