import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class SSLServer {
    public static void main(String[] args) throws Exception {
        int port = 8443;

        // Load keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream("serverkeystore.jks"), "password".toCharArray());

        // Set up key manager
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keyStore, "password".toCharArray());

        // Init SSL Context
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(kmf.getKeyManagers(), null, null);

        // Create SSLServerSocket
        SSLServerSocketFactory ssf = context.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(port);

        System.out.println("SSL Server started at port " + port);

        SSLSocket socket = (SSLSocket) serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String message = reader.readLine();
        System.out.println("Client: " + message);

        writer.write("Hello from SSL Server!\n");
        writer.flush();

        writer.close();
        reader.close();
        socket.close();
        serverSocket.close();
    }
}

