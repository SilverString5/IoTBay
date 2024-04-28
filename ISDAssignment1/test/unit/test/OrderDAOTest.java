/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;
import uts.isd.model.dao.UserDAO;

/**
 *
 * @author pyaephyozaw
 */
public class OrderDAOTest {
    private DBConnector connector;
    private Connection conn;
    private OrderDAO orderDAO;
    public OrderDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        orderDAO = new OrderDAO(conn);
    }
    
    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
    }
    
//    @Test
//    public void testSelectUsers() throws SQLException {
//       ArrayList<User> users = userDAO.fetchUsers();
//       assertEquals(users.size(), 1);
//    }
    
//    @Test
//    public void testCreateOrder() throws SQLException {
//        orderDAO.CreateOrder(0, 1000, 6001, 1, 10);   //hardcoded for testing purpose
//    }
    
//    @Test
//    public void testSubmitOrder() throws SQLException {
//        orderDAO.SubmitOrder(1000);
//    }
    
//    @Test
//    public void testDeleteOrder() throws SQLException {
//        orderDAO.deleteOrder(5001);
//    }
}
