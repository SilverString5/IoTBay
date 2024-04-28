/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.test;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;


/**
 *
 * @author pyaephyozaw
 */
public class UserDAOTest {
    private DBConnector connector;
    private Connection conn;
    private UserDAO userDAO;
    public UserDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        userDAO = new UserDAO(conn);
    }
    
    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
    }
    
    @Test
    public void testSelectUsers() throws SQLException {
       ArrayList<User> users = userDAO.fetchUsers();
       assertEquals(users.size(), 1);
    }
}
