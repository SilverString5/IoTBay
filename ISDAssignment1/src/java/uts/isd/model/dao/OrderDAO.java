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
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;
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
    private PreparedStatement deleteSt;
    private PreparedStatement fetchProductIdSt;
    private PreparedStatement searchOrderSt;
    private PreparedStatement updateStatusSt;
    private PreparedStatement fetchRecentOrderSt;
    private PreparedStatement updateOrderSt;
    
    private String readQuery = "SELECT * FROM `Order` WHERE UserID=?";
    private String readSpecificOrder = "SELECT * FROM `Order` WHERE OrderID=? AND OrderDate=?";
    
//    private String submitOrder = "UPDATE `Order` SET OrderStatus='Complete', OrderDate=?, TotalAmount=? WHERE OrderID=?";
//    private String orderLineItem = "INSERT INTO `OrderLineItem` (ProductID, OrderID, ProductQuantity, SubTotal) VALUES (?, ?, ?, ?)";
    private String fetchPendingOrder = "SELECT OrderID FROM `Order` WHERE UserID=? AND OrderStatus='Pending'";
    private String deleteOrder = "DELETE FROM `Order` WHERE OrderID=?";
    private String orderLineItem = "INSERT INTO `OrderLineItem` (ProductID, OrderID, ProductQuantity, SubTotal) VALUES (?, ?, ?, ?) "
                                    + "ON DUPLICATE KEY UPDATE ProductQuantity = VALUES(ProductQuantity), SubTotal = VALUES(SubTotal)";
    private String fetchProductID = "SELECT * FROM `OrderLineItem` WHERE OrderID=?";
    public String updateStatus = "UPDATE `Order` SET OrderStatus='Cancelled' WHERE UserID=?";
    
    private String submitOrder = "INSERT INTO `Order` (UserID,OrderDate,OrderStatus,TotalAmount) VALUES (?, ?, ?, ?)";
    private String fetchRecentOrder = "Select OrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1";
    private String updateOrder = "UPDATE `Order` SET OrderDate=?, TotalAmount=? WHERE OrderID=?";
            
    public OrderDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        st = connection.createStatement();
        readSt = connection.prepareStatement(readQuery);
        readSpecificOrderSt = connection.prepareStatement(readSpecificOrder);
//        saveOrderSt = connection.prepareStatement(saveOrder);
        submitOrderSt = connection.prepareStatement(submitOrder);
        orderLineItemSt = connection.prepareStatement(orderLineItem);
        fetchPendingOrderSt = connection.prepareStatement(fetchPendingOrder);
        deleteSt = connection.prepareStatement(deleteOrder);
        fetchProductIdSt = connection.prepareStatement(fetchProductID);
        updateStatusSt = connection.prepareStatement(updateStatus);
        fetchRecentOrderSt = connection.prepareStatement(fetchRecentOrder);
        updateOrderSt = connection.prepareStatement(updateOrder);
        
    }
    
    //Create Operation -  add items to the order and save the order
    public void CreateOrder (int customerID, double totalAmount, ArrayList<Integer> quantityList, ArrayList<Product> cartList) throws SQLException{
//        String columns = "INSERT INTO Order(OrderDate,OrderStatus,ShipmentStatus,TotalAmount,ShipmentID,CustomerID)";
//        String values = "VALUES ('" + OrderDate + "','Pending','Pending'," + totalAmount + "" )"
//        PreparedStatement preparedStatement = connection.preparation
//          saveOrderSt.setDate(1, "");
//            HttpSession session = request.getSession();
//          HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart"); 
           //if condition required here
          fetchPendingOrderSt.setInt(1, customerID);
          ResultSet rs1 = fetchPendingOrderSt.executeQuery();
          if(!rs1.next()){
                saveOrderSt.setInt(1, customerID);
                saveOrderSt.setString(2, "Processing");
      //          saveOrderSt.setString(2, "Pending");
                saveOrderSt.setDouble(3, totalAmount);
      //          saveOrderSt.setInt(4, customerID);
                saveOrderSt.executeUpdate();
          }else{
              
          }                   
//          String fetchPendingOrder = "SELECT OrderID FROM `Order` WHERE OrderStatus='Pending'";  //add customer ID as well
          int i = 0;
          fetchPendingOrderSt.setInt(1, customerID);
          ResultSet rs = fetchPendingOrderSt.executeQuery();
          while(rs.next()){  //does not work on duplicate: two pending orders
              int orderID = rs.getInt(1);
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
    }
    
    //Create Operation - Sumit the order
    public void SubmitOrder (int customerID, double totalAmount, ArrayList<Integer> quantityList, ArrayList<Product> cartList) throws SQLException { //just add orderID? (TBD) 
        submitOrderSt.setInt(1, customerID);
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        submitOrderSt.setDate(2, sqlDate);
        submitOrderSt.setString(3, "Processing");
        submitOrderSt.setDouble(4, totalAmount);
        submitOrderSt.executeUpdate();
        
        int i = 0;
        ResultSet rs = fetchRecentOrderSt.executeQuery();
        while(rs.next()){
            int orderID = rs.getInt(1);
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
//        orderLineItemSt.setInt(1, productID);
//        orderLineItemSt.setInt(2, orderID);
//        orderLineItemSt.setInt(3, quantity);
//        orderLineItemSt.setDouble(4,quantity * unitPrice);
//        orderLineItemSt.executeUpdate();
//        fetchProductIdSt.setInt(1, orderID);
//        ResultSet rs = fetchProductIdSt.executeQuery();
//        while(rs.next()){
//            int productID = rs.getInt(1);
//         
//        }
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
//           int customerID = Integer.parseInt(rs.getString(7));
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
    // Read - Get OrderID of the pending order (shopping cart)
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
//        ArrayList<Integer> productIDList = new ArrayList();
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
    //Delete Operation
    public void deleteOrder (int orderID) throws SQLException{
        deleteSt.setInt(1, orderID);
        int row = deleteSt.executeUpdate();
        System.out.println(row + " rows deleted");
    }
    
    public void changeOrderStatus (int userID) throws SQLException{
        updateStatusSt.setInt(1, userID);
        updateStatusSt.executeUpdate();
        
    }
}
