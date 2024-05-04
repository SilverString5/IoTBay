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
    public void testShipmentConnector() throws SQLException {
        ArrayList<Shipment> shipment = shipmentDAO.fetchShipment();
        for(Shipment index : shipment) {
            System.out.println(index.getShipmentAddress());
        }
        assertEquals(shipment.size(), 1);
    }
    
    @Test
    public void testCreateShipment() throws SQLException {
        shipmentDAO.createShipment(1, "UNSW High St, Kensington NSW 2052 UNSW High St", "Express", "Dispatched", new Date(2024, 4, 8));

        assertEquals(shipmentDAO.fetchShipment().size(), 2);
        
    }
    
    @Test
    public void testFecthShipmentFromACustomer() throws SQLException {
        shipmentDAO.fetchShipmentFromACustomer(1);

        assertEquals(shipmentDAO.fetchShipment().size(), 2);
        
    }
    
    @Test
    public void testUpdateShipmentAddress() throws SQLException {
        shipmentDAO.updateShipmentAddress(2, "15 Broadway Ultimo, NSW 2007");

        
    }
    
    @Test
    public void testUpdateShipmentMethod() throws SQLException {
        shipmentDAO.updateShipmentAddress(2, "Express");
        
    }
    
    
    @Test
    public void testDeleteShipment() throws SQLException {
        shipmentDAO.deleteShipment(21);
    }
    

}
