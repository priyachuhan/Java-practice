import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentCRUDSwing extends JFrame {
    // DB info
    private static final String URL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";      // your username
    private static final String PASS = "asdf123!@/#&*()"; // your password

    // Swing components
    private JTextField txtId = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtAge = new JTextField();

    private JButton btnAdd = new JButton("Add");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnClear = new JButton("Clear");

    private JTable table;
    private DefaultTableModel tableModel;

    private Connection conn;

    public StudentCRUDSwing() {
        setTitle("Student CRUD with Swing");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Connect to DB
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Connection Failed: " + e.getMessage());
            System.exit(1);
        }

        // Input fields panel
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInput.add(new JLabel("ID:"));
        panelInput.add(txtId);
        panelInput.add(new JLabel("Name:"));
        panelInput.add(txtName);
        panelInput.add(new JLabel("Age:"));
        panelInput.add(txtAge);

        // Buttons panel
        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnClear);

        // Combine input and buttons in one top panel
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelInput, BorderLayout.CENTER);
        panelTop.add(panelButtons, BorderLayout.SOUTH);

        // Table and model
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        table = new JTable(tableModel);

        // Add components to frame
        add(panelTop, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load data initially
        loadTableData();

        // Button actions
        btnAdd.addActionListener(e -> insertStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());

        // JTable click to populate fields
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    txtAge.setText(tableModel.getValueAt(row, 2).toString());
                    txtId.setEditable(false);
                }
            }
        });
    }

    private void loadTableData() {
        tableModel.setRowCount(0); // clear existing rows
        String sql = "SELECT * FROM students";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load data: " + e.getMessage());
        }
    }

    private void insertStudent() {
        if (!validateFields(true)) return;

        String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.setString(2, txtName.getText());
            ps.setInt(3, Integer.parseInt(txtAge.getText()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student added successfully.");
                loadTableData();
                clearFields();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + e.getMessage());
        }
    }

    private void updateStudent() {
        if (!validateFields(false)) return;

        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txtName.getText());
            ps.setInt(2, Integer.parseInt(txtAge.getText()));
            ps.setInt(3, Integer.parseInt(txtId.getText()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully.");
                loadTableData();
                clearFields();
                txtId.setEditable(true);
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that ID.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Update failed: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        String idText = txtId.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a student to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this student?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idText));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully.");
                loadTableData();
                clearFields();
                txtId.setEditable(true);
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that ID.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtId.setEditable(true);
        table.clearSelection();
    }

    private boolean validateFields(boolean checkId) {
        if (checkId && txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID is required.");
            return false;
        }
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.");
            return false;
        }
        if (txtAge.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Age is required.");
            return false;
        }

        try {
            if (checkId) Integer.parseInt(txtId.getText());
            Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID and Age must be valid integers.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentCRUDSwing app = new StudentCRUDSwing();
            app.setVisible(true);
        });
    }
}
