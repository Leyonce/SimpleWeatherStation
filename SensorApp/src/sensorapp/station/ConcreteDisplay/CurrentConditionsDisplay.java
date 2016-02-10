/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.station.ConcreteDisplay;

import sensorapp.appinterfaces.DisplayElement;
import sensorapp.appinterfaces.Observer;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.pojo.SensorData;
import sensorapp.station.WeatherData;

/**
 *
 * @author leo
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    public String id;
    private Sensor sensor;
    private SensorData data;
    private WeatherData weatherData;

    private static CurrentConditionsDisplay instance;

    private CurrentConditionsDisplay() {
        this.weatherData = WeatherData.getInstance();
        weatherData.registerObserver(this);

    }

    public static CurrentConditionsDisplay getInstance() {
        if (instance == null) {
            instance = new CurrentConditionsDisplay();
        }
        return instance;
    }

    @Override
    public void update(SensorData data, Sensor sensor) {

        this.data = data;
        this.sensor = sensor;
        display();
    }

    /**
     * Display current conditions of sensor if this display object's sensor is selected
     * @return 
     */
    @Override
    public String display() {
        Double s = this.data.getData();
        if(this.sensor.getSensor_name().equals(StationUI.getInstance().getCurrentSensor().getSensor_name()) ) {
            StationUI.getInstance().getCurrentValueTextField().setText(s.toString() + " " + this.data.getSiUnit());
        }
        return s.toString();

    }

}
