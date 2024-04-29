package uts.isd.model;
import java.time.*;
import java.io.Serializable;

public class Order implements Serializable {
    private int orderID;
    private LocalDate orderDate;
    private String orderStatus;
    
    public Order(int orderID, LocalDate orderDate, String orderStatus){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
    
    public void setOrderID(int orderID){
        this.orderID = orderID;
    }
    
    public void setOrderDate (LocalDate orderDate){
        this.orderDate = orderDate;
    }
    
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }
    
    public int getOrderID(){
        return this.orderID;
    }
    
    public LocalDate getOrderDate (){
        return this.orderDate;
    }
    
    public String getOrderStatus(){
        return this.orderStatus;
    }
}
