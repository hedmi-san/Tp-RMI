package simplesqltp;

import java.rmi.*;
import java.sql.*;
import java.util.List;

public interface Interface extends Remote{
    public List<String> getDataFromDataBase()throws RemoteException,ClassNotFoundException,SQLException;
    public boolean addDataToDatabase(String productName, double price, int quantity)throws RemoteException,ClassNotFoundException,SQLException;
    public boolean deleteDataFromDatabase(String productName)throws RemoteException,ClassNotFoundException,SQLException;
    public boolean updateDataInDataBase(String productName, double newPrice, int newQuantity)throws RemoteException,ClassNotFoundException,SQLException;
    public List<String> getSpecificDataFromDataBase(String productName)throws RemoteException,ClassNotFoundException,SQLException;
}
