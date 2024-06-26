/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import uts.isd.model.Order;
import uts.isd.model.Product;

/**
 *
 * @author pyaephyozaw
 */
public class OrderDAO {
    private Statement st;
    private PreparedStatement readSt;
    private PreparedStatement readSpecificOrderSt;
    private PreparedStatement saveOrderSt;
    private PreparedStatement submitOrderSt;
    private PreparedStatement orderLineItemSt;
    private PreparedStatement fetchPendingOrderSt;
    private PreparedStatement updateSt;
    private PreparedStatement fetchProductIdSt;
    private PreparedStatement searchOrderSt;
    private PreparedStatement updateStatusSt;
    private PreparedStatement updateStatusSt2;
    private PreparedStatement fetchRecentOrderSt;
    private PreparedStatement updateOrderSt;
    private PreparedStatement anonymousOrderSt;
    private PreparedStatement orderPaymentSt;
    
    private String readQuery = "SELECT * FROM `Order` WHERE UserID=?";
    private String readSpecificOrder = "SELECT * FROM `Order` WHERE OrderID=? AND OrderDate=?";
    private String fetchPendingOrder = "SELECT OrderID FROM `Order` WHERE UserID=? AND OrderStatus='Pending'";
    private String orderLineItem = "INSERT INTO `OrderLineItem` (ProductID, OrderID, ProductQuantity, SubTotal) VALUES (?, ?, ?, ?) "
                                    + "ON DUPLICATE KEY UPDATE ProductQuantity = VALUES(ProductQuantity), SubTotal = VALUES(SubTotal)";
    private String fetchProductID = "SELECT * FROM `OrderLineItem` WHERE OrderID=?";
    
    public String updateStatus = "UPDATE `Order` SET OrderStatus='Cancelled' WHERE UserID=?";
    public String updateStatus2 = "UPDATE `Order` SET OrderStatus='Cancelled' WHERE OrderID=?";
    private String submitOrder = "INSERT INTO `Order` (UserID,OrderDate,OrderStatus,TotalAmount,ShipmentID) VALUES (?, ?, ?, ?, ?)";
    private String anonymousOrder = "INSERT INTO `Order` (OrderDate,OrderStatus,TotalAmount,ShipmentID) VALUES (?, ?, ?, ?)";
    private String fetchRecentOrder = "Select OrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1";
    private String updateOrder = "UPDATE `Order` SET OrderDate=?, TotalAmount=? WHERE OrderID=?";
    private String orderPayment = "INSERT INTO `Order_Payment` (PaymentID,OrderID,PaymentDate,PaymentTime) VALUES (?,?,?,?)";
            
