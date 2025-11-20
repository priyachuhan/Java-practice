import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        int port = 9876;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Server started on port " + port);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            // Wait for a message
            socket.receive(receivePacket);
            String encryptedMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String decryptedMsg = AESutil.decrypt(encryptedMsg);
            System.out.println("Received (decrypted): " + decryptedMsg);

            // Respond with an acknowledgement
            String ack = "ACK: " + decryptedMsg;
            String encryptedAck = AESutil.encrypt(ack);

            byte[] sendBuffer = encryptedAck.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer,
                    sendBuffer.length,
                    receivePacket.getAddress(),
                    receivePacket.getPort()
            );

            socket.send(sendPacket);
            System.out.println("Acknowledgement sent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
