/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;

/**
 *
 * @author notba
 */
public class User implements Serializable {
    private String email;
    private String name;
    private String phone;
    private String password;   
    private String address;
<<<<<<< Updated upstream


=======
    private String DOB;
    private String gender;
>>>>>>> Stashed changes


    
    public User(){}
    
    public User(int userID, String email, String name, String phone, String password, String address, String DOB, String gender){
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address=address;
        this.DOB=DOB;
        this.gender=gender;


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
<<<<<<< Updated upstream
=======

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
>>>>>>> Stashed changes
}
