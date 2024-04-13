/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;

/**
 *
 * @author lorinchanel
 */
public class Shipment implements Serializable {

    private int shipmentID;
    private int customerID;
    private String shipmentAddress;

    
    public Shipment(int shipmentID, int customerID, String shipmentAddress){
        this.shipmentID = shipmentID;
        this.customerID = customerID;
        this.shipmentAddress = shipmentAddress;
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

    public String getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
    
}
