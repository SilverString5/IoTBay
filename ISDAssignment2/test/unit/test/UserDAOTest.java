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
import java.sql.Date;




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
	public void testfetchUsers() throws SQLException {
		ArrayList<User> users = manager.fetchUsers();
		assertTrue(users.size()>=10);
	}
        @Test
        public void testDeleteUser() throws SQLException{
            manager.delete(21);
        }

        @Test
        public void testCreateUser() throws SQLException{
                manager.createUser("ladygaga@testemail.com", "Lady Gaga", "test", "0444444445", "Fame St",Date.valueOf("1994-02-02"), "F");
            }
        
        @Test
        public void testUpdate() throws SQLException{
        User user = manager.update("updated@winxclub.com", "winxclub", "Layla Houssain", "0422222222",
        "42 Pixie Ave, Domino, QLD, 7023", "1992-07-21", "F", 3);
        assertEquals("winxclub", user.getPassword());
}
        @Test
        public void testLoginCorrect() throws SQLException{
                assertNotNull(manager.login("admin@iotbay.com","logmein"));
        }

        @Test
        public void testLoginIncorrect() throws SQLException{
        assertNull(manager.login("nonexistent@gmail.com", "idontexist"));
        }

        @Test
        public void testCheckExists() throws SQLException{
        assertTrue(manager.checkExists("JKirk@enterprise.com"));
        }




}

    
