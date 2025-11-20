import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class WebsiteReachabilityChecker {
    public static void main(String[] args) {
        // List of websites to check
        String[] websites = {
                "www.google.com",
                "www.facebook.com",
                "www.youtube.com",
                "www.github.com",
                "www.nonexistentwebsite.example"
        };

        System.out.println("Checking website reachability...\n");

        for (String website : websites) {
            try {
                InetAddress inet = InetAddress.getByName(website);

                // Timeout set to 3000 ms (3 seconds)
                boolean reachable = inet.isReachable(3000);

                if (reachable) {
                    System.out.println(website + " is reachable.");
                } else {
                    System.out.println(website + " is NOT reachable.");
                }
            } catch (UnknownHostException e) {
                System.out.println(website + " - Unknown host.");
            } catch (IOException e) {
                System.out.println(website + " - Error checking reachability: " + e.getMessage());
            }
        }
    }
}
