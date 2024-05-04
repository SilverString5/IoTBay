/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.time.*;
import java.io.Serializable;

/**
 *
 * @author lorinchanel
 */
public class ShipmentProgress  implements Serializable {

    private int shipmentProgressID;
    private int shipmentID;
    private String shipmentDetails;
    private LocalDate shipmentArrivalDate;
    
    public ShipmentProgress(int shipmentProgressID, int shipmentID, String shipmentDetails, LocalDate shipmentArrivalDate){
        this.shipmentProgressID = shipmentProgressID;
        this.shipmentID = shipmentID;
        this.shipmentDetails = shipmentDetails;
        this.shipmentArrivalDate = shipmentArrivalDate;
    }

    public int getShipmentProgressID() {
        return shipmentProgressID;
    }

    public void setShipmentProgressID(int shipmentProgressID) {
        this.shipmentProgressID = shipmentProgressID;
    }

    public int getShipmentID() {
        return shipmentID;
    }


    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public String getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(String shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    public LocalDate getShipmentArrivalDate() {
        return shipmentArrivalDate;
    }

    public void setShipmentArrivalDate(LocalDate shipmentArrivalDate) {
        this.shipmentArrivalDate = shipmentArrivalDate;
    }
    
}


