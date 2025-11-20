import java.sql.*;

public class ScrollableResultSetDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/schooldb";
        String user = "root"; // your MySQL username
        String password = "asdf123!@/#&*()"; // your MySQL password

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, // enables scrolling
                        ResultSet.CONCUR_READ_ONLY          // read-only mode
                );
                ResultSet rs = stmt.executeQuery("SELECT id, name, age FROM students")
        ) {
            // Move to last row
            rs.last();
            System.out.println("Last Record: " + rs.getString("name"));

            // Move to first row
            rs.first();
            System.out.println("First Record: " + rs.getString("name"));

            // Move to second row using absolute
            if (rs.absolute(2)) {
                System.out.println("Second Record (absolute): " + rs.getString("name"));
            }

            // Move relative from current position (+1 row)
            if (rs.relative(1)) {
                System.out.println("Relative +1 (now at 3rd row): " + rs.getString("name"));
            }

            // Move backwards with previous()
            if (rs.previous()) {
                System.out.println("Previous (back to 2nd row): " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
