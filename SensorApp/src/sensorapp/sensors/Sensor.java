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

/**
 *
 * @author leo
 */
public abstract class Sensor extends Thread {

    /**
     * * Sensor Abstract class, all sensors must extend this class
     */
    protected Location location;
    protected String sensor_name;

    protected String sensor_id ;

    protected SensorData sensorData = null;
    protected SensorDataList sensorDataList = null;

    protected Status status = Status.OFF;
    protected String sensorType = null;
    protected String siUnit = null;

    public Sensor(String sensor_name, String sensorType) {
        /**
         * *Sensor constructor, sets the name of the sensor, the type of the
         * sensor and instantiate the sensor data List ArrayList
         */
        this.sensor_name =sensor_name;
        this.sensorType = sensorType;
        this.sensorDataList = new SensorDataList();
        this.sensor_id = this.MD5Code(sensor_name);
    }
    
    
    
    public Sensor(String sensor_name, String sensorType, Location location) {
        /**
         * *Sensor constructor, sets the name of the sensor, the type of the
         * sensor, the location and instantiate the sensor data List ArrayList
         */
        this.sensor_name = sensor_name;
        this.location = location;
        this.sensorType = sensorType;
        this.sensorDataList = new SensorDataList();
        this.sensor_id = this.MD5Code(sensor_name);

    }

    public String getSensor_name() {
        return sensor_name;
    }
    public Location getLocation() {
        /**
         * * Returns the location of the sensor
         */
        return location;
    }

    public String getSensor_id() {
        return sensor_id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SensorData getSensorData() {
        /**
         * * Returns the current sensor data object
         */
        return sensorData;
    }

    public void setDataObject() {

        this.sensorData.setData(55);
    }

    protected void generateSensorData() throws SQLException {
        Random rn = new Random();
        double number = rn.nextInt(100 - 0 + 1) + 0;
        sensorData = new SensorData();
        sensorData.setSiUnit(siUnit);
        sensorData.setData(number);
        DBExecute.insertSensorDataSQL(this.sensor_id, sensorData.getData(), sensorData.getDate(), this.sensorData.getTime());

//      //notify display  
//      WeatherData weatherData = new WeatherData();
//      CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData); 
//      weatherData.setMeasurement(sensorData);
        sensorDataList.addSensorData(sensorData);
    }

    @Override
    public String toString() {
        return " Created sensor called: "
                + this.sensor_name.toUpperCase()
                + ".\n The sensor is of type: " + this.sensorType
                + ". \n The sensor is located at: " + this.location;
    }

    public String MD5Code(String text) {
        String hashtext="";
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
        
            
                    System.out.println(hashtext.substring(0,10));
        return hashtext.substring(0,10);
    }

}
