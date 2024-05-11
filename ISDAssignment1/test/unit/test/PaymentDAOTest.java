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
     
     
     //??? Test this later....
    //@Test
    //public void testSelectUsers() throws SQLException {
    //    ArrayList<Payment> payments = manager.fetchPayments();
    //    assertEquals(payments.size(), 21);
    //}  
    
    
    
    //updating the Payment Method of the payment and checking if all the updated values are correct.
    @Test
    public void testUpdatePayment() throws SQLException {
        paymentDAO.updatePayment("VISA", "12-12-2030", 332, 28763024, 4 );
        Payment paymentex = manager.findPaymentRecord(4);
        assertEquals(paymentex.getPaymentMethod(), "VISA");
        assertEquals(paymentex.getExpiryDate(), "12-12-2030");
        assertEquals(paymentex.getPaymentCVC(), 332);
        assertEquals(paymentex.getPaymentCardNumber(), 28763024);
        
    }
    
    //updating only expiry date of payment
    @Test
    public void testUpdateExpiryDate() throws SQLException{
        paymentDAO.updateExpiryDate(2, "12-02-2003");
        Payment paymentex = manager.findPaymentRecord(2);
        assertEquals(paymentex.getExpiryDate(), "12-12-2003");
    }

    
    //deleting payment record
    @Test
    public void testDeletePayment() throws SQLException {
        paymentDAO.deletePayment(3);
    }
    
    
    
    
    //Create a Payment and test if it worked.
    @Test
    public void testCreateAPayment() throws SQLException {
        Payment paymentex = manager.createPayment("VISA", "12-02-2025", 123, 12343322, 2);
        assertEquals(paymentex.getPaymentMethod(), "VISA");
        assertEquals(paymentex.getExpiryDate(), "12-02-2025");
        assertEquals(paymentex.getPaymentCVC(), 123);
        assertEquals(paymentex.getPaymentCardNumber(), 12343322);
    }
            
            
    //Output all the Payment Records from one payment
    @Test
    public void testfetchAllUserPayments() throws SQLException {
        ArrayList<Payment> payments = manager.fetchPaymentsFromACustomer(8);
        assertEquals(payments.size(), 2);
    }
}