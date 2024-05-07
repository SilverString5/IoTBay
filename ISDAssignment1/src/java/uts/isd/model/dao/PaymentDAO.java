/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import uts.isd.model.*;

public class PaymentDAO {
    private Statement st;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    
    
    private String readQuery = "SELECT * FROM Payments WHERE UserID=? AND PaymentID=?";
    private String updateQuery = "UPDATE Payments SET PaymentMethod=?, PaymentDate=?, PaymentCardNumber=?, PaymentCVC=? WHERE PaymentId=?";
    private String deleteQuery = "DELETE FROM Payments WHERE UserID=? AND PaymentID=?";
    
    public PaymentDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        st = connection.createStatement();
        readSt = connection.prepareStatement(readQuery);
        updateSt = connection.prepareStatement(updateQuery);
        deleteSt = connection.prepareStatement(deleteQuery);
    }
    
    //Create Operation: Create a payment
    public void createPayment(String paymentMethod, Date paymentDate, int paymentCardNumber, int paymentCVC) throws SQLException {
        String columns = "INSERT INTO Payments(paymentMethod, paymentDate, paymentCardNumber, paymentCVC)";
        String values = "VALUES('" + paymentMethod + "','" + paymentDate + "','" + paymentCardNumber + "','" + paymentCVC + "')";
        st.executeUpdate(columns + values);
    }
    
    //Update Operation: update user 
    public void updatePayment(String paymentMethod, Date paymentDate, int paymentCardNumber, int paymentCVC, int paymentId) throws SQLException {
        updateSt.setString(1, paymentMethod);
        updateSt.setDate( 2, new java.sql.Date(paymentDate.getTime()));
        updateSt.setString(3, Integer.toString(paymentCardNumber));
        updateSt.setString(4, Integer.toString(paymentCVC));
        updateSt.setString(5, Integer.toString(paymentId));
        int row = updateSt.executeUpdate();
        System.out.println("row" + row + "updated successfully");
    }
    
    
    //Delete Operation: delete a payment by paymentID.
    public void deletePayment(int PaymentID) throws SQLException{
        deleteSt.setString(1, Integer.toString(PaymentID));
        int row = deleteSt.executeUpdate();
        System.out.println("row" + row + " deleted successfully.");
    }
    
    public ArrayList<Payment> getPayment() throws SQLException {
        String fetch = "SELECT * FROM Payment";
        ResultSet rs = readSt.executeQuery(fetch);
        ArrayList<Payment> payments = new ArrayList<>();
        
        while (rs.next()) {
            int paymentID = rs.getInt(1);
            String paymentMethod = rs.getString(2);
            Date paymentDate = rs.getDate(3);
            int paymentCardNumber = rs.getInt(4);
            int paymentCVC = rs.getInt(5);
            
            Payment payment = new Payment(paymentID, paymentMethod, paymentDate, paymentCardNumber, paymentCVC);
            payments.add(payment);
        }
        
        return payments;
    }
    
}
