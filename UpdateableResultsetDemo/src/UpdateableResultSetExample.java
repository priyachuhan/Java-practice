 import java.sql.*;

public class UpdateableResultSetExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/schooldb";
        String user = "root"; // your MySQL username
        String password = "asdf123!@/#&*()"; // your MySQL password

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                );
                ResultSet rs = stmt.executeQuery("SELECT id, name, age FROM students")
        ) {
            if (rs.next()) {
                System.out.println("Before update:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));

                // Update age
                rs.updateInt("age", rs.getInt("age") + 1);
                rs.updateRow();

                System.out.println("\nAfter update:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
            } else {
                System.out.println("No records found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
