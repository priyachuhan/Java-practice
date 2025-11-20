import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class browser extends JFrame {
    private JTextField urlField;
    private JTextArea sourceArea;
    private JButton loadButton, downloadButton;
    private JLabel infoLabel;

    public browser() {
        setTitle("Simple Java Browser");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        urlField = new JTextField("https://example.com", 50);
        loadButton = new JButton("Load Page");
        downloadButton = new JButton("Download File");
        sourceArea = new JTextArea();
        infoLabel = new JLabel("Enter URL and click Load Page");

        JPanel topPanel = new JPanel();
        topPanel.add(urlField);
        topPanel.add(loadButton);
        topPanel.add(downloadButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(sourceArea), BorderLayout.CENTER);
        add(infoLabel, BorderLayout.SOUTH);

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> loadPage()).start();
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> downloadFile()).start();
            }
        });
    }

    private void loadPage() {
        try {
            String urlString = urlField.getText().trim();
            sourceArea.setText("");
            infoLabel.setText("Loading...");

            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            String protocol = url.getProtocol();
            String host = url.getHost();
            int port = url.getPort();
            String file = url.getFile();

            infoLabel.setText("Protocol: " + protocol + ", Host: " + host +
                    ", Port: " + (port != -1 ? port : "default") + ", Path: " + file);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sourceArea.append(inputLine + "\n");
            }
            in.close();
        } catch (MalformedURLException e) {
            infoLabel.setText("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            infoLabel.setText("Error reading from URL: " + e.getMessage());
        }
    }

    private void downloadFile() {
        try {
            String urlString = urlField.getText().trim();
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            String fileName = new File(url.getPath()).getName();
            if (fileName.isEmpty()) fileName = "downloaded_file";

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(fileName));
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File saveFile = fileChooser.getSelectedFile();

                InputStream in = connection.getInputStream();
                FileOutputStream out = new FileOutputStream(saveFile);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                in.close();
                out.close();

                infoLabel.setText("Downloaded to: " + saveFile.getAbsolutePath());
            } else {
                infoLabel.setText("Download canceled.");
            }
        } catch (MalformedURLException e) {
            infoLabel.setText("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            infoLabel.setText("Download failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new browser().setVisible(true));
    }
}
