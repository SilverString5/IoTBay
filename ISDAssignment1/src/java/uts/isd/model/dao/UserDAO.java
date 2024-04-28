/*IotBay
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
import uts.isd.model.User;




/**
 *
 * @author notba
 */
public class UserDAO {
    private Statement st;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private String readQuery = "SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=?";
    private String updateQuery = "UPDATE USERS SET EMAIL=?, PASSWORD=?, \"NAME\"=?, PHONE=?, ADDRESS=?, DOB=?, GENDER=? WHERE UserID=?";
    private String deleteQuery = "DELETE FROM USERS WHERE UserID= ?";

	public UserDAO(Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
	}

// Create Operation: create a user
	public void createUser(String email, String name, String password, int phone, String address, String DOB, String gender, String type) throws SQLException {
		String columns = "INSERT INTO USERS(EMAIL,\"NAME\",PASSWORD,PHONE, ADDRESS, DOB, GENDER, TYPE)";
		String values = "VALUES('" + email + "','" + name + "','" + password + "','" 
                + phone + "'," + address + "," + DOB + "','"+ gender + "','" + type +"')";
		st.executeUpdate(columns + values);
	}

	// Read Operation: user login
	public User login(String email, String password) throws SQLException {
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

	// Update Operation: update user
	public void update(int userID, String email, String password, String name, 
        String phone, String address, String DOB, String gender) throws SQLException {
		updateSt.setString(1, Integer.toString(userID));
                updateSt.setString(2, email);
		updateSt.setString(3, password);
		updateSt.setString(4, name);
                updateSt.setString(5, phone);
                updateSt.setString(6, address);
                updateSt.setString(7, DOB);
                updateSt.setString(8, gender);


		int row = updateSt.executeUpdate();
		System.out.println("row " + row + " updated successfuly");
	}

	// Delete Operation: delete a user by email & password
	public void delete(String email, String password, int ID) throws SQLException {
		deleteSt.setString(1, Integer.toString(ID));
		int row = deleteSt.executeUpdate();
		System.out.println("row " + row + " deleted successfuly");
	}

	// Fetch All: List all users
	public ArrayList<User> fetchUsers() throws SQLException {
		String fetch = "SELECT * FROM user";
		ResultSet rs = st.executeQuery(fetch);
		ArrayList<User> users = new ArrayList<User>();

		while (rs.next()) {
			String name = rs.getString(4);

			System.out.println(name);
		}
		return users;
	}
}


    

