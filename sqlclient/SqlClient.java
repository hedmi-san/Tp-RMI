package sqlclient;

import java.net.MalformedURLException;
import java.rmi.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import simplesqltp.Interface;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SqlClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        Interface stub = (Interface) Naming.lookup("rmi://localhost:7700/manageData");
        JFrame f = new JFrame();
        f.setTitle("Base de Donnes");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l1 = new JLabel("Manage Data Base");
        l1.setBounds(80, 20, 140, 30);
        JButton read = new JButton("get data");
        read.setBounds(150, 50, 100, 30);
        JButton update = new JButton("update data");
        update.setBounds(150, 110, 130, 30);
        JButton search = new JButton("Search");
        search.setBounds(150, 150, 130, 30);
        f.add(l1);
        f.add(read);
        f.add(update);
        f.add(search);
        f.setLayout(null);
        read.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> database = null;
                try {
                    database = stub.getDataFromDataBase();
                } catch (RemoteException ex) {
                    Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (String item : database) {
                    System.out.println(item + "\n");
                }
                JOptionPane.showMessageDialog(null, "The data is : " + database);
            }
        });
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame f2 = new JFrame();
                f2.setTitle("Update");
                f2.setSize(400, 400);
                f2.setLocationRelativeTo(null);
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JTextField textFieldOne = new JTextField();
                textFieldOne.setBounds(150, 20, 100, 35);
                JLabel l1 = new JLabel("Product Name :");
                l1.setBounds(0, 20, 140, 30);
                JTextField textFieldTwo = new JTextField();
                textFieldTwo.setBounds(150, 70, 100, 35);
                JLabel l2 = new JLabel("price :");
                l2.setBounds(0, 70, 140, 30);
                JTextField textFieldThree = new JTextField();
                textFieldThree.setBounds(150, 120, 100, 35);
                JLabel l3 = new JLabel("Qantity:");
                l2.setBounds(0, 120, 140, 30);
                JButton ok = new JButton("ok");
                ok.setBounds(150, 170, 130, 30);
                f2.add(textFieldOne);
                f2.add(l1);
                f2.add(textFieldTwo);
                f2.add(l2);
                f2.add(textFieldThree);
                f2.add(l3);
                f2.add(ok);
                f2.setLayout(null);
                f2.setVisible(true);
                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String valueOne = textFieldOne.getText();
                        String valueTwo = textFieldTwo.getText();
                        String valueThree = textFieldThree.getText();
                        double price = Double.parseDouble(valueTwo);
                        int qt = Integer.parseInt(valueThree);
                        try {
                            boolean success = stub.addDataToDatabase(valueOne, price, qt);
                            String x;
                            if (success) {
                                x = "Product updated successfully.";
                            } else {
                                x = "Failed to update product.";
                            }
                            JOptionPane.showMessageDialog(null, x);
                        } catch (RemoteException ex) {
                            Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame f3 = new JFrame();
                f3.setTitle("Update");
                f3.setSize(400, 400);
                f3.setLocationRelativeTo(null);
                f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JTextField textFieldOne = new JTextField();
                textFieldOne.setBounds(150, 20, 100, 35);
                JLabel l1 = new JLabel("Product Name :");
                l1.setBounds(0, 20, 140, 30);
                JButton ok = new JButton("ok");
                ok.setBounds(150, 70, 130, 30);
                f3.add(textFieldOne);
                f3.add(l1);
                f3.add(ok);
                f3.setLayout(null);
                f3.setVisible(true);
                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        List<String> database = null;
                        String valueOne = textFieldOne.getText();
                        try {
                            database = stub.getSpecificDataFromDataBase(valueOne);
                        } catch (RemoteException ex) {
                            Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (String item : database) {
                            System.out.println(item + "\n");
                        }
                        JOptionPane.showMessageDialog(null, "The data is : " + database);
                    }
                });
            }
        });
        f.setVisible(true);
    }
}
