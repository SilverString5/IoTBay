/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;
import java.io.Serializable;

/**
 *
 * @author lorinchanel
 */
public class Staff implements Serializable {

    private int staffID;
    private String email;
    private String name;
    private String phone;
    private String password;   
    private String address;
    
    
    public Staff(int staffID, String email, String name, String phone, String password, String address){
        this.staffID = staffID;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }
    
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
