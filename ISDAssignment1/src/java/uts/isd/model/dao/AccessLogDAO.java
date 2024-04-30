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


/**
 *
 * @author notba
 */
public class AccessLogDAO {
    private Statement st;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private String readQuery = "SELECT * FROM accesslogs WHERE userID=?";


	public AccessLogDAO (Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
	}

// Create Operation: create a user access log
	public void createUserAccessLog(int userID, String eventType, String sessionCreationTime, String sessionEndTime, String browserType) throws SQLException {
		String columns = "INSERT INTO accesslogs(userID, eventType, sessionCreationTime, sessionEndTime, browserType)";
		String values = "VALUES('" + userID + "','" + eventType + "','" + sessionCreationTime + "','" 
                + sessionEndTime + "','" + browserType + "')";
		st.executeUpdate(columns + values);
	}

	// Read Operation: allow user to view all their access logs
	public ArrayList<UserAccessLog> viewAccessLogs(int userID) throws SQLException {
		readSt.setString(1, Integer.toString(userID));
                ResultSet rs = readSt.executeQuery();
                ArrayList<UserAccessLog> myAccessLogs = new ArrayList<>();
                while (rs.next()){
                int accessLogID = rs.getInt(1);
                String eventType = rs.getString(3);
                String sessionCreationTime = rs.getString(4);
                String sessionEndTime = rs.getString(5);
                String browserType = rs.getString(6);
                UserAccessLog accessLog = new UserAccessLog(userID, accessLogID, 
                eventType, sessionCreationTime, sessionEndTime, browserType);
                myAccessLogs.add(accessLog);
                             }
          return myAccessLogs;
		
}


	/*// Fetch All: List all user access logs
	public ArrayList<UserAccessLog> fetchUserAccessLogs() throws SQLException {
		String fetch = "SELECT * FROM accesslogs";
		ResultSet rs = st.executeQuery(fetch);
		ArrayList<UserAccessLog> allAccessLogs = new ArrayList<>();

		while (rs.next()) {
			String name = rs.getString(4);

			System.out.println(name);
		}
		return accessLogs;
	}*/
    
}
