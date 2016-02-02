/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import sensorapp.constants.SensorType;

/**
 *
 * @author leo
 */
public class SensorFactory {
 /*** Create sensor object depending on the type of sensor. This factory sets the SI Units of the various sensors*/   


public Sensor createSensor(String name, SensorType sensorType ) {
    Sensor sensor = null;
    if(sensorType.equals(SensorType.HUMIDITY)) {
     //create humidity sensor   
        sensor = new HumiditySensor(name, sensorType);
    } else if (sensorType.equals(SensorType.TEMPERATURE)) {
       //create temperature sensor 
        sensor = new TemperatureSensor(name, sensorType);

    } else if (sensorType.equals(SensorType.PRESSURE)) {
        //create pressure sensor
        sensor = new PressureSensor(name, sensorType);

    } else if (sensorType.equals(SensorType.WIND_VELOCITY)) {
        //create wind velocity sensor
        sensor = new WindVelocitySensor(name, sensorType);

    }
    
    return sensor;
}

}
