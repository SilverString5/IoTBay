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
public class UserAccessLog implements Serializable {
    
    private int accessLogId;
    private int userID;
    private String browserType;
    private String eventType;
    private String sessionCreationTime;
    private String sessionEndTime;
    
    public UserAccessLog(int accessLogId, int userID, String browserType, String eventType, String sessionCreationTime, String sessionEndTime){
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

    public String getSessionCreationTime() {
        return sessionCreationTime;
    }

    public void setSessionCreationTime(String sessionCreationTime) {
        this.sessionCreationTime = sessionCreationTime;
    }
    
    public String getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(String sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    
}