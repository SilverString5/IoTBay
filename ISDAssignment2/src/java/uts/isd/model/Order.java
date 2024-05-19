package uts.isd.model;
//import java.util.*;
import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
    private int orderID;
    private Date orderDate;
    private String orderStatus;
    private int customerID;
    private int shipmentID;
    private double totalAmount;
//    private String shipmentStatus;
    
    public Order(int orderID, Date orderDate, String orderStatus, double totalAmount, int customerID, int shipmentID){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
//        this.shipmentStatus = shipmentStatus;
        this.totalAmount = totalAmount;
        this.customerID = customerID;
        this.shipmentID = shipmentID;
    }
    
    public void setOrderID(int orderID){
        this.orderID = orderID;
    }
    
    public void setOrderDate (Date orderDate){
        this.orderDate = orderDate;
    }
    
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }
    
//    public void setShipmentStatus(String shipmentStatus){
//        this.shipmentStatus = shipmentStatus;       
//    }
    
    public void setTotalAmount(int totalAmount){
        this.totalAmount = totalAmount;
    }
    
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }
    
    public void setShipmentID(int shipmentID){
        this.shipmentID = shipmentID;
    }
    
    public int getOrderID(){
        return this.orderID;
    }
    
    public Date getOrderDate (){
        return this.orderDate;
    }
    
    public String getOrderStatus(){
        return this.orderStatus;
    }
    
//    public String getShipmentStatus(){
//        return this.shipmentStatus;
//    }
    
    public double getTotalAmount(){
        return this.totalAmount;
    }
    
    public int getCustomerID(){
        return this.customerID;
    }
    
    public int getShipmentID(){
        return this.shipmentID;
    }
}