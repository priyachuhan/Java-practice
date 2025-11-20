import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpHeaderPrinter {
    public static void main(String[] args) {
        String urlString = "https://example.com"; // You can change this to any website

        try {
            // Create a URL and open connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method
            conn.setRequestMethod("GET");

            // Connect to the server
            conn.connect();

            // Print response code
            System.out.println("Response Code: " + conn.getResponseCode());

            // Get and print all header fields
            System.out.println("\n=== HTTP Headers ===");
            Map<String, List<String>> headers = conn.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String headerKey = entry.getKey();
                List<String> headerValues = entry.getValue();

                if (headerKey != null) {
                    System.out.println(headerKey + ": " + String.join(", ", headerValues));
                } else {
                    // This is the HTTP status line (e.g., HTTP/1.1 200 OK)
                    System.out.println(String.join(", ", headerValues));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

