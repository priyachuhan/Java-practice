import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AddServiceExec extends UnicastRemoteObject implements AddService {

    protected AddServiceExec() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}

