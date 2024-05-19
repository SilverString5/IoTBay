//NOTE: This class was originally going to be used to save payment lists in the controllers more efficiently. However, this method
//was discarded, and ArrayLists instead of Payments were declared in the controller instead.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;
import java.util.ArrayList;



/**
 *
 * @author sienn
 */
public class Payments implements Serializable {
    private ArrayList<Payment> listOfCustomerPayments = new ArrayList<>();
    
    public Payments(ArrayList<Payment> listOfCustomerPayments) {
        this.listOfCustomerPayments = listOfCustomerPayments;
    }
    
    public ArrayList<Payment> getListOfCustomerPayments() {
        return listOfCustomerPayments;
    }
    
    public void setListOfCustomerPayments(ArrayList <Payment> listOfCustomerPayments) {
        this.listOfCustomerPayments = listOfCustomerPayments;
    }
    
}
