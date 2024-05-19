package uts.isd.model;

import java.util.*;
import java.time.*;
import java.io.Serializable;

public class Payment implements Serializable{
    private int paymentID;
    private int customerID;
    
    private String paymentMethod;
    private Date expiryDate;
    private int paymentCardNumber;
    private int paymentCVC;
    
    //default constructor to initialise.
    public Payment() {
        
    }
    
    public Payment(int paymentID, String paymentMethod, Date expiryDate, int paymentCVC, int paymentCardNumber, int customerID){
        this.paymentID = paymentID;
        this.customerID = customerID;
        
        this.paymentMethod = paymentMethod;
        this.expiryDate = expiryDate;
        this.paymentCVC = paymentCVC;
        this.paymentCardNumber = paymentCardNumber;
        
    }
    
    public void setPaymentID(int paymentID){
        this.paymentID = paymentID;
    }
    
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    
    public void setExpiryDate(Date expiryDate){
        this.expiryDate = expiryDate;
    }
    
    public void setPaymentCardNumber(int paymentCardNumber){
        this.paymentCardNumber = paymentCardNumber;
    }
    
    public void setPaymentCVC(int paymentCVC){
        this.paymentCVC = paymentCVC;
    }
    
    //for getting the paymentID.
    public int getPaymentID(){
        return this.paymentID;
    }
    
    public String getPaymentMethod(){
        return this.paymentMethod;
    }
    
    public Date getExpiryDate(){
        return this.expiryDate;
    }
    
    public int getPaymentCardNumber(){
        return this.paymentCardNumber;
    }
    
    public int getPaymentCVC(){
        return this.paymentCVC;
    }
    
    //used for parameter for the PaymentDAO creation and deletion, as well as fetching methods.
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

