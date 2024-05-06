/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import org.junit.Test;
import static org.junit.Assert.*;
import uts.isd.model.dao.*;
import java.util.ArrayList;
import uts.isd.model.UserAccessLog;

/**
 *
 * @author notba
 */
public class AccessLogDAOTest {
    private DBConnector connector;
    private Connection conn;
    private AccessLogDAO manager;

	public AccessLogDAOTest() throws ClassNotFoundException, SQLException {
		connector = new DBConnector();
		conn = connector.openConnection();
		manager = new AccessLogDAO(conn);
	}

	@Test
	public void testConnection() throws SQLException {
		assertNotNull(conn);
	}
        @Test
        public void testCreateUserAccessLog() throws SQLException{
                manager.createUserAccessLog(3);
        }

        @Test
        public void testFilterAccessLog() throws SQLException {
                ArrayList<UserAccessLog> filLogs = manager.filterAccessLogDate(3, "2024-05-03");
                assertEquals(filLogs.size(),1);
        }
         @Test
          public void testupdateLogout() throws SQLException {
          UserAccessLog accessLog = manager.updateLogoutTime(Time.valueOf("19:07:23"), 20);
          assertEquals(Time.valueOf("19:07:23"),accessLog.getLogoutTime());
        }   

        @Test
        public void testFindMostRecent() throws SQLException {
                UserAccessLog testLog = manager.findMostRecent(3);
                assertEquals(testLog.getAccessLogID(),32);
        }





}


    
