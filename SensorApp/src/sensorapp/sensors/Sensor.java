/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp.sensors;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import sensorapp.sensors.pojo.SensorData;
import sensorapp.sensors.pojo.SensorDataList;
import sensorapp.sensors.pojo.Location;
import sensorapp.constants.Status;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensorapp.datahelper.DBExecute;
import sensorapp.station.ConcreteDisplay.CurrentConditionsDisplay;
import sensorapp.station.WeatherData;

/**
 * Sensor abstract class responsible for implementing common functionalities
 * between the sensors. Every sensor must extend this class which also extends
 * the Thread class. As such every sensor is implemented as a thread.
 */
public abstract class Sensor extends Thread {

    protected Location location;
    protected String sensor_name;

    protected String sensor_id;

    protected SensorData sensorData = null;
    protected SensorDataList sensorDataList = null;

    protected Status status = Status.OFF;

    protected String sensorType;

    protected String siUnit;

    public Sensor() {

        this.sensor_name = "NO Name";
        this.sensorType = "NO Type";
    }

    /**
     * Sensor constructor, sets the name of the sensor, the type of the sensor
     * and instantiate the sensor data List ArrayList
     */
    public Sensor(String sensor_name, String sensorType) {
        this.sensor_name = sensor_name;
        this.sensorType = sensorType;
        this.sensorDataList = new SensorDataList();
        this.sensor_id = this.MD5Code(sensor_name);
    }

    /**
     * *Sensor constructor, sets the name of the sensor, the type of the
     * sensor, the location and instantiate the sensor data List ArrayList
     */
    public Sensor(String sensor_name, String sensorType, Location location) {
        this.sensor_name = sensor_name;
        this.location = location;
        this.sensorType = sensorType;
        this.sensorDataList = new SensorDataList();
        this.sensor_id = this.MD5Code(sensor_name);

    }

    /**
     * List of sensor data generated in the current session
     *
     * @return SensorDataList
     */
    public SensorDataList getSensorDataList() {
        return sensorDataList;
    }

    /**
     * Returns the type of the sensor
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Returns the name of the sensor
     */
    public String getSensor_name() {
        return sensor_name;
    }

    /**
     * * Returns the location of the sensor
     */
    public Location getLocation() {
        return location;
    }

    public String getSensor_id() {
        return sensor_id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * * Returns the current sensor data object
     */
    public SensorData getSensorData() {
        return sensorData;
    }

    public void setDataObject() {

        this.sensorData.setData(55);
    }
/**
 * This method generates the data of sensors and inserts the data into the sensor_info table 
 * @throws SQLException 
 */
    protected void generateSensorData() throws SQLException {
        Random rn = new Random();
        double number = rn.nextInt(100 - 0 + 1) + 0;
        sensorData = new SensorData();
        sensorData.setSiUnit(siUnit);
        sensorData.setData(number);
        DBExecute.insertSensorDataSQL(this.sensor_id, sensorData.getData(), sensorData.getDate(), this.sensorData.getTime());

      //notify display  
      CurrentConditionsDisplay currentDisplay =CurrentConditionsDisplay.getInstance(); 
        WeatherData.getInstance().setMeasurement(sensorData);
        sensorDataList.addSensorData(sensorData);
//        System.out.println(currentDisplay.display());
    }

    /**
     * Generates the unique id of the sensor using the name of the sensor.
     */
    public String MD5Code(String text) {
        String hashtext = "";
        String plaintext = text;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
// Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hashtext.substring(0, 10);
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return " Created sensor called: "
                + this.sensor_name.toUpperCase()
                + ".\n The sensor is of type: " + this.sensorType
                + ". \n The sensor is located at: " + this.location;
    }
}
