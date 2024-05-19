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

/**
 *
 * @author lorinchanel
 */
public class ShipmentDAO {
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement readStatement;
    private PreparedStatement fetchCreatedShipmentSt;
    private String readQuery = "SELECT * FROM shipment";
    String fetchCreatedShipment = "SELECT ShipmentID FROM Shipment ORDER BY ShipmentID DESC LIMIT 1";
    
    public ShipmentDAO (Connection connection) throws SQLException {
        
        this.connect = connection;
        connection.setAutoCommit(true);
        statement = connection.createStatement();
        readStatement = connection.prepareStatement(readQuery);
        fetchCreatedShipmentSt = connection.prepareStatement(fetchCreatedShipment);
        
    }
    
    //Create - Create Shipment Record for Registered User
    public void createShipment(int customerID, String shipmentAddress, String shipmentMethod, String shipmentStatus, Date shipmentDate) throws SQLException {
        
        
        PreparedStatement createQuery = connect.prepareStatement("INSERT INTO shipment(UserID, ShipmentAddress, ShipmentMethod, ShipmentStatus, ShipmentDate) VALUES(?,?,?,?,?)");
        createQuery.setInt(1, customerID);
        createQuery.setString(2, shipmentAddress);
        createQuery.setString(3, shipmentMethod);
        createQuery.setString(4, shipmentStatus);
        createQuery.setDate(5, new java.sql.Date(calculateShipmentDate(shipmentDate).getTime()));
        
        createQuery.executeUpdate();
        System.out.println("Shipment Record Successfully Created!");
                
    }
    
    //Create - Create Shipment Record for Anonymous User
    public void createShipmentForAnonymousUser(String shipmentAddress, String shipmentMethod, String shipmentStatus, Date shipmentDate) throws SQLException {
        
        
        PreparedStatement createQuery = connect.prepareStatement("INSERT INTO shipment(ShipmentAddress, ShipmentMethod, ShipmentStatus, ShipmentDate) VALUES(?,?,?,?)");
        createQuery.setString(1, shipmentAddress);
        createQuery.setString(2, shipmentMethod);
        createQuery.setString(3, shipmentStatus);
        createQuery.setDate(4, new java.sql.Date(calculateShipmentDate(shipmentDate).getTime()));
        
        createQuery.executeUpdate();
        System.out.println("Shipment Record Successfully Created!");
                
    }
    
    //Calculates the Shipment Date (Date that Order is Dispatched)
    public Date calculateShipmentDate(Date currentDate) {
        currentDate.setTime(currentDate.getTime() - 36000000); //converts from UTC to AEST
        currentDate.setTime(currentDate.getTime() + 172800000); //Adds two days from the current date
        
        return currentDate;
    }
    
    //Fetch - Fetch the Shipment ID of the record that matches the address, method, status and date
    public int fetchShipment(String shipmentAddressInput, String ShipmentMethodInput, String shipmentStatusInput, Date shipmentDateInput) throws SQLException {
        PreparedStatement readsQuery = connect.prepareStatement("SELECT * FROM shipment WHERE SHIPMENTADDRESS=? AND SHIPMENTMETHOD=? AND SHIPMENTSTATUS=? AND SHIPMENTDATE=?");
        readsQuery.setString(1, shipmentAddressInput);
        readsQuery.setString(2, ShipmentMethodInput);
        readsQuery.setString(3, shipmentStatusInput);
        
        readsQuery.setDate(4, new java.sql.Date(shipmentDateInput.getTime()));
        
        ResultSet resultSet = readsQuery.executeQuery();
        
        if(resultSet.next()) {
           
            int shipmentID = resultSet.getInt(1);
            
            return shipmentID;
            
        } 
         
        return 0;
    }
    
    //Fetch - Finds the shipment record that matches the shipment ID
    public Shipment fetchShipmentByID(int shipmentID) throws SQLException {
        PreparedStatement readsQuery = connect.prepareStatement("SELECT * FROM shipment WHERE SHIPMENTID=?");
        readsQuery.setInt(1, shipmentID);
        
        ResultSet resultSet = readsQuery.executeQuery();
        
        if(resultSet.next()) {
            String shipmentAddress = resultSet.getString(2);
            String shipmentMethod = resultSet.getString(3);
            String shipmentStatus = resultSet.getString(4);
            Date shipmentEstDate = resultSet.getDate(5);
            
            
            Shipment shipment = new Shipment(shipmentID, 0, shipmentAddress, shipmentMethod, shipmentStatus, shipmentEstDate);
            
            return shipment;
        }
        
        return null;
        
    }
    
