/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.datahelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author leo
 */
public class DBExecute {

    private static final String INSERTSQL = "INSERT INTO sensors (sensor_id,sensor_name, sensor_data, sensor_date, sensor_type,sensor_time) VALUES (?,?,?,?,?,?);";
    private static final String SELECTSQL = "SELECT  sensor_data, sensor_time FROM sensors WHERE to_char(sensor_date,'D') in (?) AND sensor_id =?";

    public static void selectSQL(Connection conn, String day, int id) throws SQLException {
        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(SELECTSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, day);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int f = rs.getInt(1);
                Time x = rs.getTime(2);
                
                System.out.println("Data:" + f + ", time:" + x);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    public static void insertSQL(Connection conn, int id, String name, double data, Date date, String type, Time time) throws SQLException {

        try (
                PreparedStatement stmt = conn.
                prepareStatement(INSERTSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setDouble(3, data);
            stmt.setDate(4, date);
            stmt.setString(5, type);
            stmt.setTime(6, time);

            stmt.execute();

        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    public void updateSQL(Connection conn, String SQL) throws SQLException {

    }

    public void deleteSQL(Connection conn, String SQL) throws SQLException {

    }
}
