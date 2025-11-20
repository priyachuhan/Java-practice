import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class browser extends JFrame {
    private JTextField urlField;
    private JTextArea displayArea;

    public browser() {
        setTitle("Socket Browser 🌐");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel nav = new JPanel(new BorderLayout(5, 5));
        urlField = new JTextField("https://example.com");
        JButton goButton = new JButton("Go");

        nav.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        nav.add(urlField, BorderLayout.CENTER);
        nav.add(goButton, BorderLayout.EAST);
        add(nav, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        goButton.addActionListener(e -> loadPage());
        urlField.addActionListener(e -> loadPage());

        setVisible(true);
    }

    private void loadPage() {
        String address = urlField.getText().trim();
        if (!address.startsWith("http://") && !address.startsWith("https://"))
            address = "https://" + address;

        try {
            URL url = new URL(address);
            String host = url.getHost();
            String path = url.getPath().isEmpty() ? "/" : url.getPath();
            boolean isSSL = url.getProtocol().equalsIgnoreCase("https");

            Socket socket;
            if (isSSL) {
                SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                socket = factory.createSocket(host, 443);
            } else {
                socket = new Socket(host, 80);
            }

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            out.println("GET " + path + " HTTP/1.1");
            out.println("Host: " + host);
            out.println("User-Agent: SimpleSocketBrowser/1.0");
            out.println("Connection: close");
            out.println();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder headers = new StringBuilder();
            StringBuilder body = new StringBuilder();
            String line;
            boolean isBody = false;
            String redirectLocation = null;

            while ((line = in.readLine()) != null) {
                if (line.isEmpty()) {
                    isBody = true;
                    continue;
                }
                if (!isBody) {
                    headers.append(line).append("\n");
                    if (line.toLowerCase().startsWith("location:")) {
                        redirectLocation = line.substring(9).trim();
                    }
                } else {
                    body.append(line).append("\n");
                }
            }

            in.close();
            out.close();
            socket.close();

            // Handle redirect
            if (redirectLocation != null && redirectLocation.startsWith("http")) {
                urlField.setText(redirectLocation);
                loadPage(); // one level redirect
                return;
            }

            displayArea.setText(body.toString());

        } catch (Exception e) {
            displayArea.setText("Error loading page:\n" + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(browser::new);
    }
}
