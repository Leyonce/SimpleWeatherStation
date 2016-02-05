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

    void addSensor(Sensor sensor) {
        list.add(sensor);
    }

    void removeSenor(Sensor sensor) {
        int i = list.indexOf(sensor);
        if (i >= 0) {
            list.remove(sensor);
        }
    }

}
