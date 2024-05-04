/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.test;

import uts.isd.model.dao.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;

import uts.isd.model.*;
import uts.isd.model.dao.*;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Array;

import java.util.ArrayList;

/**
 *
 * @author jijianlan
 */
public class ProductDAOTest {
    
    private DBConnector connector;
    private Connection conn;
    private ProductDAO manager;
    
    public ProductDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        manager = new ProductDAO(conn);
    }
    
    @Test
    public void testAllProducts() throws SQLException {
        ArrayList<Product> allProducts = manager.fetchAllProducts();
        assertEquals(allProducts.size(), 21);
    }
    
    @Test
    public void testNonFilteredProducts() throws SQLException {
        ArrayList<Product> filteredProducts = manager.fetchFilteredProducts("","");
        assertEquals(filteredProducts.size(), 21);
    }
    
    @Test
    public void testFilteredProduct() throws SQLException {
        ArrayList<Product> filteredProducts = manager.fetchFilteredProducts("D","RA");
        assertEquals(filteredProducts.size(), 7);
        
        //fetch the 5th product in the filtered list
        Product raspberrySensor = filteredProducts.get(4);
        
        //check its value match to the database
        assertEquals(raspberrySensor.getProductID(), 19);
        assertEquals(raspberrySensor.getProductName() , "Radio controlled outlets / Power sockets");
        assertEquals(raspberrySensor.getProductType(), "Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth");
        assertEquals(raspberrySensor.getProductUnitPrice(),12.5,0.001);
        assertEquals(raspberrySensor.getProductDetails(), "Work with 433 MHz radio signals. By reading the codes of the remote control with a receiver on the Raspberry Pi, one can switch these radio sockets individually.");
        assertEquals(raspberrySensor.getProductInStock(), 50);
       
    }
    
    
            
    @Test
    public void testCreatingAProduct() throws SQLException {
        //Create and save records into database
        manager.createProduct("Raspberry Pi Compute Module 4 - 16GB eMMC, 2GB RAM", "Raspberry Pi", 65, "Raspberry Pi Compute Module 4 incorporates a quad-core ARM Cortex-A72 processor, dual video output, and a wide selection of other interfaces.", 50);
        
        //fetch the product through database by its name and type
        ArrayList<Product> products = manager.fetchFilteredProducts("Raspberry Pi Compute Module 4 - 16GB eMMC, 2GB RAM","Raspberry Pi");
        
        //Check the product returned has correct values that we saved into the data base
        Product raspberryPi = products.get(0);
        //ID is not testable because its not increment from the previous record's id, its increment by historically how many records got created
        assertEquals(raspberryPi.getProductName() , "Raspberry Pi Compute Module 4 - 16GB eMMC, 2GB RAM");
        assertEquals(raspberryPi.getProductType(), "Raspberry Pi");
        assertEquals(raspberryPi.getProductUnitPrice(),65,0.001);
        assertEquals(raspberryPi.getProductDetails(), "Raspberry Pi Compute Module 4 incorporates a quad-core ARM Cortex-A72 processor, dual video output, and a wide selection of other interfaces.");
        assertEquals(raspberryPi.getProductInStock(), 50);
        
        //Delete to ensure previous test won't be affected
        manager.deleteProductByName("Raspberry Pi Compute Module 4 - 16GB eMMC, 2GB RAM");
        
    }
    
    
    
    @Test
    public void testDeleteAProduct() throws SQLException {
        //created and add a product to the database for testing
        manager.createProduct("Raspberry Pi Compute Module 3", "Raspberry Pi", 50, "Rasperry pi compute module 3 kits", 50);
        
        //fetch the list of all product, there are originally 21 products, after adding now it should be 22 products
        ArrayList<Product> products = manager.fetchAllProducts();
        assertEquals(products.size(), 22);
        
        //get 22th product
        Product raspberryPi = products.get(21);
        
        //delete by productID of the 22th product, now call the fetchAllProducts again
        manager.deleteProduct(raspberryPi.getProductID());
        products = manager.fetchAllProducts();
        //after deletion there should be 21 products
        assertEquals(products.size(), 21);
    }
    
    
    
    @Test
    public void testUpdateAProduct() throws SQLException {
        //creaye a object to be updated
        ArrayList<Product> products = manager.fetchAllProducts();
    }
    
    
}
