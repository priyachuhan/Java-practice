import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter server IP address (e.g., 127.0.0.1): ");
        String serverAddress = scanner.nextLine();

        System.out.print("Enter server port (e.g., 12345): ");
        int port = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        try (
                // Connect to the server
                Socket socket = new Socket(serverAddress, port);

                // Output stream to send data
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Input stream to receive data
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.print("Enter message to send: ");
            String message = scanner.nextLine();

            // Send message to the server
            out.println(message);

            // Read and print server response
            String response = in.readLine();
            System.out.println("Received from server: " + response);

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

        scanner.close();
    }
}

