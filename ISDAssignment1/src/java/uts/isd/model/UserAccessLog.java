/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author notba
 */
public class UserAccessLog implements Serializable {
    
    private int accessLogID;
    private int userID;
    private Date accessLogDate;
    private Time loginTime;
    private Time logoutTime;

    
    public UserAccessLog(int accessLogID, int userID, Date accessLogDate, Time loginTime, Time logoutTime){
        this.accessLogID = accessLogID;
        this.userID = userID;
        this.accessLogDate = accessLogDate;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;


    }

     public UserAccessLog(){
     }

    public Date getAccessLogDate() {
        return accessLogDate;
    }

    public void setAccessLogDate(Date accessLogDate) {
        this.accessLogDate = accessLogDate;
    }

    public Time getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Time loginTime) {
        this.loginTime = loginTime;
    }

    public Time getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Time logoutTime) {
        this.logoutTime = logoutTime;
    }
    
    public int getAccessLogID() {
        return accessLogID;
    }

    public void setAccessLogID(int AccessLogId) {
        this.accessLogID = AccessLogId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    


    
}
