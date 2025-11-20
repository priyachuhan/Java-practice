import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SwingCRUD extends JFrame {
    JTextField nameField, emailField;
    JTable table;
    DefaultTableModel model;
    Connection conn;

    public SwingCRUD() {
        setTitle("MySQL CRUD with Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Top panel for input
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        nameField = new JTextField();
        emailField = new JTextField();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Connect to DB
        connectToDB();
        loadTable();

        // Add button logic
        addBtn.addActionListener(e -> {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)");
                ps.setString(1, nameField.getText());
                ps.setString(2, emailField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User added!");
                loadTable();
                nameField.setText("");
                emailField.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Update button logic
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                    String name = nameField.getText();
                    String email = emailField.getText();

                    PreparedStatement ps = conn.prepareStatement("UPDATE users SET name=?, email=? WHERE id=?");
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setInt(3, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "User updated!");
                    loadTable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Delete button logic
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "User deleted!");
                    loadTable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Fill fields on row select
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                nameField.setText(model.getValueAt(row, 1).toString());
                emailField.setText(model.getValueAt(row, 2).toString());
            }
        });

        setVisible(true);
    }

    void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crud_db", "root", "asdf123!@/#&*()"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadTable() {
        try {
            model.setRowCount(0); // Clear table
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SwingCRUD();
    }
}
