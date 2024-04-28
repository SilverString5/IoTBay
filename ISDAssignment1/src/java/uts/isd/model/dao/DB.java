/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;
import java.sql.Connection;
/**
 *
 * @author pyaephyozaw
 */
public abstract class DB {
    protected String URL = "jdbc:mysql://localhost:3306/";
    protected String db = "IoTBay";
    protected String dbuser = "root";
    protected String dbpass = "mysql117@";
    protected String params = "?useSSL=false&allowPublicKeyRetrieval=true";
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected Connection conn;
}
