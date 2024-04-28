/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

//import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import uts.isd.model.User;
//import uts.isd.model.Staff;

/**
 *
 * @author pyaephyozaw
 */
public class UserDAO {
    private Statement st;
    private PreparedStatement readSt;
    private String readQuery = "SELECT * FROM user";
    
    public UserDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        st = connection.createStatement();
        readSt = connection.prepareStatement(readQuery);
    }
   
    public ArrayList<User> fetchUsers() throws SQLException {
        ResultSet rs = readSt.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
        while (rs.next()){
            String firstName = rs.getString(2);
//            String lastName = rs.getString(3);
            User u = new User();
            u.setName(firstName);
            System.out.println(u.getName());
            users.add(u);
        }
        return users;
    }
}
