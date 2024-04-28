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
	public void createUserAccessLog(String email, String name, String password, int phone, String address, String DOB, String gender, String type) throws SQLException {
		String columns = "INSERT INTO USERS(EMAIL,\"NAME\",PASSWORD,PHONE, ADDRESS, DOB, GENDER, TYPE)";
		String values = "VALUES('" + email + "','" + name + "','" + password + "','" 
                + phone + "'," + address + "," + DOB + "','"+ gender + "','" + type +"')";
		st.executeUpdate(columns + values);
	}

	// Read Operation: user login
	public UserAccessLog read(String email, String password) throws SQLException {
		readSt.setString(1, email);
		readSt.setString(2, password);
		ResultSet rs = readSt.executeQuery();

		while (rs.next()) {
			String useremail = rs.getString(2);
			String userpass = rs.getString(3);
			if (email.equals(useremail) && password.equals(userpass)) {
				int ID = Integer.parseInt(rs.getString(1));
				String name = rs.getString(4);
				String phone = rs.getString(5);
                                String address = rs.getString(6);
                                String DOB = rs.getString(7);
                                String type = rs.getString(8);
				// return new User(ID, email, name, password, phone);
				return null;
			}
		}
		return null;
	}



	// Fetch All: List all user access logs
	public ArrayList<UserAccessLog> fetchUserAccessLogs() throws SQLException {
		String fetch = "SELECT * FROM userAccessLogs";
		ResultSet rs = st.executeQuery(fetch);
		ArrayList<UserAccessLog> accessLogs = new ArrayList<UserAccessLog>();

		while (rs.next()) {
			String name = rs.getString(4);

			System.out.println(name);
		}
		return accessLogs;
	}
    
}
