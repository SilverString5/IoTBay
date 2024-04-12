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
public class CustomerHistoryLogs implements Serializable {
    
    private int historyLogId;
    private String actionPerformed;
    private String actionDetail;
    private Date timeStamp;
    
    public CustomerHistoryLogs(int historyLogId, String actionPerformed, String actionDetail, Date timeStamp){
        this.historyLogId = historyLogId;
        this.actionPerformed = actionPerformed;
        this.actionDetail = actionDetail;
        this.timeStamp = timeStamp;
    }

    public int getHistoryLogId() {
        return historyLogId;
    }

    public void setHistoryLogId(int HistoryLogId) {
        this.historyLogId = HistoryLogId;
    }
    
    public String getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(String actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    public String getActionDetail() {
        return actionDetail;
    }

    public void setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
    
}
