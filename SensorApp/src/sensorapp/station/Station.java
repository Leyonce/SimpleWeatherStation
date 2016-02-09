package sensorapp.station;

import sensorapp.datahelper.DBExecute;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.SensorFactory;
import sensorapp.sensors.pojo.Location;

/**
 *
 * @author leo
 */
public class Station {

    private static SensorFactory factory = null;
    private static SensorList sensorList =  new SensorList();

    private static final Station stationInstance = new Station();

    private Station() {
        Station.factory = new SensorFactory();
        
    }

    public static Station getInstance() {

        return stationInstance;
    }

    public Sensor createSensor(String name, String type, Location location) {
        Sensor sensor;
        sensor = factory.createSensor(name, type, location);
        sensorList.addSensor(sensor);
        DBExecute.insertSensorSQL(sensor.getSensor_id(), name, type, location.toString());
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

    public SensorList getSensorList() {
        
        return sensorList;
    }

}
