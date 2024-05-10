/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import uts.isd.model.*;

public class PaymentDAO {
    private Connection connect;
    private Statement statement;
    private PreparedStatement readStatement;
    //private PreparedStatement updateSt;
    //private PreparedStatement deleteSt;
    
    
    //private String readQuery = "SELECT * FROM Payments WHERE UserID=? AND PaymentID=?";
    private String readQuery = "SELECT PaymentID, PaymentMethod, ExpiryDate, PaymentCVC, PaymentCardNumber, UserID FROM Payment";
    //private String updateQuery = "UPDATE Payments SET PaymentMethod=?, PaymentDate=?, PaymentCardNumber=?, PaymentCVC=? WHERE PaymentId=?";
    //private String deleteQuery = "DELETE FROM Payments WHERE UserID=? AND PaymentID=?";
    
    public PaymentDAO(Connection connection) throws SQLException {
        this.connect = connection;
        connection.setAutoCommit(true);
        statement = connection.createStatement();
        readStatement = connection.prepareStatement(readQuery);
        //updateSt = connection.prepareStatement(updateQuery);
        //deleteSt = connection.prepareStatement(deleteQuery);
        
    }
    
    //Create Operation: Create a payment
    public void createPayment(String paymentMethod, Date expiryDate, int paymentCVC, int paymentCardNumber,  int userID) throws SQLException {
        //String columns = "INSERT INTO Payments(paymentMethod, paymentDate, paymentCardNumber, paymentCVC, UserID) VALUES (?,?,?,?,?)";
        //String values = "VALUES('" + paymentMethod + "','" + paymentDate + "','" + paymentCardNumber + "','" + paymentCVC + "','" + userID + "')";
        //statement.executeUpdate(columns + values);
        PreparedStatement createQuery = connect.prepareStatement("INSERT INTO payment(PaymentMethod, ExpiryDate, PaymentCVC, PaymentCardNumber, UserID) VALUES(?,?,?,?,?)");
        createQuery.setString(1, paymentMethod);
        createQuery.setDate(2, new java.sql.Date(expiryDate.getTime()));
        createQuery.setInt(3, paymentCVC);
        createQuery.setInt(4, paymentCardNumber);
        createQuery.setInt(5, userID);
        
        createQuery.executeUpdate();
    }
    
    
    //Update Operation: update user - all
    public void updatePayment(int paymentID, String paymentMethod, Date expiryDate, int paymentCVC, int paymentCardNumber) throws SQLException {
        
        PreparedStatement updateQuery = connect.prepareStatement("UPDATE payment SET PaymentMethod=? , ExpiryDate=? , PaymentCVC=? , PaymentCardNumber=? WHERE UserID=?");
        updateQuery.setString(1, paymentMethod);
        updateQuery.setDate(2, new java.sql.Date(expiryDate.getTime()));
        updateQuery.setInt(3, paymentCVC);
        updateQuery.setInt(4, paymentCardNumber);
        updateQuery.setInt(5, paymentID);
        
        updateQuery.executeUpdate();
    }
    
    
   
    
    //Delete Operation: delete a payment by paymentID.
    public void deletePayment(int PaymentID) throws SQLException{
        
        String deleteQuery = "DELETE FROM Payment WHERE PaymentID=" + PaymentID;
        statement.executeUpdate(deleteQuery);
    
    }
    
    //Delete Operation: delete a payment by userID.
    public void deletePaymentByUser(int UserID) throws SQLException{
        
        String deleteQuery = "DELETE FROM Payment WHERE UserID=" + UserID;
        statement.executeUpdate(deleteQuery);
    
    }
    
    
    
    public ArrayList<Payment> fetchPayment() throws SQLException {
        ResultSet resultSet = readStatement.executeQuery(readQuery);
        ArrayList<Payment> payments = new ArrayList<>();
        
        while (resultSet.next()) {
            int paymentID = resultSet.getInt(1);
            String paymentMethod = resultSet.getString(2);
            Date expiryDate = resultSet.getDate(3);
            int paymentCVC = resultSet.getInt(4);
            int paymentCardNumber = resultSet.getInt(5);
            int userID = resultSet.getInt(6);

            
            Payment payment = new Payment(paymentID, paymentMethod, expiryDate, paymentCVC, paymentCardNumber, userID);
            payments.add(payment);
        }
        
        return payments;
    }
    
    public void updatePaymentMethod(int paymentID, String paymentMethod) throws SQLException{
        String updateQuery = "UPDATE payment SET PaymentMethod='" + paymentMethod + "' WHERE PaymentID=" + paymentID;
        statement.executeUpdate(updateQuery);
    }
    public void updateExpiryDate(int paymentID, Date expiryDate) throws SQLException{
        PreparedStatement updateQuery = connect.prepareStatement("UPDATE payment SET ExpiryDate=? WHERE PaymentID=?");
        updateQuery.setDate(1, new java.sql.Date(expiryDate.getTime()));
        updateQuery.setInt(2, paymentID);
        updateQuery.executeUpdate();
    }
    
    public void updatePaymentCVC(int paymentID, int paymentCVC) throws SQLException{
        String updateQuery = "UPDATE payment SET PaymentCVC='" + paymentCVC + "' WHERE PaymentID=" + paymentID;
        statement.executeUpdate(updateQuery);
    }
    
    public void updateCardNumber(int paymentID, int paymentCardNumber) throws SQLException{
        String updateQuery = "UPDATE payment SET PaymentCardNumber='" + paymentCardNumber + "' WHERE PaymentID=" + paymentID;
        statement.executeUpdate(updateQuery);
    }
    
    public Payment findPaymentRecord(int paymentID) throws SQLException {
        String find = "SELECT * FROM Payment WHERE PaymentID=" + paymentID;
        ResultSet result = readStatement.executeQuery(find);
        String paymentMethod = result.getString(2);
        Date expiryDate = result.getDate(3);
        int paymentCVC = result.getInt(4);
        int paymentCardNumber = result.getInt(5);
        int userID = result.getInt(6);
        Payment payment = new Payment(paymentID, paymentMethod, expiryDate, paymentCVC, paymentCardNumber, userID);
        return payment;
    }
    
    public ArrayList<Payment> fetchPaymentFromACustomer(int customerID) throws SQLException {
        ArrayList<Payment> payments = new ArrayList<> ();
        for(Payment payment : fetchPayment()){
            if(payment.getCustomerID() == customerID) {
                payments.add(payment);
            }
        }
        return payments;
    }

}
