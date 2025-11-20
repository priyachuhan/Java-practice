package com.example;

import java.sql.*;
import java.util.*;

public class StudentDao {

    // Save a new student
    public static int save(String name, String email, String course) {
        int status = 0;
        try {
            System.out.println("➡️ Trying to save: " + name + ", " + email + ", " + course);
            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("❌ Database connection is NULL");
                return 0;
            }

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students(name, email, course) VALUES (?, ?, ?)"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);

            status = ps.executeUpdate();
            System.out.println("✅ Insert status: " + status);

            con.close();
        } catch (Exception e) {
            System.out.println("❌ ERROR while inserting student:");
            e.printStackTrace();
        }
        return status;
    }

    // Get all students
    public static List<Map<String, String>> getAllStudents() {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                Map<String, String> student = new HashMap<>();
                student.put("id", String.valueOf(rs.getInt("id")));
                student.put("name", rs.getString("name"));
                student.put("email", rs.getString("email"));
                student.put("course", rs.getString("course"));
                list.add(student);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get a single student by ID
    public static Map<String, String> getStudentById(int id) {
        Map<String, String> student = new HashMap<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student.put("id", String.valueOf(rs.getInt("id")));
                student.put("name", rs.getString("name"));
                student.put("email", rs.getString("email"));
                student.put("course", rs.getString("course"));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    // Update an existing student
    public static int update(int id, String name, String email, String course) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE students SET name=?, email=?, course=? WHERE id=?"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setInt(4, id);

            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Delete a student by ID
    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?");
            ps.setInt(1, id);

            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
