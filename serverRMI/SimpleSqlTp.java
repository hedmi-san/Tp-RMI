package simplesqltp;
import java.rmi.*;
import java.rmi.registry.*;

public class SimpleSqlTp {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(7700);
            Interface manageData = new implimentation();
            Naming.rebind("rmi://localhost:7700/manageData", manageData);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
