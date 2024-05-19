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
import java.sql.Date;




/**
 *
 * @author notba
 */
public class UserDAO {
    private Connection con; 
    //Initialising statements here so they can be used later  
    private Statement st;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement checkSt;
    private PreparedStatement retrieveSt;
    private PreparedStatement findSt;

    // SQL statements
    private String readQuery = "SELECT * FROM users WHERE userEmail=? AND userPassword=?";
    private String updateQuery = "UPDATE users SET userEmail=?, userPassword=?, userFullName=?, userPhone=?, userAddress=?, userDOB=?, userGender=? WHERE userID=?";
    private String deleteQuery = "DELETE FROM users WHERE userID=?";
    private String checkQuery = "SELECT * FROM users WHERE userEmail=?";
    private String retrieveQuery = "SELECT userID FROM users WHERE userEmail=?";
    private String findQuery = "SELECT * from users WHERE userID=?";


	public UserDAO(Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
                checkSt = connection.prepareStatement(checkQuery);
                retrieveSt = connection.prepareStatement(retrieveQuery);
                findSt = connection.prepareStatement(findQuery);
	}

        // Create Operation: create a user
	public void createUser(String userEmail, String userPassword, String userFullName,  String userPhone, String userAddress, Date userDOB, String userGender) throws SQLException {
		String columns = "INSERT INTO users(userEmail, userFullName,userPassword,userPhone,userAddress, userDOB, userGender)";
		String values = "VALUES('" + userEmail + "','" + userFullName + "','" + userPassword + "','" 
                + userPhone + "','" + userAddress + "','" + userDOB + "','"+ userGender + "')";
		st.executeUpdate(columns + values);
	}

        //Check if user already exists to prevent re-registration
        public boolean checkExists(String userEmail) throws SQLException{
            boolean exists = false;
            checkSt.setString(1, userEmail);
            ResultSet rs = checkSt.executeQuery();
            while (rs.next()){
                String email = rs.getString(2);
                if (userEmail.equals(email)){
                        exists = true;
                }
            }

                return exists;
            }

        //Retrieve userID based on email
        public int retrieveUserID(String userEmail) throws SQLException{
            retrieveSt.setString(1, userEmail);
            ResultSet rs = retrieveSt.executeQuery();
            rs.next();
            int ID = rs.getInt(1);
            return ID;
}

        //Find a user based on userID
        public int findUser(String userEmail) throws SQLException{
            retrieveSt.setString(1,userEmail);
            ResultSet rs = retrieveSt.executeQuery();
            rs.next();
            int userID = rs.getInt(retrieveQuery);
            return userID;
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
                                Date userDOB = rs.getDate(7);
                                String userGender = rs.getString(8);
                                String userType = rs.getString(9);
				return new User(userID, userEmail, userPassword, userFullName, userPhone, userAddress, userDOB, userGender, userType);
				
                            }
		
                          }
                return null;
                        }

	// Update Operation: update user
	public User update(String userEmail, String userPassword, String userFullName, 
        String userPhone, String userAddress, String userDOB, String userGender, int userID) throws SQLException {
                updateSt.setString(1, userEmail);
		updateSt.setString(2, userPassword);
		updateSt.setString(3, userFullName);
                updateSt.setString(4, userPhone);
                updateSt.setString(5, userAddress);
                updateSt.setDate(6, Date.valueOf(userDOB));
                updateSt.setString(7, userGender);
                updateSt.setString(8, Integer.toString(userID));


		int row = updateSt.executeUpdate();
		System.out.println("row " + row + " updated successfuly");
                checkSt.setString(1, userEmail);
                ResultSet rs = checkSt.executeQuery();
                rs.next();
                String userType = rs.getString(9);
                User user = new User (userID, userEmail, userPassword, 
                userFullName, userPhone, userAddress, Date.valueOf(userDOB), userGender, userType);
                return user;  



	}

	// Delete Operation: delete a user by userEmail & userPassword
	public void delete(int userID) throws SQLException {
		deleteSt.setInt(1, userID);
		int row = deleteSt.executeUpdate();
		System.out.println("row " + row + " deleted successfuly");
	}

	// Fetch All: List all users
	public ArrayList<User> fetchUsers() throws SQLException {
		String fetch = "SELECT * FROM users";
		ResultSet rs = st.executeQuery(fetch);
		ArrayList<User> allUsers = new ArrayList<>();

		while (rs.next()) {
                        int userID=rs.getInt(1);
                        String userEmail = rs.getString(2);
                        String userPassword = rs.getString(3);
			String userFullName = rs.getString(4);
                        String userPhone = rs.getString(5);
                        String userAddress = rs.getString(6);
                        Date userDOB = rs.getDate(7);
                        String userGender = rs.getString(8);
                        String userType=rs.getString(9);
                        User user = new User(userID, userEmail, userPassword, userFullName, 
                        userPhone, userAddress, userDOB, userGender, userType);
                        allUsers.add(user);

		}
		return allUsers;
	}
}


    
