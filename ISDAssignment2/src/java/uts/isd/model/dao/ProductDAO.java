/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import uts.isd.model.Product;
/**
 *
 * @author jijianlan
 */
public class ProductDAO {
    
    	private Statement st;
	private PreparedStatement readSt;
	private PreparedStatement updateSt;
	private PreparedStatement deleteSt;
        private PreparedStatement updateStockSt;
        private PreparedStatement fetchStockSt;
	private String readQuery = "SELECT * FROM Product";
	private String updateQuery = "UPDATE Product SET ProductName = ?, ProductType= ?, ProductUnitPrice= ?, ProductDetails= ?, ProductInStock= ? WHERE ProductID= ?";
	private String deleteQuery = "DELETE FROM Product WHERE ProductID= ? ";
        private String updateStockQuery = "UPDATE Product SET ProductInStock= ? WHERE ProductID= ?";
        private String fetchStock = "SELECT ProductInStock FROM Product WHERE ProductID=?";
        
        public ProductDAO(Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
                updateStockSt = connection.prepareStatement(updateStockQuery);
                fetchStockSt = connection.prepareStatement(fetchStock);
	}
        
        
        
        // Read Operation: read a list of all devices with its image, id, name, type, price, details
        public ArrayList<Product> fetchAllProducts() throws SQLException {
            
            ResultSet rs = readSt.executeQuery();
            
            ArrayList<Product> products = new ArrayList();

            while (rs.next()) {
                    int ProductID = rs.getInt(1);
                    String ProductName = rs.getString(2);
                    String ProductType = rs.getString(3);
                    double ProductUnitPrice = rs.getDouble(4);
                    String ProductDetails = rs.getString(5);
                    int ProductInStock = rs.getInt(6);
                    String ProductImg = rs.getString(7);
                    
                    
                    Product product = new Product(ProductID, ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock, ProductImg);
                    products.add(product);
            }
            
            
            
            return products;
	}
        
        //Read Operation: Filter and read a list of devices by name and type
        //return all devices when filtering empty string
        public ArrayList<Product> fetchFilteredProducts(String productName, String productType) throws SQLException {
            
            String filteringQuery = "SELECT * FROM Product"+" WHERE ProductName LIKE '"+productName+"%' AND ProductType LIKE '"+productType+"%'";
            ResultSet rs = st.executeQuery(filteringQuery);
            
            ArrayList<Product> products = new ArrayList();
            
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString(2);
                String ProductType = rs.getString(3);
                double ProductUnitPrice = rs.getDouble(4);
                String ProductDetails = rs.getString(5);
                int ProductInStock = rs.getInt(6);
                String ProductImg = rs.getString(7);

                Product product = new Product(ProductID, ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock, ProductImg);
                products.add(product);
            }
            return products;
        }
        
        //Update Operation: return an existing device by its ID
        //Hence update the device by it's data
        public Product getProduct(int productID) throws SQLException{
            String getBookQuery = "SELECT * FROM Product WHERE ProductID = "+ productID;
            ResultSet rs = st.executeQuery(getBookQuery);
            Product product = null;
            
            while(rs.next()) {
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString(2);
                String ProductType = rs.getString(3);
                double ProductUnitPrice = rs.getDouble(4);
                String ProductDetails = rs.getString(5);
                int ProductInStock = rs.getInt(6);
                String ProductImg = rs.getString(7);
                product = new Product(ProductID, ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock, ProductImg);
            }
            
            return product;
        }
        
        //Create Operation: create a device by input name, type, price, details and stock
	public void createProduct(String productName, String productType, double productUnitPrice, String productDetails, int productInStock) throws SQLException {
		String columns = "INSERT INTO Product (ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock)";
		String values = "VALUES('" + productName + "','" + productType + "'," + productUnitPrice + ",'" + productDetails + "'," + productInStock +" )";
		st.executeUpdate(columns + values);
                System.out.println("1 row successfully created");
	}
        
        
        
        //Update Operation: update a device by input name, type, price, detail and stock
	public void updateProduct(String productName, String productType, double productUnitPrice, String productDetails, int productInStock, int ProductID) throws SQLException {
		//ensure the update form has prefilled data from the database
                updateSt.setString(1, productName);
		updateSt.setString(2, productType);
		updateSt.setString(3, Double.toString(productUnitPrice));
		updateSt.setString(4, productDetails);
                updateSt.setString(5, Integer.toString(productInStock));
                updateSt.setString(6, Integer.toString(ProductID));
                
		updateSt.executeUpdate();
		System.out.println("1 row updated successfuly");
	}
        
        
        
        //Delete Operation: delete a device by it's id
	public void deleteProduct(int ProductID) throws SQLException {
		
                deleteSt.setString(1, Integer.toString(ProductID));
		
                deleteSt.executeUpdate();
		System.out.println("1 row deleted successfuly");
	}
        
        //Update Operation: specific for add to cart feature in ordering
        //the productID is passdown by postmethod and request object
        //the stockVolumn is the stock that has been manipulated in the ordering servlet
        public void updateStock(int productID, int stockVolumn) throws SQLException{
            
            updateStockSt.setString(1, Integer.toString(stockVolumn));
            updateStockSt.setString(2, Integer.toString(productID));
            updateStockSt.executeUpdate();
            System.out.println("1 row updated successfuly");

        }
        
        public HashMap<Integer, Integer> fetchStock () throws SQLException {
            HashMap<Integer, Integer> map = new HashMap();
            ResultSet rs = readSt.executeQuery();
            while(rs.next()){
                map.put(rs.getInt(1), rs.getInt(6));
            }
            return map;
        }
               
        public int fetchSingleStock (int productID) throws SQLException {
            fetchStockSt.setInt(1, productID);
            ResultSet rs = fetchStockSt.executeQuery();
            int productStock = 0;
            while(rs.next()){
                productStock = rs.getInt(1);
            }
            return productStock;
        }

}
