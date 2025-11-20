        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;

public class sumDifferenceCalculator extends JFrame {
    private JTextField textField1, textField2, textFieldResult;

    public sumDifferenceCalculator() {
        setTitle("Sum and Difference Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel label1 = new JLabel("Enter first number:");
        textField1 = new JTextField();

        JLabel label2 = new JLabel("Enter second number:");
        textField2 = new JTextField();

        JLabel labelResult = new JLabel("Result:");
        textFieldResult = new JTextField();
        textFieldResult.setEditable(false);

        add(label1);
        add(textField1);
        add(label2);
        add(textField2);
        add(labelResult);
        add(textFieldResult);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                calculateSum();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                calculateDifference();
            }
        });

        setVisible(true);
    }

    private void calculateSum() {
        try {
            int num1 = Integer.parseInt(textField1.getText());
            int num2 = Integer.parseInt(textField2.getText());
            textFieldResult.setText(String.valueOf(num1 + num2));
        } catch (NumberFormatException e) {
            textFieldResult.setText("Invalid input");
        }
    }

    private void calculateDifference() {
        try {
            int num1 = Integer.parseInt(textField1.getText());
            int num2 = Integer.parseInt(textField2.getText());
            textFieldResult.setText(String.valueOf(num1 - num2));
        } catch (NumberFormatException e) {
            textFieldResult.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        new sumDifferenceCalculator();
    }
}
