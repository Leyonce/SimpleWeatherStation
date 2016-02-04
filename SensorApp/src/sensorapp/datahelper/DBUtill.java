/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import sensorapp.constants.DBType;

/**
 *
 * @author leo
 */
public class DBUtill {
     
    private static final String USERNAME = "stationuser";
    private static final String PASSWORD = "stationuser";
    private static final String P_CONN_STRING = 
            "jdbc:postgresql://localhost:5432/simpleweatherstation";
 
    
    public static Connection getConnection (DBType dBType) throws SQLException{
        switch (dBType) {
            case POSTGRESQL:
                return DriverManager.getConnection(P_CONN_STRING, USERNAME, PASSWORD);
            default :
                return null;
        }
    
    
}
}
