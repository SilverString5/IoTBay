/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;
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




    
    public User(){}
    
    public User(String email, String name, String phone, String password, String address){
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address=address;

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
}
