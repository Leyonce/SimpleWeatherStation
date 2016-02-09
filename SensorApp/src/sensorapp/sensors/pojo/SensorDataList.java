/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors.pojo;

import java.util.ArrayList;

/**
 *
 * @author leo
 */
public class SensorDataList {
    
    protected ArrayList list;

    public SensorDataList() {
        this.list = new ArrayList();
    }
    
    public void addSensorData(SensorData sensorData) {
        list.add(sensorData);
    }
    
    public ArrayList getList() {
        return list;
    }

}