    public OrderDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        st = connection.createStatement();
        readSt = connection.prepareStatement(readQuery);
        readSpecificOrderSt = connection.prepareStatement(readSpecificOrder);
        submitOrderSt = connection.prepareStatement(submitOrder);
        orderLineItemSt = connection.prepareStatement(orderLineItem);
        fetchPendingOrderSt = connection.prepareStatement(fetchPendingOrder);
        fetchProductIdSt = connection.prepareStatement(fetchProductID);
        updateStatusSt = connection.prepareStatement(updateStatus);
        updateStatusSt2 = connection.prepareStatement(updateStatus2);
        fetchRecentOrderSt = connection.prepareStatement(fetchRecentOrder);
        updateOrderSt = connection.prepareStatement(updateOrder);
        anonymousOrderSt = connection.prepareStatement(anonymousOrder);
        orderPaymentSt = connection.prepareStatement(orderPayment);
        
    }
        
    //Create Operation - Create and Submit the order
    public int SubmitOrder (int customerID, double totalAmount, ArrayList<Integer> quantityList, ArrayList<Product> cartList, int shipmentID) throws SQLException { 
        submitOrderSt.setInt(1, customerID);
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        submitOrderSt.setDate(2, sqlDate);
        submitOrderSt.setString(3, "Processing");
        submitOrderSt.setDouble(4, totalAmount);
        submitOrderSt.setInt(5, shipmentID);
        submitOrderSt.executeUpdate();
        
        int i = 0;
        ResultSet rs = fetchRecentOrderSt.executeQuery();
        int orderID = 0;
        while(rs.next()){
            orderID = rs.getInt(1);
            for (Product product : cartList){
                  orderLineItemSt.setInt(1, product.getProductID());
                  orderLineItemSt.setInt(2, orderID);
                  int quantity = quantityList.get(i);
                  i++;
                  orderLineItemSt.setInt(3, quantity); //add
                  double subTotal = quantity * product.getProductUnitPrice();
                  orderLineItemSt.setDouble(4, subTotal);
                  orderLineItemSt.executeUpdate();
              }
        }
        return orderID;
       
    }
    
    public int anonymousOrder (double totalAmount, ArrayList<Integer> quantityList, ArrayList<Product> cartList, int shipmentID) throws SQLException { 
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        anonymousOrderSt.setDate(1, sqlDate);
        anonymousOrderSt.setString(2, "Processing");
        anonymousOrderSt.setDouble(3, totalAmount);
        anonymousOrderSt.setInt(4, shipmentID);
        anonymousOrderSt.executeUpdate();
        
        int orderID = 0;
        int i = 0;
        ResultSet rs = fetchRecentOrderSt.executeQuery();
        while(rs.next()){
            orderID = rs.getInt(1);
            for (Product product : cartList){
                  orderLineItemSt.setInt(1, product.getProductID());
                  orderLineItemSt.setInt(2, orderID);
                  int quantity = quantityList.get(i);
                  i++;
                  orderLineItemSt.setInt(3, quantity); //add
                  double subTotal = quantity * product.getProductUnitPrice();
                  orderLineItemSt.setDouble(4, subTotal);
                  orderLineItemSt.executeUpdate();
              }
        }
    return orderID; 
    }
    
    public void updateOrder (int orderID, double totalAmount, ArrayList<Integer> quantityList, ArrayList<Product> productList) throws SQLException{
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        updateOrderSt.setDate(1, sqlDate);
        updateOrderSt.setDouble(2, totalAmount);
        updateOrderSt.setInt(3, orderID);
        updateOrderSt.executeUpdate();
        int i = 0;
        for(Product product : productList){
            orderLineItemSt.setInt(1, product.getProductID());
            orderLineItemSt.setInt(2, orderID);
            int quantity = quantityList.get(i);
            i++;
            orderLineItemSt.setInt(3, quantity);
            double subTotal = quantity * product.getProductUnitPrice();
            orderLineItemSt.setDouble(4, subTotal);
            orderLineItemSt.executeUpdate();
        }
    }
    
    //Read Operation - Fetch order history list of a customer
    public ArrayList<Order> readOrderHistory (int customerID) throws SQLException{
       readSt.setInt(1, customerID);
       ResultSet rs = readSt.executeQuery();
       ArrayList<Order> orderList = new ArrayList<>(); 
       while(rs.next()){
           int orderID = rs.getInt(1);
           Date orderDate = rs.getDate(2);
           String orderStatus = rs.getString(3);
           double totalAmount = rs.getDouble(4);
           int shipmentID = 0; //Default shipmentID
           String ShipmentIDString = rs.getString(5);
           if(ShipmentIDString != null){
               shipmentID = rs.getInt(5);
           }
           Order order = new Order(orderID, orderDate, orderStatus, totalAmount, customerID, shipmentID);
           orderList.add(order);
           
       }
       return orderList; 
    }  
    
    // Read - Get OrderID of the pending order (shopping cart) Delete?
    public int getOrderID (int customerID) throws SQLException {
        fetchPendingOrderSt.setInt(1, customerID);
        ResultSet rs = fetchPendingOrderSt.executeQuery();
        int orderID = 0;
        while(rs.next()){
            orderID = rs.getInt(1);
        }
        return orderID;       
    }
    
    //Read - Get ProductIDs from OrderLineItem based on the orderID
    public ArrayList<Integer> getProductIDList(int orderID) throws SQLException {
        ArrayList<Integer> productIDList = new ArrayList();
        fetchProductIdSt.setInt(1, orderID);
        ResultSet rs = fetchProductIdSt.executeQuery();
        while(rs.next()){
            productIDList.add(rs.getInt(1));
        }
        return productIDList;
    }
    
    public HashMap<Integer, Integer> getQuantity(int orderID) throws SQLException {
        HashMap<Integer, Integer> map = new HashMap();
        fetchProductIdSt.setInt(1, orderID);
        ResultSet rs = fetchProductIdSt.executeQuery();
        while(rs.next()){
            map.put(rs.getInt(1), rs.getInt(3));
        }
        return map;
    }
    
    //Read Operation
    public Order searchOrder(int orderID, Date orderDate) throws SQLException {
        readSpecificOrderSt.setInt(1, orderID);
        readSpecificOrderSt.setDate(2, orderDate);
        Order order = null;
        ResultSet rs = readSpecificOrderSt.executeQuery();
        if(rs.next()){
            String orderStatus = rs.getString(3);
            double totalAmount = rs.getDouble(4);
            int shipmentID = 0; //Default shipmentID
            String ShipmentIDString = rs.getString(5);
            if(ShipmentIDString != null){
                shipmentID = rs.getInt(5);
            }
            int customerID = rs.getInt(6);
            order = new Order(orderID, orderDate, orderStatus, totalAmount, customerID, shipmentID);
        }
        return order;
    }
    
    public void changeOrderStatus (int userID) throws SQLException{
        updateStatusSt.setInt(1, userID);
        updateStatusSt.executeUpdate();
        
    }
    
    //Delete Operation: when the user cancel the order, change the order status to "Cancelled" 
    public void cancelOrder (int orderID) throws SQLException{
        updateStatusSt2.setInt(1, orderID);
        updateStatusSt2.executeUpdate();
    }
    
    //Add a row in order_payment table when the user submits the order
    public void updateOrderPayment (int orderID, int paymentID) throws SQLException{
        orderPaymentSt.setInt(1, paymentID);
        orderPaymentSt.setInt(2, orderID);
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        orderPaymentSt.setDate(3, sqlDate);
        LocalTime time = LocalTime.now();
        Time sqlTime = Time.valueOf(time);
        orderPaymentSt.setTime(4, sqlTime);
        orderPaymentSt.executeUpdate();
    }
    
}