package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database — change these values to your setup
            String url = "jdbc:mysql://localhost:3306/studentdb";
            String user = "root";
            String password = "asdf123!@/#&*()";  // leave empty if no password

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to database!");
        } catch (Exception e) {
            System.out.println("❌ DB Connection Error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
