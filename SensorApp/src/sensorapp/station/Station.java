package sensorapp.station;

import sensorapp.constants.Status;
import sensorapp.datahelper.DBExecute;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.SensorFactory;
import sensorapp.sensors.pojo.Location;
import sensorapp.sensors.pojo.SensorData;

/**
 *
 * @author leo
 */
public class Station {

    private static SensorFactory factory = null;
    private static SensorList sensorList = new SensorList();
    private static Sensor currentSensor;
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
        
        DBExecute.insertSensorSQL(sensor.getSensor_id(), name, type, location.toString());
        return sensor;
    }

    public void startSensor(Sensor sensor) {
        if (sensor.isInterrupted() || !sensor.isAlive()) {
            try {
                sensor.start();
            } catch (Exception e) {
                //if thread has already been started, create another thread then start it.
                currentSensor = createSensor(sensor.getSensor_name(), sensor.getSensorType(), sensor.getLocation());
                this.startSensor(currentSensor);
            }
            sensor.setStatus(Status.ON);
            
        }
    }

    public void stopSensor(Sensor sensor) {
            SensorData s= new SensorData();
            s.setData(0.0);
            s.setSiUnit("");
        if (sensor.isAlive() || !sensor.isInterrupted()) {

            sensor.interrupt();
            sensor.setStatus(Status.OFF);
            WeatherData.getInstance().setMeasurement(s,sensor);
        }
        if (currentSensor != null) {

            currentSensor.interrupt();
            currentSensor.setStatus(Status.OFF);
            WeatherData.getInstance().setMeasurement(s,sensor);

        }
    }

    public void deleteSensor(Sensor sensor) {
        sensorList.removeSensor(sensor);
        this.stopSensor(sensor);
    }

    public SensorList getSensorList() {

        return sensorList;
    }

}
