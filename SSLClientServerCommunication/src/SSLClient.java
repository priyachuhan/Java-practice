import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class SSLClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8443;

        // Trust the server's certificate
        char[] password = "password123".toCharArray();
        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(new FileInputStream("keystore.jks"), password);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(trustStore);

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);

        SSLSocketFactory sf = context.getSocketFactory();
        SSLSocket socket = (SSLSocket) sf.createSocket(host, port);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("Hello from secure client!");
        String response = in.readLine();
        System.out.println("Server replied: " + response);

        socket.close();
    }
}
