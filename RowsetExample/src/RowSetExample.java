import javax.sql.RowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class RowSetExample{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/schooldb";
        String user = "root";
        String password = "asdf123!@/#&*()";

        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowSet = factory.createCachedRowSet();

            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(password);
            rowSet.setCommand("SELECT * FROM students");
            rowSet.execute();

            while (rowSet.next()) {
                System.out.println("ID: " + rowSet.getInt("id") +
                        ", Name: " + rowSet.getString("name") +
                        ", Age: " + rowSet.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
