/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import uts.isd.model.Payment;
import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

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
}