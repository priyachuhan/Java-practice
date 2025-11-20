package gui;

import core.Steganography;
import core.CryptoUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class EncodePanel extends JPanel {
    private BufferedImage coverImage;
    private File coverFile;

    private JLabel imageLabel;
    private JTextArea messageArea;
    private JPasswordField passwordField;
    private JButton loadImageButton, encodeButton;

    public EncodePanel() {
        setLayout(new BorderLayout(10, 10));

        // Top: Image preview and load button
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        imageLabel = new JLabel("No image loaded", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 300));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        loadImageButton = new JButton("Load Image");
        loadImageButton.addActionListener(e -> loadImage());

        topPanel.add(imageLabel, BorderLayout.CENTER);
        topPanel.add(loadImageButton, BorderLayout.SOUTH);

        // Center: Message and password input
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        messageArea = new JTextArea("Enter secret message here...");
        JScrollPane scroll = new JScrollPane(messageArea);
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setToolTipText("Enter password to encrypt the message");

        centerPanel.add(scroll);
        centerPanel.add(passwordField);

        // Bottom: Encode button
        encodeButton = new JButton("Encode & Save");
        encodeButton.addActionListener(e -> encodeAndSave());

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(encodeButton, BorderLayout.SOUTH);
    }

    private void loadImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                coverFile = chooser.getSelectedFile();
                coverImage = ImageIO.read(coverFile);
                Image scaled = coverImage.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
                imageLabel.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to load image: " + ex.getMessage());
            }
        }
    }

    private void encodeAndSave() {
        if (coverImage == null) {
            JOptionPane.showMessageDialog(this, "Please load a PNG image first.");
            return;
        }
        String message = messageArea.getText();
        if (message == null || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a secret message.");
            return;
        }
        char[] pwd = passwordField.getPassword();
        if (pwd.length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter a password.");
            return;
        }

        try {
            byte[] encryptedMsg = CryptoUtils.encrypt(message, new String(pwd));
            BufferedImage encodedImage = Steganography.encode(coverImage, encryptedMsg);

            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int res = chooser.showSaveDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File saveFile = chooser.getSelectedFile();
                if (!saveFile.getName().toLowerCase().endsWith(".png")) {
                    saveFile = new File(saveFile.getAbsolutePath() + ".png");
                }
                ImageIO.write(encodedImage, "png", saveFile);
                JOptionPane.showMessageDialog(this, "Message encoded and saved successfully!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Encoding failed: " + ex.getMessage());
        }
    }
}

