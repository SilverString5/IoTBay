/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import uts.isd.model.*;
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
    
    @Test
    public void testCreateOrder() throws SQLException {
        ArrayList<Integer> quantityList = new ArrayList();
        quantityList.add(2);
        
        Product product = new Product(1, "DS18B20", "Temperature/Humidity/Air Pressure/Gas", 4, "DHT22 measure all humidity ranges from 0-100% with an accuracy of 2%.", 50, "");
        ArrayList<Product> cartList = new ArrayList();
        cartList.add(product);
        
        int orderID = orderDAO.SubmitOrder(10, 8, quantityList, cartList, 50); 
        System.out.println("Order submitted suceessfully");
        //Test
        ArrayList<Order> orders = new ArrayList();
        orders = orderDAO.readOrderHistory(10); 
        int lastIndex = orders.size() - 1;
        Order order = orders.get(lastIndex);
        
        assertEquals(order.getOrderID(), orderID);
        assertEquals(order.getOrderStatus(), "Processing");
        assertEquals(order.getTotalAmount(),8.0,0.001);
        assertEquals(order.getCustomerID(), 10);
        assertEquals(order.getShipmentID(), 50);                               
    }
    
 
    @Test
    public void testAnonymousCreateOrder() throws SQLException {
        ArrayList<Integer> quantityList = new ArrayList();
        quantityList.add(2);
        
        Product product = new Product(1, "DS18B20", "Temperature/Humidity/Air Pressure/Gas", 4, "DHT22 measure all humidity ranges from 0-100% with an accuracy of 2%.", 50, "");
        ArrayList<Product> cartList = new ArrayList();
        cartList.add(product);
        
        int orderID = orderDAO.anonymousOrder(8, quantityList, cartList, 38); 
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        System.out.println("AnonymousOrder submitted suceessfully");
        //Test
//        ArrayList<Order> orders = new ArrayList();
//        orders = orderDAO.readOrderHistory(1); 
//        int lastIndex = orders.size() - 1;
        Order order = orderDAO.searchOrder(orderID, sqlDate);
        
        assertEquals(order.getOrderID(), orderID);
        assertEquals(order.getOrderStatus(), "Processing");
        assertEquals(order.getTotalAmount(),8.0,0.001);
        System.out.println("ID: " + order.getCustomerID());
        assertEquals(order.getCustomerID(), 0);  //since the user is anonymous, the userID is 0 (null)
        assertEquals(order.getShipmentID(), 38);                               
    }
    
    @Test
    public void fetchUserOrders() throws SQLException{
        ArrayList<Order> orders = orderDAO.readOrderHistory(1);
        assertTrue("At least one order", orders.size() > 0);
    }
    
    @Test
    public void testSearchOrder() throws SQLException, ParseException{
//        String dateString = "2022-02-02";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = Date.valueOf("2024-04-21");
        Order order = orderDAO.searchOrder(16, orderDate);
        
        assertEquals(order.getOrderID(), 16);
        assertEquals(order.getOrderStatus(), "Completed");
        assertEquals(order.getTotalAmount(), 3.5, 0.001);
        assertEquals(order.getShipmentID(), 1);
        assertEquals(order.getCustomerID(), 19);
    }
    
    @Test
    public void testCancelOrder() throws SQLException {
        orderDAO.cancelOrder(20);  //hardcoded for testing purpose
        System.out.println("One order cancelled successfully");
    }
}
