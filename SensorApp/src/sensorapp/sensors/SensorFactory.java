/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */

package sensorapp.sensors;

import sensorapp.constants.SensorType;
import sensorapp.sensors.pojo.Location;

/**
  * Create sensor object depending on the type of sensor. 
  * The classes inheriting from sensor are responsible for setting their own unique parameters. 
  * This class decides on which type of sensor to create using the sensor type as key parameter. 
 */

public class SensorFactory {
 
    /**
     * @return 
     */
public Sensor createSensor(String name, String sensorType, Location location ) {
    Sensor sensor = null;
    if(sensorType.equals(SensorType.HUMIDITY.toString())) {
     //create humidity sensor   
        sensor = new HumiditySensor(name, sensorType, location);
    } else if (sensorType.equals(SensorType.TEMPERATURE.toString())) {
       //create temperature sensor 
        sensor = new TemperatureSensor(name, sensorType, location);

    } else if (sensorType.equals(SensorType.PRESSURE.toString())) {
        //create pressure sensor
        sensor = new PressureSensor(name, sensorType, location);

    } else if (sensorType.equals(SensorType.WIND_VELOCITY.toString())) {
        //create wind velocity sensor
        sensor = new WindVelocitySensor(name, sensorType, location);

    }
    
    return sensor;
}

}
