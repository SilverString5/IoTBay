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
	private String readQuery = "SELECT * FROM Product";
	private String updateQuery = "UPDATE Product SET ProductName = ?, ProductType= ?, ProductUnitPrice= ?, ProductDetails= ?, ProductInStock= ? WHERE ProductID=?";
	private String deleteQuery = "DELETE FROM Product WHERE ProductID= ? ";
        
        public ProductDAO(Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
	}
        
        
        
        // Read Operation: read a list of all products with its name, type, price, details
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
                    
                    Product product = new Product(ProductID, ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock);
                    products.add(product);
            }
            
            
            
            return products;
	}
        
        //Filter and read a list of products by name and type, return all products when filtering empty string
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

                Product product = new Product(ProductID, ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock);
                products.add(product);
            }
            return products;
        }
        
        //Get book for update
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
                product = new Product(ProductID, ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock);
            }
            
            return product;
        }
        
        //Create Operation: create a product
	public void createProduct(String productName, String productType, double productUnitPrice, String productDetails, int productInStock) throws SQLException {
		String columns = "INSERT INTO Product (ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock)";
		String values = "VALUES('" + productName + "','" + productType + "'," + productUnitPrice + ",'" + productDetails + "'," + productInStock +" )";
		st.executeUpdate(columns + values);
                System.out.println("1 row successfully created");
	}
        
        
        
        // Update Operation: update Name, Type, Price, Detail, Stock of a product
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
        
        
        
        // Delete Operation: delete a product by id
	public void deleteProduct(int ProductID) throws SQLException {
		//ensure ID can be passed down by the read list
                deleteSt.setString(1, Integer.toString(ProductID));
		
                deleteSt.executeUpdate();
		System.out.println("1 row deleted successfuly");
	}
        
      
        
    
}
