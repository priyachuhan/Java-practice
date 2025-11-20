import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class URLEncodeDecode {
    public static void main(String[] args) {
        try {
            // Original string
            String original = "Hello World! Welcome to Java Programming.";

            // Encode using UTF-8
            String encoded = URLEncoder.encode(original, "UTF-8");
            System.out.println("Encoded String: " + encoded);

            // Decode back to original
            String decoded = URLDecoder.decode(encoded, "UTF-8");
            System.out.println("Decoded String: " + decoded);

        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding error: " + e.getMessage());
        }
    }
}

