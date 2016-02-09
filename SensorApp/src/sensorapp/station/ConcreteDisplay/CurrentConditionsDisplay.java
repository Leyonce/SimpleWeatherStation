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
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    
    public String id ;
    private SensorData data;
    private WeatherData weatherData;

    private static CurrentConditionsDisplay instance;
    
    
    private CurrentConditionsDisplay() {
        this.weatherData =   WeatherData.getInstance();
        weatherData.registerObserver(this);
           
    }
    public static CurrentConditionsDisplay getInstance() {
        if (instance == null) {
            instance = new CurrentConditionsDisplay();
        }
        return instance;
    }
    
    @Override
    public void update(SensorData data) {

        this.data = data;
        display();
    }

    @Override
    public String display() {
        Double s = this.data.getData();
          
        StationUI.getInstance().getCurrentValueTextField().setText(s.toString()+ " "+this.data.getSiUnit());
        return s.toString();
        
    }

}
