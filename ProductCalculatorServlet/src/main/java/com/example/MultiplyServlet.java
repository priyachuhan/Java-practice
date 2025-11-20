package com.example;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


    public class MultiplyServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            try {
                int num1 = Integer.parseInt(request.getParameter("num1"));
                int num2 = Integer.parseInt(request.getParameter("num2"));
                int product = num1 * num2;

                out.println("<html><body>");
                out.println("<h2>Product: " + product + "</h2>");
                out.println("</body></html>");
            } catch (Exception e) {
                out.println("<html><body>");
                out.println("<p style='color:red;'>Invalid input. Please enter numbers.</p>");
                out.println("</body></html>");
            }
        }
    }


