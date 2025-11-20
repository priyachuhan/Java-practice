
    import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

    public class FactorialServiceExec extends UnicastRemoteObject implements FactorialService {

        protected FactorialServiceExec() throws RemoteException {
            super();
        }

        @Override
        public long factorial(int number) throws RemoteException {
            if (number < 0) return -1;  // error handling
            long fact = 1;
            for (int i = 1; i <= number; i++) {
                fact *= i;
            }
            return fact;
        }
    }

