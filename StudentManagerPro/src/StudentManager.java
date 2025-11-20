// File: StudentManager.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class StudentManager extends JFrame {
    Connection conn;
    DefaultTableModel model;
    JTable table;
    JTextField idField, nameField;

    public StudentManager() {
        setTitle("Student CRUD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        idField = new JTextField(); nameField = new JTextField();
        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");

        idField.setBounds(20, 20, 150, 25);
        nameField.setBounds(200, 20, 150, 25);
        addBtn.setBounds(20, 60, 150, 25);
        delBtn.setBounds(200, 60, 150, 25);

        model = new DefaultTableModel(new String[]{"ID", "Name"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 100, 330, 150);

        add(idField); add(nameField); add(addBtn); add(delBtn); add(scroll);

        connect();
        loadData();

        addBtn.addActionListener(e -> {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUES(?,?)");
                ps.setInt(1, Integer.parseInt(idField.getText()));
                ps.setString(2, nameField.getText());
                ps.executeUpdate();
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        delBtn.addActionListener(e -> {
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM student WHERE id=?");
                ps.setInt(1, Integer.parseInt(idField.getText()));
                ps.executeUpdate();
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        setVisible(true);
    }

    void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "asdf123!@/#&*()");
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void loadData() {
        try {
            model.setRowCount(0);
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM student");
            while (rs.next())
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2)});
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public static void main(String[] args) {
        new StudentManager();
    }
}
