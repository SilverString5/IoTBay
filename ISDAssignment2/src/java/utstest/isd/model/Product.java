package uts.isd.model;
import java.io.Serializable;


public class Product implements Serializable{
    
    private int productID;
    private String productName;
    private String productType;
    private double productUnitPrice;
    private String productDetails;
    private int productInStock;
    
    public Product(){
    }
    
    public Product(int productID, String productName, String productType, double productUnitPrice, String productDetails, int productInStock){
      this.productID = productID;
      this.productName = productName;
      this.productType = productType;
      this.productUnitPrice = productUnitPrice;
      this.productDetails = productDetails;
      this.productInStock = productInStock;
      
    }
    
    public void setProductID(int productID){
        this.productID = productID;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public void setProductType(String productType){
        this.productType = productType;
    }
    
    public void setProductUnitPrice(double productUnitPrice){
        this.productUnitPrice = productUnitPrice;
    }
    
    public void setProductDetails(String productDetails){
        this.productDetails = productDetails;
    }
    
    public void setProductInStock(int productInStock) {
        this.productInStock = productInStock;
    }
    
    public int getProductID(){
        return this.productID;
    }
    
    public String getProductName(){
        return this.productName;
    }
    
    public String getProductType(){
        return this.productType;
    }
    
    public double getProductUnitPrice(){
        return this.productUnitPrice;
    }
    
    public String getProductDetails(){
        return this.productDetails;
    }
    
    public int getProductInStock() {
        return this.productInStock;
    }
    
    
}
