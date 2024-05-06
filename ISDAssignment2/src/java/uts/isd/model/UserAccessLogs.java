/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author lorinchanel
 */
public class UserAccessLogs implements Serializable {
    
    private int accessLogId;
    private int userID;
    private String browserType;
    private String eventType;
    private Date sessionCreationTime;
    private Date sessionEndTime;
    
    public UserAccessLogs(){
    }
    
    public UserAccessLogs(int accessLogId, int userID, String browserType, String eventType, Date sessionCreationTime, Date sessionEndTime){
        this.accessLogId = accessLogId;
        this.userID = userID;
        this.browserType = browserType;
        this.eventType = eventType;
        this.sessionCreationTime = sessionCreationTime;
        this.sessionEndTime = sessionEndTime;

    }
    
    public int getAccessLogId() {
        return accessLogId;
    }

    public void setAccessLogId(int AccessLogId) {
        this.accessLogId = AccessLogId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getSessionCreationTime() {
        return sessionCreationTime;
    }

    public void setSessionCreationTime(Date sessionCreationTime) {
        this.sessionCreationTime = sessionCreationTime;
    }
    
    public Date getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(Date sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    
}
