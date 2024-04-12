/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author lorinchanel
 */
public class ApplicationAccessLogs implements Serializable {

    private int accessLogId;
    private String browserType;
    private String eventType;
    private Date timeStamp;
    private int sessionNum;

    
    public ApplicationAccessLogs(int accessLogId, String browserType, String eventType, Date timeStamp, int sessionNum){
        this.accessLogId = accessLogId;
        this.browserType = browserType;
        this.eventType = eventType;
        this.timeStamp = timeStamp;
        this.sessionNum = sessionNum;
    }
    
    public int getAccessLogId() {
        return accessLogId;
    }

    public void setAccessLogId(int AccessLogId) {
        this.accessLogId = AccessLogId;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getSessionNum() {
        return sessionNum;
    }

    public void setSessionNum(int sessionNum) {
        this.sessionNum = sessionNum;
    }
   
    
}
