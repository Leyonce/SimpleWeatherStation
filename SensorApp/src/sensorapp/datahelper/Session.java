/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.datahelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sensorapp.constants.DBType;

/**
 *
 * @author leo
 */
public class Session {

    public void getConnection() throws SQLException {

        try (
                Connection conn = DBUtill.getConnection(DBType.POSTGRESQL);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Sensors")) {
            rs.last();

            System.out.println("Connected: " + rs.getRow());
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

}
