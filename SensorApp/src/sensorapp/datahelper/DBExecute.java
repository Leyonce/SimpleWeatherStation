/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.datahelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensorapp.constants.DBType;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.pojo.Location;
import sensorapp.station.Station;

/**
 * Execute specific database queries
 *
 * @author leo
 */
public class DBExecute {

//    private static final String SENSOR_CREATE_SQL = "INSERT INTO sensors (sensor_id,sensor_name,sensor_type,sensor_location ) VALUES (?,?,?,?);";
    private static final String SENSOR_CREATE_SQL = "INSERT INTO sensors (sensor_id,sensor_name,sensor_type,sensor_location ) "
                                                        + "SELECT sensor_id,sensor_name,sensor_type,sensor_location FROM sensors UNION VALUES(?,?,?,?) "
                                                        + "EXCEPT SELECT sensor_id,sensor_name,sensor_type,sensor_location from sensors";

    private static final String SENSOR_INSERT_INFO_SQL = "INSERT INTO sensors_info (sensor_id,sensor_data,sensor_date,sensor_time) VALUES (?,?,?,?);";
    private static final String SENSOR_DATA_TIME = "SELECT sensor_data, sensor_time FROM sensors_info WHERE to_char(sensor_date,'D') in (?) AND sensor_id =?";
    private static final String SENSORSLISTSQL = "SELECT  DISTINCT sensor_name FROM sensors WHERE sensor_type = ?";
    private static final String SENSORSQL = "SELECT sensor_type, sensor_location, sensor_id from sensors where sensor_name=?";
    private static Connection conn = DBUtil.getConnection(DBType.POSTGRESQL);

    /**
     * Count the number of sensors in the database
     *
     * @return
     */
    public int getRowCount() {
        ResultSet rs = null;
        int rows = 0;
        try (
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            rs = stmt.executeQuery("SELECT * FROM sensors");
            rs.last();
            rows = rs.getRow();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBExecute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return rows;
    }

    /**
     * Gets the sensor data and corresponding time for a given day
     *
     * @param day numeric representation of day
     * @param id sensor identification
     */
    public static void getSensorDataTime(String day, String id) {
        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(SENSOR_DATA_TIME, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, day);
            stmt.setString(2, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int f = rs.getInt(1);
                Time x = rs.getTime(2);

                System.out.println("Data:" + f + ", time:" + x);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBExecute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    /**
     * Creates a sensor from the sensors table
     *
     * @param sensorName name of sensor in the database
     * @return sensor
     */
    public static Sensor getSensor(String sensorName) {

        ResultSet rs = null;

        Sensor sensor = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(SENSORSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, sensorName);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String[] loca = rs.getString(2).split(",");
                Location location = new Location();
                location.setLongitude(Double.parseDouble(loca[0]));
                location.setLatitude(Double.parseDouble(loca[1]));

                sensor = Station.getInstance().createSensor(sensorName, rs.getString(1), location);

            }

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Query Issue!!");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBExecute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return sensor;

    }

    /**
     * Returns the list of sensors of a specific type
     *
     * @param sensorType Type of the sensor
     * @return ArrayList of sensors
     */
    public static ArrayList getSensorList(String sensorType) {

        ResultSet rs = null;
        ArrayList arrList = new ArrayList();

        try (
                PreparedStatement stmt = conn.prepareStatement(SENSORSLISTSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, sensorType);

            rs = stmt.executeQuery();
            while (rs.next()) {
                arrList.add(rs.getString(1));

            }

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Query Issue!!");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBExecute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return arrList;

    }

    /**
     * Create/insert a sensor in the database
     *
     * @param id identification of sensor (primary key)
     * @param name name of the sensor
     * @param type type of the sensor
     * @param location location of sensor
     */
    public static void insertSensorSQL(String id, String name, String type, String location) {

        try (
                PreparedStatement stmt = conn.
                prepareStatement(SENSOR_CREATE_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, type);
            stmt.setString(4, location);

            stmt.execute();

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Sensor Exist already!!");
        }

    }

    /**
     * This method inserts the sensor data into the sensor_info table
     *
     * @param id sensor Identification (foreign key)
     * @param data sensor data
     * @param date date of data creation
     * @param time time of data creation
     */
    public static void insertSensorDataSQL(String id, double data, Date date, Time time) {

        try (
                PreparedStatement stmt = conn.
                prepareStatement(SENSOR_INSERT_INFO_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, id);

            stmt.setDouble(2, data);
            stmt.setDate(3, date);

            stmt.setTime(4, time);

            stmt.execute();

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Insert Issue!!");
        }

    }

    public void updateSQL(Connection conn, String SQL) throws SQLException {

    }

    public void deleteSQL(Connection conn, String SQL) throws SQLException {

    }
}
