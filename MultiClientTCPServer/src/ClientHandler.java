import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Set up input and output streams
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true
            );

            out.println("Connected to the server. Type 'bye' to exit.");

            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Client [" + clientSocket.getInetAddress() + "]: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("bye")) {
                    out.println("Goodbye!");
                    break;
                }

                // Echo the message back to the client
                out.println("Server received: " + clientMessage);
            }

        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
