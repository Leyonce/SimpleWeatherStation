/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.station;

import java.util.ArrayList;
import sensorapp.sensors.Sensor;

/**
 *
 * @author leo
 */
public class SensorList {

    ArrayList list;

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public SensorList() {
        this.list = new ArrayList();
    }

    public void addSensor(Sensor sensor) {
        for (int i = 0; i < list.size(); i++) {
            Sensor s  = (Sensor)list.get(i);
            if (Integer.toString(sensor.getSensor_id()).equals(Integer.toString(s.getSensor_id()))) {
                System.out.println("Already in the list");
                return;
            }
        }
       

        list.add(sensor);

    }
    
    
    public void removeSensor(Sensor sensor) {
        int i = list.indexOf(sensor);
        if (i >= 0) {
            list.remove(sensor);
            System.out.println("Removed sensor" + sensor.getSensor_name());
        }
    }

}
