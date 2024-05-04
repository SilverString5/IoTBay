/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import uts.isd.model.UserAccessLog;
import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author notba
 */
public class AccessLogDAO {
    private Statement st;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement filterSt;
    private String readQuery = "SELECT * FROM accesslogs WHERE userID=?";
    private String filterQuery = "SELECT * from accesslogs WHERE userID=? AND accessLogDate=?";
    private String updateQuery = "UPDATE accesslogs SET logoutTime=? WHERE accessLogID=?";



	public AccessLogDAO (Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
                filterSt = connection.prepareStatement(filterQuery);
                updateSt = connection.prepareStatement(updateQuery);
	}

        // Create Operation: create a user access log
	public void createUserAccessLog(int userID) throws SQLException {
		String columns = "INSERT INTO accesslogs(userID)";
		String values = "VALUES('" + userID+ "')";
		st.executeUpdate(columns + values);
	}

	// Read Operation: allow user to view all their access logs
	public ArrayList<UserAccessLog> viewAccessLogs(int userID) throws SQLException {
		readSt.setString(1, Integer.toString(userID));
                ResultSet rs = readSt.executeQuery();
                ArrayList<UserAccessLog> myAccessLogs = new ArrayList<>();
                while (rs.next()){
                int accessLogID = rs.getInt(1);
                Date accessLogDate = rs.getDate(3);
                Time loginTime = rs.getTime(4);
                Time logoutTime = rs.getTime(5);
                UserAccessLog accessLog = new UserAccessLog(userID, accessLogID, 
                accessLogDate, loginTime, logoutTime);
                myAccessLogs.add(accessLog);
                             }
          return myAccessLogs;
		
}

public void addLogoutTime(int accessLogID) throws SQLException{
Time logoutTime = new Time(System.currentTimeMillis());

updateSt.setTime(1, logoutTime);
updateSt.setInt(2, accessLogID);
int row = updateSt.executeUpdate();
System.out.println("row" + row + " updated successfuly");
}

        public ArrayList<UserAccessLog> filterAccessLogDate(int userID, String filterDate) throws SQLException {
                filterSt.setInt(1, userID);
                filterSt.setDate(2, Date.valueOf(filterDate));
                ArrayList<UserAccessLog> fltrAccessLogs = new ArrayList<>();
                ResultSet rs = filterSt.executeQuery();
                while (rs.next()){
                int accessLogID=rs.getInt(1);
                Time loginTime = rs.getTime(4);
                Time logoutTime = rs.getTime(5);
                UserAccessLog accessLog = new UserAccessLog (userID, accessLogID, Date.valueOf(filterDate),
                loginTime, logoutTime);
                fltrAccessLogs.add(accessLog);

                }   
                return fltrAccessLogs;

}

}