    //Fetch - gets all shipments made by a specific customer
    public ArrayList<Shipment> fetchShipmentFromACustomer(int customerID) throws SQLException {
        
        PreparedStatement readsQuery = connect.prepareStatement("SELECT * FROM shipment WHERE USERID=?");
        readsQuery.setString(1, String.valueOf(customerID));
        
        ResultSet resultSet = readsQuery.executeQuery();
        ArrayList<Shipment> shipmentRecords = new ArrayList<> ();
        
        while(resultSet.next()) {
            String shipmentAddress = resultSet.getString(2);
            String shipmentMethod = resultSet.getString(3);
            String shipmentStatus = resultSet.getString(4);
            Date shipmentEstDate = resultSet.getDate(5);
           
            int shipmentID = resultSet.getInt(1);
            int userID = resultSet.getInt(6);
            
            Shipment shipment = new Shipment(shipmentID, userID, shipmentAddress, shipmentMethod, shipmentStatus, shipmentEstDate);
            
            shipmentRecords.add(shipment);
        }
        
        return shipmentRecords;
        
    }
    
    //Fetch - finds the shipment that matches the customer id, shipment id and shipment date
    public ArrayList<Shipment> fetchShipmentByFilter(int customerID, int shipmentIDAsInput, String shipmentDate) throws SQLException {
        
        PreparedStatement readQuery = connect.prepareStatement("SELECT * FROM shipment WHERE USERID=? AND SHIPMENTID=? AND SHIPMENTDATE=?");
        readQuery.setString(1, String.valueOf(customerID));
        readQuery.setString(2, String.valueOf(shipmentIDAsInput));
        readQuery.setString(3, shipmentDate);
        
        ResultSet resultSet = readQuery.executeQuery();
        ArrayList<Shipment> shipmentRecords = new ArrayList<> ();
        
        while(resultSet.next()) {
            String shipmentAddress = resultSet.getString(2);
            String shipmentMethod = resultSet.getString(3);
            String shipmentStatus = resultSet.getString(4);
            Date shipmentEstDate = resultSet.getDate(5);
           
            int shipmentID = resultSet.getInt(1);
            int userID = resultSet.getInt(6);
            
            Shipment shipment = new Shipment(shipmentID, userID, shipmentAddress, shipmentMethod, shipmentStatus, shipmentEstDate);
            
            shipmentRecords.add(shipment);
        }
        
        return shipmentRecords;
    }
    
    //Fetch - finds the shipment that matches the customer ID and shipment ID
    public Shipment fetchShipmentByFilter(int customerID, int shipmentIDAsInput) throws SQLException {
        
        PreparedStatement readQuery = connect.prepareStatement("SELECT * FROM shipment WHERE USERID=? AND SHIPMENTID=?");
        readQuery.setString(1, String.valueOf(customerID));
        readQuery.setString(2, String.valueOf(shipmentIDAsInput));
        ResultSet resultSet = readQuery.executeQuery();
        
         if(resultSet.next()) {
            String shipmentAddress = resultSet.getString(2);
            String shipmentMethod = resultSet.getString(3);
            String shipmentStatus = resultSet.getString(4);
            Date shipmentEstDate = resultSet.getDate(5);
           
            int shipmentID = resultSet.getInt(1);
            int userID = resultSet.getInt(6);
            
            Shipment shipment = new Shipment(shipmentID, userID, shipmentAddress, shipmentMethod, shipmentStatus, shipmentEstDate);
            
            return shipment;
            
        } 
         
        return null;
        
        
    }
    
    //Update - update shipment details
    public void updateShipmentAddressAndMethod(int shipmentID, String shipmentAddress, String shipmentMethod) throws SQLException {
        
        PreparedStatement updateQuery = connect.prepareStatement("UPDATE shipment SET SHIPMENTADDRESS=? , SHIPMENTMETHOD=? WHERE SHIPMENTID=?");
        updateQuery.setString(1, shipmentAddress);
        updateQuery.setString(2, shipmentMethod);
        updateQuery.setString(3, String.valueOf(shipmentID));
        updateQuery.executeUpdate();
        
        System.out.println("Shipment record is successfully updated");
        
        
    }
    
    //Delete - delete shipment record
    public void deleteShipment(int shipmentID) throws SQLException {
            
            String deleteQuery = "DELETE FROM shipment WHERE SHIPMENTID=" + shipmentID;
            statement.executeUpdate(deleteQuery);
            
            System.out.println("Shipment record is successfully deleted");
    }
    
   //Update - update the shipment status to cancelled (method is called when user deletes their account)
   public void updateShipmentStatus(int userID) throws SQLException {
        PreparedStatement updateQuery = connect.prepareStatement("UPDATE shipment SET SHIPMENTSTATUS=? WHERE SHIPMENTSTATUS<>'Delivered' AND USERID=?");
        updateQuery.setString(1, "Cancelled");
        updateQuery.setInt(2, userID);
        updateQuery.executeUpdate();
   }
    
    public int fetchShipmentID() throws SQLException {       
        ResultSet rs = fetchCreatedShipmentSt.executeQuery();
        int shipmentID = 0;
        if(rs.next()){
            shipmentID = rs.getInt(1);
        }
        System.out.println("ShipmentID fetched sucessfully");  
        return shipmentID;
    }

    
}
