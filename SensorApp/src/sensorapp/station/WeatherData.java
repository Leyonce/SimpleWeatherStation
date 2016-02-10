/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp.station;

import java.util.ArrayList;
import sensorapp.appinterfaces.Observer;
import sensorapp.appinterfaces.Subject;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.pojo.SensorData;

/**
 *
 * @author leo
 */
public class WeatherData implements Subject {

    private static final WeatherData WDInstance = new WeatherData();
    private ArrayList observers;
    private SensorData data;
    private Sensor sensor;

    private WeatherData() {
        observers = new ArrayList();
    }

    public static WeatherData getInstance() {
        return WDInstance;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void setMeasurement(SensorData data, Sensor sensor) {
        this.data = data;
        this.sensor = sensor;
        measurementsChanged();
    }

    public void measurementsChanged() {
        notifyObservers();
    }
    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(data, sensor);
        }
    }


}
