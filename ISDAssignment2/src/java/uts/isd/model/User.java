/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;
/**
 *
 * @author notba
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author notba
 */
public class User implements Serializable {
    private int userID;
    private String userEmail;
    private String userFullName;
    private String userPhone;
    private String userPassword;   
    private String userAddress;
    private Date userDOB;
    private String userGender;
    private String userType;


    
    public User(){}
    
    public User(int userID, String userEmail, String userPassword, String userFullName, String userPhone,  String userAddress, Date userDOB, String userGender, String userType){
        this.userID=userID;
        this.userEmail = userEmail;
        this.userFullName = userFullName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userAddress=userAddress;
        this.userDOB=userDOB;
        this.userGender=userGender;
        this.userType=userType;
      


    }

    public void setUserID(int userID){
        this.userID=userID;
    }
    public int getUserID(){
        return this.userID;
    }
    
    public void setEmail(String userEmail){
        this.userEmail = userEmail;
    }
    
    public String getEmail(){
        return this.userEmail;
    }
    
        public String getName() {
        return this.userFullName;
    }

    public void setName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getPhone() {
        return this.userPhone;
    }

    public void setPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return this.userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getAddress(){
    return this.userAddress;
    }

    public void setAddress(String userAddress){
    this.userAddress=userAddress;
    }   

    public Date getuserDOB() {
        return userDOB;
    }

    public void setuserDOB(Date userDOB) {
        this.userDOB = userDOB;
    }

    public String getGender() {
        return userGender;
    }

    public void setGender(String userGender) {
        this.userGender = userGender;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    
}