import javax.swing.*;
    public class loginform {
        public static void main(String[] args) {
            JFrame f1 = new JFrame("Login Form");

            JLabel userLabel = new JLabel("Username:");
            JLabel passLabel = new JLabel("Password:");
            JTextField userTextField = new JTextField();
            JPasswordField passField = new JPasswordField();
            JButton loginButton = new JButton("Login");
            JButton resetButton = new JButton("Reset");

            userLabel.setBounds(50, 50, 100, 30);
            userTextField.setBounds(150, 50, 150, 30);
            passLabel.setBounds(50, 100, 100, 30);
            passField.setBounds(150, 100, 150, 30);
            loginButton.setBounds(50, 150, 100, 30);

            resetButton.setBounds(200, 150, 100, 30);

            f1.add(userLabel);
            f1.add(userTextField);
            f1.add(passLabel);
            f1.add(passField);
            f1.add(loginButton);
            f1.add(resetButton);

            f1.setSize(400, 300);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f1.setLayout(null);
            f1.setVisible(true);
        }
    }

