package uts.isd.model;
import java.util.*;
import java.io.Serializable;

public class Order implements Serializable {
    private int orderID;
    private Date orderDate;
    private String orderStatus;
    
    public Order(){
    }
    
    public Order(int orderID, Date orderDate, String orderStatus){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
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
    
    public int getOrderID(){
        return this.orderID;
    }
    
    public Date getOrderDate (){
        return this.orderDate;
    }
    
    public String getOrderStatus(){
        return this.orderStatus;
    }
}
