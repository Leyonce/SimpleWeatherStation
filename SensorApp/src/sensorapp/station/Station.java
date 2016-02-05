
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
   SensorList sensorList = null;
   public Station(SensorFactory factory) {
       this.factory = factory;
       this.sensorList = new SensorList();
   }
   
   public Sensor createSensor (String name, SensorType type) {
      Sensor sensor;
      sensor = factory.createSensor(name, type);
      sensorList.addSensor(sensor);
      return sensor;
   } 
   
  public void startSensor(Sensor sensor) {
   
      sensor.start();
  } 
  
  
  public void stopSensor(Sensor sensor) {
      sensor.interrupt();
  }
  
  public void killSensor(Sensor sensor) {
      sensorList.removeSenor(sensor);
      this.stopSensor(sensor);
  }

}
