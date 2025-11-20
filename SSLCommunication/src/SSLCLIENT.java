import javax.net.ssl.*;
import java.io.*;

public class SSLCLIENT{
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "serverkeystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 8443);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("Hello from SSL Client!\n");
        writer.flush();

        String response = reader.readLine();
        System.out.println("Server: " + response);

        writer.close();
        reader.close();
        socket.close();
    }
}

