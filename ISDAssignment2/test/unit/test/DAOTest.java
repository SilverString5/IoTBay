/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.test;
import uts.isd.model.dao.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;
import uts.isd.model.*;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author jijianlan
 */
public class DAOTest {
    
    private DBConnector connector;
    private Connection conn;
    
    public DAOTest() throws ClassNotFoundException, SQLException
    {
        connector = new DBConnector();
        conn = connector.openConnection();
        
    }
    
    @Test
    public void testConnection() throws SQLException 
    {
        assertNotNull(conn);
    }
    
}
