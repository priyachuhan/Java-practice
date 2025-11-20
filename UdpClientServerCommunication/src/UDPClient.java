import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 9876;

        try (DatagramSocket socket = new DatagramSocket()) {
            String message = "Hello from client!";
            String encryptedMessage = AESutil.encrypt(message);

            byte[] sendBuffer = encryptedMessage.getBytes();
            InetAddress serverAddress = InetAddress.getByName(serverHost);

            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer,
                    sendBuffer.length,
                    serverAddress,
                    serverPort
            );

            socket.send(sendPacket);
            System.out.println("Encrypted message sent: " + encryptedMessage);

            // Wait for response
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            socket.receive(receivePacket);
            String encryptedAck = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String decryptedAck = AESutil.decrypt(encryptedAck);

            System.out.println("Acknowledgement from server (decrypted): " + decryptedAck);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

