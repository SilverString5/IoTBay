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
import java.util.ArrayList;
import java.util.Date;
import uts.isd.model.Order;

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
    
    private String readQuery = "SELECT * FROM `Order` Where CustomerID=?";
    private String readSpecificOrder = "SELECT * FROM `Order` WHERE OrderID=? AND OrderDate=?";
    private String saveOrder = "INSERT INTO `Order` (OrderStatus, ShipmentStatus, TotalAmount, CustomerID) VALUES (?, ?, ?, ?)";
    private String submitOrder = "UPDATE `Order` SET OrderStatus='Complete' WHERE OrderID=?";
    private String orderLineItem = "INSERT INTO `OrderLineItem` (ProductID, OrderID, ProductQuantity, SubTotal) VALUES (?, ?, ?, ?)";
    private String fetchPendingOrder = "SELECT OrderID FROM `Order` WHERE CustomerID=? AND OrderStatus='Pending'";
    private String deleteOrder = "DELETE FROM `Order` WHERE OrderID=?";
    
    public OrderDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        st = connection.createStatement();
        readSt = connection.prepareStatement(readQuery);
        readSpecificOrderSt = connection.prepareStatement(readSpecificOrder);
        saveOrderSt = connection.prepareStatement(saveOrder);
        submitOrderSt = connection.prepareStatement(submitOrder);
        orderLineItemSt = connection.prepareStatement(orderLineItem);
        fetchPendingOrderSt = connection.prepareStatement(fetchPendingOrder);
        deleteSt = connection.prepareStatement(deleteOrder);
        
    }
    
    //Create Operation -  add items to the order and save the order
    public void CreateOrder (int totalAmount, int customerID, int productID, int quantity, int subTotal) throws SQLException{
//        String columns = "INSERT INTO Order(OrderDate,OrderStatus,ShipmentStatus,TotalAmount,ShipmentID,CustomerID)";
//        String values = "VALUES ('" + OrderDate + "','Pending','Pending'," + totalAmount + "" )"
//        PreparedStatement preparedStatement = connection.preparation
//          saveOrderSt.setDate(1, "");
           
           //if condition required here
          saveOrderSt.setString(1, "Pending");
          saveOrderSt.setString(2, "Pending");
          saveOrderSt.setInt(3, totalAmount);
          saveOrderSt.setInt(4, customerID);
          saveOrderSt.executeUpdate();
         
//          String fetchPendingOrder = "SELECT OrderID FROM `Order` WHERE OrderStatus='Pending'";  //add customer ID as well
          fetchPendingOrderSt.setInt(1, customerID);
          ResultSet rs = fetchPendingOrderSt.executeQuery();
          while(rs.next()){
              int orderID = rs.getInt(1);
              orderLineItemSt.setInt(1, productID);
              orderLineItemSt.setInt(2, orderID);
              orderLineItemSt.setInt(3, quantity);
              orderLineItemSt.setInt(4, subTotal);
              orderLineItemSt.executeUpdate();
              System.out.println("Entered");
              System.out.println(orderID);
          }
    }
    
    //Create Operation - Sumit the order
    public void SubmitOrder (int customerID) throws SQLException { //just add orderID? (TBD) 
        fetchPendingOrderSt.setInt(1, customerID);
        ResultSet rs = fetchPendingOrderSt.executeQuery();
        while(rs.next()){
            int orderID = rs.getInt(1);
            submitOrderSt.setInt(1, orderID);
            submitOrderSt.executeUpdate();
            System.out.println("Entered");
        }
       
    }
    
    //Read Operation 
    public ArrayList<Order> readOrderHistory (int customerID) throws SQLException{
       readSt.setInt(1, customerID);
       ResultSet rs = readSt.executeQuery();
       ArrayList<Order> orderList = new ArrayList<>(); 
       while(rs.next()){
           int orderID = rs.getInt(1);
           String orderDate = rs.getString(2);
           String orderStatus = rs.getString(3);
           String shipmentStatus = rs.getString(4);
           int totalAmount = rs.getInt(5);
//           int customerID = Integer.parseInt(rs.getString(7));
           int shipmentID = 0; //Default shipmentID
           String ShipmentIDString = rs.getString(6);
           if(ShipmentIDString != null){
               shipmentID = rs.getInt(6);
           }
           Order order = new Order(orderID, orderDate, orderStatus, shipmentStatus, totalAmount, customerID, shipmentID);
           orderList.add(order);
           
       }
       return orderList; 
    }
    
    //Delete Operation
    public void deleteOrder (int orderID) throws SQLException{
        deleteSt.setInt(1, orderID);
        int row = deleteSt.executeUpdate();
        System.out.println(row + " rows deleted");
    }
    
    
    
}
