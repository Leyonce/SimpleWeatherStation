/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import sensorapp.constants.SensorType;
import sensorapp.sensors.pojo.Location;

/**
 *
 * @author leo
 */
public class SensorFactory {
 /*** Create sensor object depending on the type of sensor. This factory sets the SI Units of the various sensors*/   


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
