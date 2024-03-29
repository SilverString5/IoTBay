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
    private String address;
    
    public User(){
    }
    
    public User(String email, String name, String phone,String password, String address){
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address=address;
    }
    
    public void setEmail(String email){
        this.email = email;
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

    public void setAddress(String address){
        this.address= address;
    }
    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
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
