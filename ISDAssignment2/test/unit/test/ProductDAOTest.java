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
    
    
    //User Story: 
    @Test
    public void testFetchAllProducts() throws SQLException {
        ArrayList<Product> allProducts = manager.fetchAllProducts();
        assertEquals(allProducts.size(), 21);
    }
    
    
    //User Story: 
    @Test
    public void testNonFilteredProducts() throws SQLException {
        ArrayList<Product> filteredProducts = manager.fetchFilteredProducts("","");
        assertEquals(filteredProducts.size(), 21);
    }
    
    
    //User Story: 
    @Test
    public void testFilteredProduct() throws SQLException {
        ArrayList<Product> filteredProducts = manager.fetchFilteredProducts("D","T");
        assertEquals(filteredProducts.size(), 2);
        
        //fetch the 5th product in the filtered list
        Product raspberrySensor = filteredProducts.get(1);
        
        //check its value match to the database
        assertEquals(raspberrySensor.getProductID(), 2);
        assertEquals(raspberrySensor.getProductName() , "DHT22");
        assertEquals(raspberrySensor.getProductType(), "Temperature / Humidity / Air Pressure / Gas");
        assertEquals(raspberrySensor.getProductUnitPrice(),4,0.001);
        assertEquals(raspberrySensor.getProductDetails(), "DHT22 measure all humidity ranges from 0-100% with an accuracy of 2%.");
        assertEquals(raspberrySensor.getProductInStock(), 50);
       
    }
    
    
    //User Story: 
    @Test
    public void testGetProduct() throws SQLException {
        
        Product testProduct = manager.getProduct(18);
        // ID: 18, ProductName: 2.4 GHz NRF24L01+ Modul, ProductType: Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth
        assertEquals(testProduct.getProductID(), 18);
        assertEquals(testProduct.getProductName() , "2.4 GHz NRF24L01+ Modul");
        assertEquals(testProduct.getProductType(), "Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth");
        assertEquals(testProduct.getProductUnitPrice(),3.5,0.001);
        assertEquals(testProduct.getProductDetails(), "An advanced 2.4 GHz frequency module that supports larger data transmission at once.");
        assertEquals(testProduct.getProductInStock(), 50);
    
    }
    
    
    //User Story:        
    @Test
    public void testCreateAProduct() throws SQLException {
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
        manager.deleteProduct(raspberryPi.getProductID());
        
    }
    
    
    //User Story:
    @Test
    public void testDeleteAProduct() throws SQLException {
        //created and add testing product to the database
        manager.createProduct("Raspberry Pi Compute Module 3", "Raspberry Pi", 50, "Rasperry pi compute module 3 kits", 50);
        
        //fetch the list of all product, there are originally 21 products, after adding now it should be 22 products
        ArrayList<Product> products = manager.fetchAllProducts();
        assertEquals(products.size(), 22);
        
        //get and check created testing product
        Product raspberryPi = products.get(21);
        assertEquals(raspberryPi.getProductName(), "Raspberry Pi Compute Module 3");
        
        //delete the testing product by product ID, now call the fetchAllProducts again
        manager.deleteProduct(raspberryPi.getProductID());
        
        //after deletion there should be 21 products
        products = manager.fetchAllProducts();
        assertEquals(products.size(), 21);
        
        //after deletion there is no product with name "Raspberry Pi Compute Module 3" in the database
        products = manager.fetchFilteredProducts("Raspberry Pi Compute Module 3", "Raspberry Pi");
        assertEquals(products.size(), 0);
    }
    
    
    //User Story:
    @Test
    public void testUpdateAProduct() throws SQLException {
        //create testing product to be updated
        manager.createProduct("Test updating", "Testing", 23, "Testing record", 72);
        ArrayList<Product> products = manager.fetchFilteredProducts("Test updating","Testing");
        
        Product testingProduct = products.get(0);
        assertEquals(testingProduct.getProductName(), "Test updating");
        assertEquals(testingProduct.getProductType(),"Testing");
        assertEquals(testingProduct.getProductUnitPrice(),23,0.001);
        assertEquals(testingProduct.getProductDetails(),"Testing record");
        assertEquals(testingProduct.getProductInStock(),72);
        
        //Update and get updated testing product
        manager.updateProduct("IDK", "Type Maybe?", 2.8, "It works?", 32, testingProduct.getProductID());
        Product updatedProduct = manager.getProduct(testingProduct.getProductID());
        assertEquals(updatedProduct.getProductName(), "IDK");
        assertEquals(updatedProduct.getProductType(),"Type Maybe?");
        assertEquals(updatedProduct.getProductUnitPrice(),2.8,0.001);
        assertEquals(updatedProduct.getProductDetails(),"It works?");
        assertEquals(updatedProduct.getProductInStock(),32);
        
        //Delete to ensure previous test won't be affected
        manager.deleteProduct(updatedProduct.getProductID());
        
    }
    
    
}
