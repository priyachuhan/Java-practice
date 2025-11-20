 import java.net.URI;
import java.net.URISyntaxException;

public class URIDemo {
    public static void main(String[] args) {
        try {
            // Define base and relative URIs
            URI baseUri = new URI("https://example.com/folder/");
            URI relativeUri = new URI("subfolder/file.html");

            // Resolve the relative URI against the base
            URI resolvedUri = baseUri.resolve(relativeUri);

            // Display the result
            System.out.println("Base URI     : " + baseUri);
            System.out.println("Relative URI : " + relativeUri);
            System.out.println("Resolved URI : " + resolvedUri);
        } catch (URISyntaxException e) {
            System.out.println("Invalid URI: " + e.getMessage());
        }
    }
}


