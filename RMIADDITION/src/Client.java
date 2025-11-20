import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Locate the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup the remote service
            AddService service = (AddService) registry.lookup("AddService");

            // Call remote method
            int result = service.add(5, 10);

            System.out.println("Sum is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

