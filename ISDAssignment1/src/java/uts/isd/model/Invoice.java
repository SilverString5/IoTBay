package uts.isd.model;
import java.util.*;
import java.io.Serializable;

public class Invoice implements Serializable {
    
    private int invoiceID;
    private Date invoiceDate;
    private boolean paymentStatus;
    
    public Invoice(int invoiceID, Date invoiceDate, boolean paymentStatus){
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.paymentStatus = paymentStatus;
    }
    
    public void setInvoiceStatus(int invoiceID){
        this.invoiceID = invoiceID;
    }
    
    public void setInvoiceDate(Date invoiceDate){
        this.invoiceDate = invoiceDate;
    }
    
    public void setPaymentStatus(boolean paymentStatus){
        this.paymentStatus = paymentStatus;
    }
    
    public int getInvoiceStatus(){
        return this.invoiceID;
    }
    
    public Date getInvoiceDate(){
        return this.invoiceDate;
    }
    
    public boolean getPaymentStatus(){
        return this.paymentStatus;
    }
    
}
