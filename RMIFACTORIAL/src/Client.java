import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Get registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup remote object
            FactorialService service = (FactorialService) registry.lookup("FactorialService.java");

            // Input number
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a number to find factorial: ");
            int number = scanner.nextInt();

            // Call remote method
            long result = service.factorial(number);
            System.out.println("Factorial of " + number + " is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
