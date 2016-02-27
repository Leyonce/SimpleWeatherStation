/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.datahelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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

    private static Connection conn = DBUtil.getConnection(DBType.POSTGRESQL);
//    private static final String SENSOR_CREATE_SQL = "INSERT INTO sensors (sensor_id,sensor_name,sensor_type,sensor_location ) VALUES (?,?,?,?);";
    private static final String SENSOR_CREATE_SQL = "INSERT INTO sensors (sensor_name,sensor_type,sensor_location,sensor_update_time ) "
            + "SELECT sensor_name,sensor_type,sensor_location,sensor_update_time FROM sensors UNION VALUES(?,?,?,?) "
            + "EXCEPT SELECT sensor_name,sensor_type,sensor_location,sensor_update_time from sensors";

    private static final String SENSOR_INSERT_INFO_SQL = "INSERT INTO sensors_info (sensor_id,sensor_data,sensor_date,sensor_time) VALUES (?,?,?,?);";
    private static final String SENSOR_DATA_TIME = "SELECT sensor_data, sensor_time FROM sensors_info WHERE to_char(sensor_date,'D') in (?) AND sensor_id =?  AND sensor_date BETWEEN (?) and date (?) + INTERVAL '6days'";
    private static final String SENSORSLISTSQL = "SELECT  DISTINCT sensor_name FROM sensors WHERE sensor_type = ?";
    private static final String SENSORSQL = "SELECT sensor_type, sensor_location, sensor_update_time, sensor_id from sensors where sensor_name=?";
    private static final String SENSOR_UPDATE_SQL = "UPDATE sensors SET sensor_location = ?, sensor_update_time = ?  WHERE  sensor_id =?";
    private static final String SENSOR_INFO_DELETE_SQL = "DELETE FROM sensors_info WHERE sensor_id  IN (SELECT sensor_id from sensors where sensor_name =?)";
    private static final String SENSOR_DELETE_SQL = "DELETE FROM sensors where sensor_name = ?;";

    private static final String SENSOR_ID_QUERY_SQL = "SELECT sensor_id from sensors where sensor_name = ? and sensor_type= ? ";

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
     * @param date date of query
     * @return SensorData and sensor time
     */
    public static DefaultTableModel getSensorDataTime(String day, int id, Date date) {
        ResultSet rs = null;
        DefaultTableModel DTm = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(SENSOR_DATA_TIME, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, day);
            stmt.setInt(2, id);
            stmt.setDate(3, date);
            stmt.setDate(4, date);

            rs = stmt.executeQuery();
            System.out.println(stmt.toString());
            DTm = buildTableModel(rs);

        } catch (SQLException e) {
            System.err.println(e);
        }
        return DTm;
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames;
        columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data;
        data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    /**
     * Creates a sensor from the sensors table
     *
     * @param sensorName name of sensor in the database
     * @return sensor
     */
    public static Sensor CreateSensorFromTable(String sensorName) {

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
                sensor.setUpdateTime(rs.getInt(3));
            }

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Query 1 Issue!!");
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
     * Get specific sensor info
     *
     * @param sensorName
     * @return
     */
    public static ResultSet getSensor(String sensorName) {
        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(SENSORSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, sensorName);

            rs = stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Query 2  Issue!!");
        }
        return rs;

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
     * Returns the list of sensors of a specific type
     *
     * @param name
     * @param sensorType Type of the sensor
     * @return sensor id
     */
    public static int getSensorID(String name, String sensorType) {

        ResultSet rs = null;
        int id = 0 ;
        try (
                PreparedStatement stmt = conn.prepareStatement(SENSOR_ID_QUERY_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, name);
            stmt.setString(2, sensorType);

            rs = stmt.executeQuery();
            while (rs.next()) {
               id =rs.getInt(1);

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
        return id;

    }

    /**
     * Create/insert a sensor in the database
     *
     * @param name name of the sensor
     * @param type type of the sensor
     * @param location location of sensor
     */
    public static void CreateSensorSQL(String name, String type, String location, int updateTime) {

        try (
                PreparedStatement stmt = conn.
                prepareStatement(SENSOR_CREATE_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, location);
            stmt.setInt(4, updateTime);

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
    public static void insertSensorDataSQL(int id, double data, Date date, Time time) {

        try (
                PreparedStatement stmt = conn.
                prepareStatement(SENSOR_INSERT_INFO_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            stmt.setInt(1, id);

            stmt.setDouble(2, data);
            stmt.setDate(3, date);

            stmt.setTime(4, time);

            stmt.execute();

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Insert Issue!!");
        }

    }

    public static void updateSensorSQL(int sensorId, String location, int updateTime) throws SQLException {
        try (
                PreparedStatement stmt = conn.
                prepareStatement(SENSOR_UPDATE_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            stmt.setString(1, location);
            stmt.setInt(2, updateTime);
            stmt.setInt(3, sensorId);

            stmt.execute();

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Sensor Not upadted!!");
        }

    }

    public static void deleteSensorSQL(String sensorName) throws SQLException {

        try (
                PreparedStatement stmt1 = conn.
                prepareStatement(SENSOR_INFO_DELETE_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            stmt1.setString(1, sensorName);

            stmt1.execute();
            try (
                    PreparedStatement stmt = conn.
                    prepareStatement(SENSOR_DELETE_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);) {
                stmt.setString(1, sensorName);

                stmt.execute();

            } catch (SQLException e) {
                System.err.println(e);
                System.out.println("Sensor 1 Not Deleted!!");
            }

        } catch (SQLException e) {
            System.err.println(e);
            System.out.println("Sensor Not Deleted!!");
        }
    }

}
