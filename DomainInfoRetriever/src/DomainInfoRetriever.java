import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DomainInfoRetriever {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a domain name (e.g., www.google.com): ");
        String domain = scanner.nextLine();

        try {
            InetAddress inetAddress = InetAddress.getByName(domain);

            System.out.println("Domain: " + domain);
            System.out.println("IP Address: " + inetAddress.getHostAddress());
            System.out.println("Hostname: " + inetAddress.getHostName());
        } catch (UnknownHostException e) {
            System.out.println("Unable to resolve domain: " + domain);
        }

        scanner.close();
    }
}

