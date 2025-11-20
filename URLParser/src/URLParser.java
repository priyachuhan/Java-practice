import java.net.URL;
import java.net.MalformedURLException;

public class URLParser {
    public static void main(String[] args) {
        try {
            // Example URL - you can change this to test other URLs
            URL url = new URL("https://www.example.com:8080/path/to/resource?query=hello&lang=en");

            // Extract and print components of the URL
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + url.getQuery());
            System.out.println("File: " + url.getFile());

        } catch (MalformedURLException e) {
            System.out.println("The URL is malformed: " + e.getMessage());
        }
    }
}

