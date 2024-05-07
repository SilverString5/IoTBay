/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import uts.isd.model.Shipment;
import java.text.SimpleDateFormat;

import java.time.*;

/**
 *
 * @author lorinchanel
 */
public class ShipmentDAO {
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement readStatement;
    //private String readQuery = "SELECT * FROM shipment WHERE customerID=?"; //Change table name depending on name from database
    private String readQuery = "SELECT * FROM shipment";
    //private String deleteQuery = "DELETE FROM shipment WHERE SHIPMENTID=?";
    
    public ShipmentDAO (Connection connection) throws SQLException {
        
        this.connect = connection;
        connection.setAutoCommit(true);
        statement = connection.createStatement();
        readStatement = connection.prepareStatement(readQuery);
        
    }
    
    //Create - Create Shipment Detail
    public void createShipment(int customerID, String shipmentAddress, String shipmentMethod, String shipmentStatus, Date shipmentDate) throws SQLException {
        
        /*
        String insertLine = "INSERT INTO shipment(UserID, ShipmentAddress, ShipmentMethod, ShipmentStatus, ShipmentDate)";
        String valuesLine = "VALUES('" + customerID + "','" + shipmentAddress + "','" + shipmentMethod + "','" + shipmentStatus + "','" + new java.sql.Date(shipmentEstDate.getTime()) + "')";
        String valuesLine = "VALUES(?,?,?,?,?)"; */
        
        
        PreparedStatement createQuery = connect.prepareStatement("INSERT INTO shipment(UserID, ShipmentAddress, ShipmentMethod, ShipmentStatus, ShipmentDate) VALUES(?,?,?,?,?)");
        createQuery.setInt(1, customerID);
        createQuery.setString(2, shipmentAddress);
        createQuery.setString(3, shipmentMethod);
        createQuery.setString(4, shipmentStatus);
        createQuery.setDate(5, new java.sql.Date(calculateShipmentDate(shipmentDate).getTime()));
        
        System.out.println(createQuery);
        
        createQuery.executeUpdate();
                
    }
    
    public Date calculateShipmentDate(Date currentDate) {
        currentDate.setTime(currentDate.getTime() - 36000000);
        System.out.println(currentDate);
        
        /*
        Calendar tempDate = Calendar.getInstance();
        tempDate.setTime(currentDate);
        tempDate.add(Calendar.DAY_OF_MONTH, 2);
        System.out.println(tempDate + " " + currentDate);*/
        currentDate.setTime(currentDate.getTime() + 172800000);
        System.out.println(currentDate);
        
        return currentDate;
    }
    
    //Read/Fetch - in general
    public ArrayList<Shipment> fetchShipment() throws SQLException {
        
        ResultSet resultSet = statement.executeQuery(readQuery);
        ArrayList<Shipment> shipmentRecord = new ArrayList<>();
        
        while(resultSet.next()) {
            String shipmentAddress = resultSet.getString(2);
            String shipmentMethod = resultSet.getString(3);
            String shipmentStatus = resultSet.getString(4);
            Date shipmentEstDate = resultSet.getDate(5);
           
            int shipmentID = resultSet.getInt(1);
            int customerID = resultSet.getInt(6);
            
            Shipment shipment = new Shipment(shipmentID, customerID, shipmentAddress, shipmentMethod, shipmentStatus, shipmentEstDate);
            
            shipmentRecord.add(shipment);
            
        }
        
        return shipmentRecord;
        
    }
    
    //get all shipments made by a specific customer
    public ArrayList<Shipment> fetchShipmentFromACustomer(int customerID) throws SQLException {
        
        ArrayList<Shipment> shipmentRecords = new ArrayList<> ();
        
        for(Shipment shipment : fetchShipment()){
            if(shipment.getCustomerID() == customerID){
                shipmentRecords.add(shipment);
            }
        }
        
        return shipmentRecords;
    }
    
    public ArrayList<Shipment> fetchShipmentByFilter(int customerID, int shipmentID, String shipmentDate) throws SQLException {
        
        ArrayList<Shipment> shipmentRecords = new ArrayList<> ();
        for(Shipment shipment : fetchShipmentFromACustomer(customerID)){
            if(shipment.getShipmentID() == shipmentID && shipment.getShipmentEstTime().toString().equals(shipmentDate)){
               
                shipmentRecords.add(shipment);
            }
        }
        
        return shipmentRecords;
    }
    
    public Shipment fetchShipmentByFilter(int customerID, int shipmentID) throws SQLException {
        
        Shipment shipment = new Shipment(); 
        for(Shipment shipmentInRecord : fetchShipmentFromACustomer(customerID)){
            if(shipmentInRecord.getShipmentID() == shipmentID){
               
                shipment = shipmentInRecord;
            }
        }
        
        return shipment;
    }
    
    //&& shipment.getShipmentEstTime().getTime() == shipmentDate.getTime()
    
    
    //update shipment - general 
    public void updateShipment(int shipmentID, String shipmentAddress, String shipmentMethod, String shipmentStatus, Date shipmentDate) throws SQLException {
        
        PreparedStatement updateQuery = connect.prepareStatement("UPDATE shipment SET SHIPMENTADDRESS=? , SHIPMENTMETHOD=? , SHIPMENTSTATUS=? , SHIPMENTDATE=? WHERE SHIPMENTID=?");
        updateQuery.setString(1, shipmentAddress);
        updateQuery.setString(2, shipmentMethod);
        updateQuery.setString(3, shipmentStatus);
        updateQuery.setDate(4, new java.sql.Date(shipmentDate.getTime()));
        updateQuery.setInt(5, shipmentID);
        
        updateQuery.executeUpdate();
        
        
    }
    
    //Update - update the status of the shipment
    public void updateShipmentAddress(int shipmentID, String shipmentAddress) throws SQLException {
        

        String updateQuery = "UPDATE shipment SET SHIPMENTADDRESS='" + shipmentAddress + "' WHERE SHIPMENTID=" + shipmentID;
        statement.executeUpdate(updateQuery);
        
        
    }
    
    //Update - update the status of the estimated date date
    public void updateShipmentMethod(int shipmentID, String shipmentMethod) throws SQLException {
        
            String updateQuery = "UPDATE shipment SET SHIPMENTMETHOD='" + shipmentMethod + "' WHERE SHIPMENTID=" + shipmentID;
            statement.executeUpdate(updateQuery);
        
        
    }
    
    //delete
    public void deleteShipment(int shipmentID) throws SQLException {
            
            String deleteQuery = "DELETE FROM shipment WHERE SHIPMENTID=" + shipmentID;
            statement.executeUpdate(deleteQuery);
        }
    
    
    private Shipment findShipmentDetail (int shipmentID) throws SQLException {
        
        for(Shipment shipment : fetchShipment()){
            if(shipment.getShipmentID() == shipmentID) {
                
                return shipment;
                
            }
        }
        
        return null;
        
    }

    
}
