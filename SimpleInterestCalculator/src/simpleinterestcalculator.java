 import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simpleinterestcalculator extends JFrame {

    // Swing components
    private JTextField principalField, rateField, timeField, resultField;
    private JButton calculateButton;

    public simpleinterestcalculator() {
        setTitle("Simple Interest Calculator");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Create labels and fields
        add(new JLabel("Principal:"));
        principalField = new JTextField();
        add(principalField);

        add(new JLabel("Rate (%):"));
        rateField = new JTextField();
        add(rateField);

        add(new JLabel("Time (years):"));
        timeField = new JTextField();
        add(timeField);

        add(new JLabel("Simple Interest:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        calculateButton = new JButton("Calculate");
        add(calculateButton);

        // Empty label for layout spacing
        add(new JLabel());

        // Button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double principal = Double.parseDouble(principalField.getText());
                    double rate = Double.parseDouble(rateField.getText());
                    double time = Double.parseDouble(timeField.getText());

                    double interest = (principal * rate * time) / 100;
                    resultField.setText(String.format("%.2f", interest));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new simpleinterestcalculator().setVisible(true);
        });
    }
}

