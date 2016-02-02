/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp.station;

import sensorapp.sensors.Sensor;
import sensorapp.sensors.SensorFactory;
import sensorapp.constants.SensorType;

/**
 *
 * @author leo
 */
public class Station {
    
   SensorFactory factory;
   
   public Station(SensorFactory factory) {
       this.factory = factory;
   }
   
   public Sensor createSensor (String name, SensorType type) {
      Sensor sensor;
      sensor = factory.createSensor(name, type);
      return sensor;
   } 
   
  public void startSensor(Sensor sensor) {
   
      sensor.start();
  } 
  
  
  public void stopSensor(Sensor sensor) {
      sensor.interrupt();
  }
  
  

}
