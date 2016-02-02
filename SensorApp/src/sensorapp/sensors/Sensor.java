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
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author leo
 */
public abstract class Sensor extends Thread {
    protected Location location;
    protected String name = null;
    protected SensorData sensorData = null;
    protected SensorDataList sensorDataList= null;
    
    protected Status status = Status.OFF;
    protected SensorType sensorType= null;
    protected String siUnit = null;
    
    
    public Sensor(String name,SensorType sensorType ) {
        this.name = name;
        this.sensorType = sensorType;
        this.sensorDataList = new SensorDataList();
    }
    
    public Sensor(String name,Location location  ) {
        this.name = name;
        this.location = location;
        this.sensorDataList = new SensorDataList();
       
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setDataObject() {
        
        this.sensorData.setSensordata(55);
    }

    protected void generateSensorData() {
        Random rn = new Random();
        double number = rn.nextInt(100 - 0 + 1) + 0;
        sensorData = new SensorData();
        sensorData.setSiUnit(siUnit);
        sensorData.setSensordata(number);
        System.out.println("Hello from "+ sensorType.toString()+ " "+  this.sensorData.toString());
        sensorDataList.addSensorData(sensorData);
       
    }
    
}
