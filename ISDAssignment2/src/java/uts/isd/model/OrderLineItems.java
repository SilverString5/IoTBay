/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;

/**
 *
 * @author lorinchanel
 */
public class OrderLineItems {

    private int orderID;
    private int productID;
    private int productQuantity;
    private int subTotal;
    
    public OrderLineItems(){
    }
    
    public OrderLineItems(int orderID, int productID, int productQuantity, int subTotal){
        this.orderID = orderID;
        this.productID = productID;
        this.productQuantity = productQuantity;
        this.subTotal = subTotal;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }


    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
