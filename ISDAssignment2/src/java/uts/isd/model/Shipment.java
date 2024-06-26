/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.time.*;
import java.util.*;
import java.io.Serializable;

/**
 *
 * @author lorinchanel
 */
public class Shipment implements Serializable {
    
    private int shipmentID;
    private int customerID;
    private int orderID;
    private String shipmentAddress;
    private String shipmentMethod;
    
    private String shipmentStatus;
    private Date shipmentEstTime; //refers to when the shipment was dispatched

    
    public Shipment() {
        
    }
    
    public Shipment(int shipmentID, int customerID, String shipmentAddress, String shipmentMethod, String shipmentStatus, Date shipmentEstTime){
        
        this.shipmentID = shipmentID;
        this.customerID = customerID;
        
        this.shipmentAddress = shipmentAddress;
        this.shipmentMethod = shipmentMethod;
        
        this.shipmentStatus = shipmentStatus;
        this.shipmentEstTime = shipmentEstTime;
    }
    
    
    
    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    public String getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
    
    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }
    
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    //Gets the shipment date - refers to when the shipment was dispatched
    public Date getShipmentEstTime() {
        return shipmentEstTime;
    }
    
    //Set the shipment date - refers to when the shipment was dispatched
    public void setShipmentEstTime(Date shipmentEstTime) {
        this.shipmentEstTime = shipmentEstTime;
    }
    
    
}
