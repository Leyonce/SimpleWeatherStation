/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.appinterfaces;

import sensorapp.sensors.Sensor;
import sensorapp.sensors.pojo.SensorData;

/**
 *
 * @author leo
 */
public interface Observer {
    public void update(SensorData data, Sensor sensor);
    
}
