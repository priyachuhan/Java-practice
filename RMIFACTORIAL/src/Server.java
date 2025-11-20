import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            FactorialService service = new FactorialServiceExec();

            // Start RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind service
            registry.rebind("FactorialService.java", service);

            System.out.println("Factorial RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

