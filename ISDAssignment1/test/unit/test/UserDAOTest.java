/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
IOTBay
 */
package unit.test;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import uts.isd.model.dao.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import uts.isd.model.User;



/**
 *
 * @author notba
 */


public class UserDAOTest {

	private DBConnector connector;
	private Connection conn;
	private UserDAO manager;

	public UserDAOTest() throws ClassNotFoundException, SQLException {
		connector = new DBConnector();
		conn = connector.openConnection();
		manager = new UserDAO(conn);
	}

	@Test
	public void testConnection() throws SQLException {
		assertNotNull(conn);
	}

	@Test
	public void testSelectUsers() throws SQLException {
		ArrayList<User> users = manager.fetchUsers();
		assertEquals(users.size(), 4);
	}
}

    

