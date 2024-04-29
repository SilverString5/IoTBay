/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author notba
 */

//Attempt to implement as superclass since it is a super type in ERD
public class User implements Serializable {

    private int userID;
    private String email;
    private String name;
    private String phone;
    private String password;   
    private String address;
    private Date DOB;
    private boolean gender;
    
    public User(){}
    
    public User(String email, String name, String phone, String password, String address){
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address=address;

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return this.email;
    }
    
        public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress(){
    return this.address;
    }

    public void setAddress(String address){
    this.address=address;
    }   

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
    
}
