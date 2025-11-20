package com.example.servlet;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schooldb", "root", "asdf123!@/#&*()"); // Update password if needed
    }
}

