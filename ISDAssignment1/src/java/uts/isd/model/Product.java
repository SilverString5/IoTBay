package uts.isd.model;
import java.io.Serializable;


public class Product implements Serializable{
    
    private int productID;
    private String productName;
    private double productUnitPrice;
    private String productDetail;
    private int productInStock;
    
    public Product(int productID, String productName, double productUnitPrice, String productDetail, int productInStock){
      this.productID = productID;
      this.productName = productName;
      this.productUnitPrice = productUnitPrice;
      this.productDetail = productDetail;
      this.productInStock = productInStock;
      
    }
    
    public void setProductID(int productID){
        this.productID = productID;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public void setUnitPrice(double productUnitPrice){
        this.productUnitPrice = productUnitPrice;
    }
    
    public void setProductDetail(String productDetail){
        this.productDetail = productDetail;
    }
    
    public void setProductInStock(int productInStock) {
        this.productInStock = productInStock;
    }
    
    public int getProductID(){
        return this.productID;
    }
    
    public String setProductName(){
        return this.productName;
    }
    
    public double setUnitPrice(){
        return this.productUnitPrice;
    }
    
    public String setProductDetail(){
        return this.productDetail;
    }
    
    public int setProductInStock() {
        return this.productInStock;
    }
    
    
}
