/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author lorinchanel
 */
public class Shipments implements Serializable {
    
    private ArrayList<Shipment> listOfCustomerShipments = new ArrayList<>();
    
    public Shipments(ArrayList<Shipment> listOfCustomerShipments) {
        this.listOfCustomerShipments = listOfCustomerShipments;
    }
    
    public ArrayList<Shipment> getListOfCustomerShipments() {
        return listOfCustomerShipments;
    }

    public void setListOfCustomerShipments(ArrayList<Shipment> listOfCustomerShipments) {
        this.listOfCustomerShipments = listOfCustomerShipments;
    }
    
}
