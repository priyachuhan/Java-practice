import javax.swing.*;

public class UserRegistrationForm  {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple UI Example");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // JLabel and JTextField
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 25);

        // JTextArea
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 60, 80, 25);
        JTextArea addressArea = new JTextArea();
        addressArea.setBounds(100, 60, 200, 50);

        // JRadioButton
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        male.setBounds(100, 120, 80, 25);
        female.setBounds(180, 120, 80, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        // JCheckBox
        JCheckBox agree = new JCheckBox("I agree");
        agree.setBounds(100, 150, 100, 25);

        // JComboBox
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setBounds(20, 180, 80, 25);
        JComboBox<String> countryBox = new JComboBox<>(new String[]{"Nepal", "India", "USA"});
        countryBox.setBounds(100, 180, 100, 25);

        // JButton
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(100, 220, 100, 30);

        // Add components
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(addressLabel);
        frame.add(addressArea);
        frame.add(male);
        frame.add(female);
        frame.add(agree);
        frame.add(countryLabel);
        frame.add(countryBox);
        frame.add(submitBtn);

        frame.setVisible(true);
    }
}
