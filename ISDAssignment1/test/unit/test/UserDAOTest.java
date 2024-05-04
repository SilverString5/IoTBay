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

@Test public void testUpdate() throws SQLException {
manager.update("updatetest@gmail.com","none","Update test", "1", "R street","1994-05-05", "M", 30);

}

	@Test
	public void testSelectUsers() throws SQLException {
		ArrayList<User> users = manager.fetchUsers();
		assertTrue(users.size()>=20);
	}
        @Test
        public void testDeleteUser() throws SQLException{
            manager.delete("test@testemail.com","test");
        }

        @Test
        public void testCreateUser() throws SQLException{
                manager.createUser("test3@testemail.com", "Tester", "test", "0444444444", "Ray St","1994-02-02", "F", "C");
            }

        @Test
        public void testLoginCorrect() throws SQLException{
                assertNotNull(manager.login("admin@iotbay.com","logmein"));
        }

        @Test
        public void testLoginIncorrect() throws SQLException{
        assertNull(manager.login("nonexistent@gmail.com", "idontexist"));
        }
}

    

