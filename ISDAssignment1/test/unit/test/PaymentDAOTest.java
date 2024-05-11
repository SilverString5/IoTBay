/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;
//Needs to be tested based on the user stories!!
//Is useful for the servlets (hopefully).

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import uts.isd.model.Payment;
import uts.isd.dao.DBConnector;
import uts.isd.dao.PaymentDAO;


public class PaymentDAOTest {
    private DBConnector connector;
    private Connection conn;
    private PaymentDAO manager;
    
    public PaymentDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        manager = new PaymentDAO(conn);
    }
    
    @Test
     public void testConnection() throws SQLException {
        assertNotNull(conn);
    }     
     
    @Test
    public void testSelectUsers() throws SQLException {
        ArrayList<Payment> payments = manager.fetchPayments();
        assertEquals(payments.size(), 2);
    }  
    
    
    
    //updating the Payment Method of the payment.
    @Test
    public void testUpdatePayment() throws SQLException {
        paymentDAO.updatePayment(2, "VISA", "12-12-2003", );
        Payment paymentex = manager.findPaymentRecord(2);
        
    }
    
    //updating expiry date of payment
    @Test
    public void testUpdateExpiryDate() throws SQLException{
        paymentDAO.updateExpiryDate(2, "12-12-2003");
        Payment paymentex = manager.findPaymentRecord(2);
        assertEquals(paymentex.getExpiryDate(), "12-12-2003");
    }
    
    
    
    
    //deleting payment record
    @Test
    public void testDeletePayment() throws SQLException {
        paymentDAO.deleteShipment(3);
    }
    
    //Create a Payment. -> Maybe id Userid = ?
    @Test
    public void testCreateAPayment() throws SQLException {
        manager.createPayment("VISA", "12-02-2025", 123, 12343322, 2);
        //ArrayList<Payment> payments = manager.
    }
            
            
    //Output all the Payment Records from one payment
}