/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp.sensors;

import java.sql.SQLException;
import sensorapp.sensors.pojo.SensorData;
import sensorapp.sensors.pojo.SensorDataList;
import sensorapp.sensors.pojo.Location;
import sensorapp.constants.Status;
import sensorapp.constants.SensorType;
import java.util.Random;
import sensorapp.constants.DBType;
import sensorapp.datahelper.DBExecute;
import sensorapp.datahelper.DBUtil;

/**
 *
 * @author leo
 */
public abstract class Sensor extends Thread {
    /*** Sensor Abstract class, all sensors must extend this class*/
    protected Location location;
    protected String name = null;
    protected int id= 0;
    protected SensorData sensorData = null;
    protected SensorDataList sensorDataList= null;
    
    protected Status status = Status.OFF;
    protected SensorType sensorType= null;
    protected String siUnit = null;
    
    
    public Sensor(String name,SensorType sensorType ) {
        /***Sensor constructor, sets the name of the sensor, the type of the sensor and instantiate the sensor data List ArrayList*/
        this.id= this.hashCode();
        this.name = name;
        this.sensorType = sensorType;
        this.sensorDataList = new SensorDataList();
    }
    
    public Sensor(String name,Location location  ) {
        /***Sensor constructor, sets the name of the sensor, the type of the sensor, the location and instantiate the sensor data List ArrayList*/
        this.id= this.hashCode();
        this.name = name;
        this.location = location;
        this.sensorDataList = new SensorDataList();
       
    }
    
    public Location getLocation() {
        /*** Returns the location of the sensor*/
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SensorData getSensorData() {
         /*** Returns the current sensor data object */
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
        DBExecute dbExecute = new DBExecute();
        dbExecute.insertSQL(DBUtil.getConnection(DBType.POSTGRESQL),this.id, this.name,sensorData.getData(), sensorData.getDate(), this.sensorType.toString(), this.sensorData.getTime());
        
//      //notify display  
//      WeatherData weatherData = new WeatherData();
//      CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData); 
//      weatherData.setMeasurement(sensorData);
        sensorDataList.addSensorData(sensorData);
    }

    @Override
    public String toString() {
        return "This sensor called:" +this.name + ", it is of type: "+ this.sensorType+" and is located at: " + this.location ; 
    }
    
    
    
}
