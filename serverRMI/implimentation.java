package simplesqltp;

import java.sql.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class implimentation extends UnicastRemoteObject implements Interface {

    public implimentation() throws RemoteException {
        super();
    }

    @Override
    public List<String> getDataFromDataBase() throws ClassNotFoundException, SQLException {
    List<String> results = new ArrayList<>();
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?characterEncoding=latin1", "root", "");
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM product");
        while (rs.next()) {
            String resultString = rs.getInt("IdP") + " " + rs.getString("nomP") + " " + rs.getDouble("prixP") + " " + rs.getInt("quantity");
            results.add(resultString);
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return results;
}
public List<String> getSpecificDataFromDataBase(String productName) throws ClassNotFoundException, SQLException {
    List<String> results = new ArrayList<>();
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?characterEncoding=latin1", "root", "");
        String selectQuery = "SELECT * FROM product WHERE nomP = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectQuery)) {
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String resultString = rs.getInt("IdP") + " " + rs.getString("nomP") + " " + rs.getDouble("prixP") + " " + rs.getInt("quantity");
                results.add(resultString);
            }
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return results;
}


    public boolean addDataToDatabase(String productName, double price, int quantity) throws ClassNotFoundException, SQLException {
    boolean success = false;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?characterEncoding=latin1", "root", "")) {
            String insertQuery = "INSERT INTO product (nomP, prixP, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
                pstmt.setString(1, productName);
                pstmt.setDouble(2, price);
                pstmt.setInt(3, quantity);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return success;
}
    public boolean deleteDataFromDatabase(String productName) throws ClassNotFoundException, SQLException {
    boolean success = false;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?characterEncoding=latin1", "root", "")) {
            String deleteQuery = "DELETE FROM product WHERE nomP = ?";
            try (PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {
                pstmt.setString(1, productName);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return success;
}
    @Override
    public boolean updateDataInDataBase(String productName, double newPrice, int newQuantity) throws ClassNotFoundException, SQLException {
    boolean success = false;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?characterEncoding=latin1", "root", "")) {
            String updateQuery = "UPDATE product SET prixP = ?, quantity = ? WHERE nomP = ?";
            try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
                pstmt.setDouble(1, newPrice);
                pstmt.setInt(2, newQuantity);
                pstmt.setString(3, productName);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return success;
}
}
