package uts.isd.model;
import java.util.*;
import java.time.*;
import java.io.Serializable;

public class Payment implements Serializable{
    private int paymentID;
    private String paymentMethod;
    private Date paymentDate;
    private int paymentCardNumber;
    private int paymentCVC;
    
    public Payment(int paymentID, String paymentMethod, Date paymentDate, int paymentCardNumber, int paymentCVC){
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentCardNumber = paymentCardNumber;
        this.paymentCVC = paymentCVC;
        
    }
    
    public void setPaymentID(int paymentID){
        this.paymentID = paymentID;
    }
    
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    
    public void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }
    
    public void setPaymentCardNumber(int paymentCardNumber){
        this.paymentCardNumber = paymentCardNumber;
    }
    
    public void setPaymentCVC(int paymentCVC){
        this.paymentCVC = paymentCVC;
    }
    
    public int getPaymentID(){
        return this.paymentID;
    }
    
    public String getPaymentMethod(){
        return this.paymentMethod;
    }
    
    public Date getPaymentDate(){
        return this.paymentDate;
    }
    
    public int getPaymentCardNumber(){
        return this.paymentCardNumber;
    }
    
    public int getPaymentCVC(){
        return this.paymentCVC;
    }
    
}

