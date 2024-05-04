/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;

/**
 *
 * @author lorinchanel
 */

//Made into a child class in an attempt to represent subtype in ERD
public class Staff extends User implements Serializable {

    private int staffID;
    private String position;
    
    
    public Staff(int staffID, String position){
        this.staffID = staffID;
        this.position = position;
    }
    
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}
