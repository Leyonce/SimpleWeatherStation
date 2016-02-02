/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.station.ConcreteDisplay;

import sensorapp.appinterfaces.DisplayElement;
import sensorapp.appinterfaces.Observer;
import sensorapp.station.WeatherData;

/**
 *
 * @author leo
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement{
     
     private double temperature;
     private double humidity;
     private double pressure;
     private WeatherData weatherData;
    
    @SuppressWarnings("LeakingThisInConstructor") 
    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    } 
    @Override
    public void update(double temp, double humidity, double pressure) {
       
        this.temperature = temp;
        this.humidity= humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("Current Conditions: " + this.temperature  + "F degrees and " + this.humidity
    + "% humidity and pressure " +this.pressure + "!");
   }
    
    
}
