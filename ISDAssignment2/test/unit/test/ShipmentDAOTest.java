/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

import uts.isd.model.dao.DBConnector; 
import uts.isd.model.dao.ShipmentDAO;
import uts.isd.model.Shipment;

import java.util.*;

/**
 *
 * @author lorinchanel
 */
public class ShipmentDAOTest {
    
    private DBConnector connector;
    private Connection connect;
    //private UserDAO userDAO;
    private ShipmentDAO shipmentDAO;
    
    public ShipmentDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        connect = connector.openConnection();
        //userDAO = new UserDAO(connect);
        shipmentDAO = new ShipmentDAO(connect);
        
    }
    
    @Test
    public void testConnection() throws SQLException {
        assertNotNull(connect);
    }
    
    
    @Test
    public void testFetchShipmentFromACustomer() throws SQLException {
        ArrayList<Shipment> shipment = shipmentDAO.fetchShipmentFromACustomer(8);
        for(Shipment index : shipment) {
            System.out.println(index.getShipmentAddress());
        }
    }
    
    @Test
    public void testFetchShipmentForFilter() throws SQLException {
        
        
        ArrayList<Shipment> shipment = shipmentDAO.fetchShipmentByFilter(17, 2, "2024-05-04");
        for(Shipment index : shipment) {
            System.out.println(index.getShipmentAddress());
        }
        
    }
    
    @Test
    public void testFetchShipment() throws SQLException { 
    
        Shipment shipment = shipmentDAO.fetchShipmentByFilter(19, 1);
        System.out.print(shipment.getShipmentAddress());
        
    }
    
    @Test
    public void testFetchShipmentID() throws SQLException {
        Date shipmentTestDate = new Date(2024 - 1900, 5 - 1, 7);
        int shipmentID = shipmentDAO.fetchShipment("24 Shirley Street, Maudsland QLD 4210", "Express", "Dispatched", shipmentTestDate);
        System.out.println("The shipment ID for this should be 1. The shipment ID received is " + shipmentID);
    }
    
    @Test
    public void testFetchShipmentByID() throws SQLException {
        Shipment shipment = shipmentDAO.fetchShipmentByID(1);
        System.out.println("For ShipmentID 1, the Shipment Address " + shipment.getShipmentAddress());
    }
    

    @Test
    public void testCreateShipment() throws SQLException {
        shipmentDAO.createShipment(1, "UNSW High St, Kensington NSW 2052 UNSW High St", "Express", "Dispatched", new Date());
        
    }
    
    @Test
    public void testUpdateShipmentAddressAndMethod() throws SQLException {
        shipmentDAO.updateShipmentAddressAndMethod(25, "123/267-319 Bulwara Road, Ultimo NSW 2007", "Standard");
    } 
    
    @Test
    public void testDeleteShipment() throws SQLException {
        shipmentDAO.deleteShipment(25);
    }
    
    @Test
    public void testUpdateShipmentStatus() throws SQLException {
        shipmentDAO.updateShipmentStatus(25);
    }

}
