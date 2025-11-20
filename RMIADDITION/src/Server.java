import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            AddService service = new AddServiceExec();

            // Create registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the service
            registry.rebind("AddService", service);

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

