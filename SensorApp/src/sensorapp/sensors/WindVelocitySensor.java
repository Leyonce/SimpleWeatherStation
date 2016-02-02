/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import sensorapp.sensors.pojo.Location;
import sensorapp.constants.SensorType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leo
 */
class WindVelocitySensor extends Sensor {

    
    public WindVelocitySensor(String name,SensorType type) {
        super(name, type);
        this.siUnit = "km/s";
        
    }
    public WindVelocitySensor(String name, Location location) {
        super(name, location);
        
    }

   public void run() {
       while(!this.isInterrupted())
           try {
               sleep(5000);
             generateSensorData();
           } catch (InterruptedException ex) {
               Logger.getLogger(WindVelocitySensor.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

   

    
}
