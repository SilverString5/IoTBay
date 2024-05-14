/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;
import java.sql.Connection;

/**
 *
 * @author notba
IotBay
 */
public abstract class DB {
    protected String URL = "jdbc:mysql://localhost:3306/";
    protected String db = "iotbay";
//    protected String db = "IoTBay";
    protected String dbuser = "root";
    protected String dbpass = "password";
//    protected String dbpass = "mysql117@";
    protected String params = "?useSSL=false&allowPublicKeyRetrieval=true";
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected Connection conn;

    
}
