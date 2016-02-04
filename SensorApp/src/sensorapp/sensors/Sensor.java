/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp.sensors;

import sensorapp.sensors.pojo.SensorData;
import sensorapp.sensors.pojo.SensorDataList;
import sensorapp.sensors.pojo.Location;
import sensorapp.constants.Status;
import sensorapp.constants.SensorType;
import java.util.Random;
import sensorapp.station.ConcreteDisplay.CurrentConditionsDisplay;
import sensorapp.station.WeatherData;

/**
 *
 * @author leo
 */
public abstract class Sensor extends Thread {
    /*** Sensor Abstract class, all sensors must extend this class*/
    protected Location location;
    protected String name = null;
    protected SensorData sensorData = null;
    protected SensorDataList sensorDataList= null;
    
    protected Status status = Status.OFF;
    protected SensorType sensorType= null;
    protected String siUnit = null;
    
    
    public Sensor(String name,SensorType sensorType ) {
        /***Sensor constructor, sets the name of the sensor, the type of the sensor and instantiate the sensor data List ArrayList*/
        this.name = name;
        this.sensorType = sensorType;
        this.sensorDataList = new SensorDataList();
    }
    
    public Sensor(String name,Location location  ) {
        /***Sensor constructor, sets the name of the sensor, the type of the sensor, the location and instantiate the sensor data List ArrayList*/

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
         /*** Returns the current sensor dataobject */
        return sensorData;
    }

    public void setDataObject() {
        
        this.sensorData.setData(55);
    }

    protected void generateSensorData() {
        Random rn = new Random();
        double number = rn.nextInt(100 - 0 + 1) + 0;
        sensorData = new SensorData();
        sensorData.setSiUnit(siUnit);
        sensorData.setData(number);
      //notify dispaly  
      WeatherData weatherData = new WeatherData();
      CurrentConditionsDisplay currentDusplay = new CurrentConditionsDisplay(weatherData); 
      weatherData.setMeasurement(sensorData);
//        System.out.println("Hello from "+ sensorType.toString()+ " "+  this.sensorData.toString());
        sensorDataList.addSensorData(sensorData);
       
    }

    @Override
    public String toString() {
        return "This sensor called:" +this.name + ", it is of type: "+ this.sensorType+" and is located at: " + this.location ; 
    }
    
    
    
}
