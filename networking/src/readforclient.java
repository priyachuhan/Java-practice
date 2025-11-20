
import java.io.*;
import java.net.*;

public class readforclient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("example.com", 80);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }


        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
