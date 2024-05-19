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

//Made into a child class in an attempt to represent subtype in ERD
public class Customer extends User implements Serializable {
    
    private int customerID;
    
    public Customer(int customerID){
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
}
