import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactorialService extends Remote {
    long factorial(int number) throws RemoteException;
}
