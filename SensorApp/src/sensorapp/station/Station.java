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
        sensorList.addSensor(sensor);
        DBExecute.insertSensorSQL(sensor.getSensor_id(), name, type, location.toString());
        return sensor;
    }

    public void startSensor(Sensor sensor) {
        if (sensor.isInterrupted() || !sensor.isAlive()) {
            try {
                sensor.start();
            } catch (Exception e) {
                currentSensor = createSensor(sensor.getSensor_name(), sensor.getSensorType(), sensor.getLocation());
                this.startSensor(currentSensor);
            }
            sensor.setStatus(Status.ON);
            System.out.println(sensor);
        }
    }

    public void stopSensor(Sensor sensor) {
            SensorData s= new SensorData();
            s.setData(0.0);
        if (sensor.isAlive() || !sensor.isInterrupted()) {

            sensor.interrupt();
            sensor.setStatus(Status.OFF);
            WeatherData.getInstance().setMeasurement(s);
        }
        if (currentSensor != null) {

            currentSensor.interrupt();
            currentSensor.setStatus(Status.OFF);
            WeatherData.getInstance().setMeasurement(s);

        }
    }

    public void deleteSensor(Sensor sensor) {
        sensorList.removeSenor(sensor);
        this.stopSensor(sensor);
    }

    public SensorList getSensorList() {

        return sensorList;
    }

}
