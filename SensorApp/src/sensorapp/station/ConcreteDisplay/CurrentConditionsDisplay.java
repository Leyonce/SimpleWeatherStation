/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.station.ConcreteDisplay;

import sensorapp.appinterfaces.DisplayElement;
import sensorapp.appinterfaces.Observer;
import sensorapp.sensors.pojo.SensorData;
import sensorapp.station.WeatherData;

/**
 *
 * @author leo
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement{
     
     private SensorData data;
     private WeatherData weatherData;
    
    @SuppressWarnings("LeakingThisInConstructor") 
    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    } 
    @Override
    public void update(SensorData data) {
       
        this.data = data;
        display();
    }

    @Override
    public void display() {
        System.out.println("Current Conditions: " + this.data);
   }
    
    
}
