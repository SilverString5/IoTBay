/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
/**
 *
 * @author jijianlan
 */
public class User implements Serializable {
    private String email;
    private String name;
    private String phone;
    private String password;
    private String gender;
    private String favCol;
    
    public User(){
    }
    
    public User(String email, String name, String phone,String password,String gender, String favCol){
        this.email = email;
        this.favCol = favCol;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    public void setFavCol(String favCol){
        this.favCol = favCol;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getEmail(){
        return email;
    }
    public String getFavCol(){
        return favCol;
    }
    public String getGender(){
        return gender;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getPhone(){
        return phone;
    }
    
}
