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
        
        createQuery.executeUpdate();
        System.out.println("Shipment Record Successfully Created!");
                
    }
    
    public Date calculateShipmentDate(Date currentDate) {
        currentDate.setTime(currentDate.getTime() - 36000000);
        System.out.println(currentDate);
        currentDate.setTime(currentDate.getTime() + 172800000);
        System.out.println(currentDate);
        
        return currentDate;
    }
    
    //get all shipments made by a specific customer
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
    
    public Shipment fetchShipmentByFilter(int customerID, int shipmentIDAsInput) throws SQLException {
        
        PreparedStatement readQuery = connect.prepareStatement("SELECT * FROM shipment WHERE USERID=? AND SHIPMENTID=?");
        System.out.println(readQuery);
        readQuery.setString(1, String.valueOf(customerID));
        readQuery.setString(2, String.valueOf(shipmentIDAsInput));
        System.out.println(readQuery);
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
    
    //update shipment details
    public void updateShipmentAddressAndMethod(int shipmentID, String shipmentAddress, String shipmentMethod) throws SQLException {
        
        PreparedStatement updateQuery = connect.prepareStatement("UPDATE shipment SET SHIPMENTADDRESS=? , SHIPMENTMETHOD=? WHERE SHIPMENTID=?");
        updateQuery.setString(1, shipmentAddress);
        updateQuery.setString(2, shipmentMethod);
        updateQuery.setString(3, String.valueOf(shipmentID));
        updateQuery.executeUpdate();
        
        System.out.println("Shipment record is successfully updated");
        
        
    }
    
    //delete
    public void deleteShipment(int shipmentID) throws SQLException {
            
            String deleteQuery = "DELETE FROM shipment WHERE SHIPMENTID=" + shipmentID;
            statement.executeUpdate(deleteQuery);
            
            System.out.println("Shipment record is successfully deleted");
    }
    
    /*
    private Shipment findShipmentDetail (int shipmentIDAsInput) throws SQLException {
        
        PreparedStatement readQuery = connect.prepareStatement("SELECT * FROM shipment WHERE SHIPMENTID=?");
        readQuery.setString(1, String.valueOf(shipmentIDAsInput));
        
        ResultSet resultSet = readQuery.executeQuery();
        
        if(!resultSet.next()){
            return null;
            
        } else {
            String shipmentAddress = resultSet.getString(2);
            String shipmentMethod = resultSet.getString(3);
            String shipmentStatus = resultSet.getString(4);
            Date shipmentEstDate = resultSet.getDate(5);
           
            int shipmentID = resultSet.getInt(1);
            int userID = resultSet.getInt(6);
            
            Shipment shipment = new Shipment(shipmentID, userID, shipmentAddress, shipmentMethod, shipmentStatus, shipmentEstDate);
            
            return shipment;
        }

        
    }*/

    
}
