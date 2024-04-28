/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 *
 * @author notba
 */
public class DBConnector extends DB {
    public DBConnector() throws ClassNotFoundException, SQLException {  
        Class.forName(driver);

Properties dbProperties = new Properties();
dbProperties.put("user", dbuser);
dbProperties.put("password", dbpass);
dbProperties.put("allowPublicKeyRetrieval","true");
dbProperties.put("useSSL", "false");

try {
 conn = DriverManager.getConnection(URL + db, dbProperties);
}

catch (SQLException e) {
 e.printStackTrace();
}
}

public Connection openConnection() {
    return this.conn;
}

public void closeConnection() throws SQLException {
    this.conn.close();
}
}
