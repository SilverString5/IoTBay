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
    private String readQuery = "SELECT * FROM users WHERE userEmail=? AND userPassword=?";
    private String updateQuery = "UPDATE users SET userEmail=?, userPassword=?, userFullName=?, userPhone=?, userAddress=?, userDOB=?, userGender=? WHERE userID=?";
    private String deleteQuery = "DELETE FROM users WHERE userEmail=? AND userPassword=?";

	public UserDAO(Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
	}

// Create Operation: create a user
	public void createUser(String userEmail, String userFullName, String userPassword, String userPhone, String userAddress, String userDOB, String userGender, String userType) throws SQLException {
		String columns = "INSERT INTO users(userEmail, userFullName,userPassword,userPhone,userAddress, userDOB, userGender, userType)";
		String values = "VALUES('" + userEmail + "','" + userFullName + "','" + userPassword + "','" 
                + userPhone + "','" + userAddress + "','" + userDOB + "','"+ userGender + "','" + userType +"')";
		st.executeUpdate(columns + values);
	}

	// Read Operation: user login
	public User login(String userEmail, String userPassword) throws SQLException {
		readSt.setString(1, userEmail);
		readSt.setString(2, userPassword);
		ResultSet rs = readSt.executeQuery();

		while (rs.next()) {
			String dataUserEmail = rs.getString(2);
			String dataUserPass = rs.getString(3);
			if (userEmail.equals(dataUserEmail) && userPassword.equals(dataUserPass)) {
				int userID = Integer.parseInt(rs.getString(1));
				String userFullName = rs.getString(4);
				String userPhone = rs.getString(5);
                                String userAddress = rs.getString(6);
                                String userDOB = rs.getString(7);
                                String userGender = rs.getString(8);
                                String userType = rs.getString(9);
				return new User(userID, userEmail, userPassword, userFullName, userPhone, userAddress, userDOB, userGender, userType);
				
                            }
		
                          }
                return null;
                        }

	// Update Operation: update user
	public void update(String userEmail, String userPassword, String userFullName, 
        String userPhone, String userAddress, String userDOB, String userGender, int userID) throws SQLException {
                updateSt.setString(1, userEmail);
		updateSt.setString(2, userPassword);
		updateSt.setString(3, userFullName);
                updateSt.setString(4, userPhone);
                updateSt.setString(5, userAddress);
                updateSt.setString(6, userDOB);
                updateSt.setString(7, userGender);
                updateSt.setString(8, Integer.toString(userID));


		int row = updateSt.executeUpdate();
		System.out.println("row " + row + " updated successfuly");
	}

	// Delete Operation: delete a user by userEmail & userPassword
	public void delete(String userEmail, String userPassword) throws SQLException {
		deleteSt.setString(1, userEmail);
                deleteSt.setString(2, userPassword);
		int row = deleteSt.executeUpdate();
		System.out.println("row " + row + " deleted successfuly");
	}

	// Fetch All: List all users
	public ArrayList<User> fetchUsers() throws SQLException {
		String fetch = "SELECT * FROM users";
		ResultSet rs = st.executeQuery(fetch);
		ArrayList<User> allUsers = new ArrayList<>();

		while (rs.next()) {
                        int userID =rs.getInt(1);
                        String userEmail = rs.getString(2);
                        String userPassword = rs.getString(3);
			String userFullName = rs.getString(4);
                        String userPhone = rs.getString(5);
                        String userAddress = rs.getString(6);
                        String userDOB = rs.getString(7);
                        String userGender = rs.getString(8);
                        String userType=rs.getString(9);
                        User user = new User(userID, userEmail, userPassword, userFullName, 
                        userPhone, userAddress, userDOB, userGender, userType);
                        allUsers.add(user);

		}
		return allUsers;
	}
}


    

