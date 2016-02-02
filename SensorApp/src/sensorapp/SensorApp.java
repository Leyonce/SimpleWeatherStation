/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp;

import sensorapp.sensors.Sensor;
import sensorapp.sensors.SensorFactory;
import sensorapp.constants.SensorType;
import sensorapp.station.Station;

/**
 *
 * @author leo
 */
public class SensorApp {

    public static void main(String[] args) {
          
      Station station = new Station(new SensorFactory());
      Sensor sensor = station.createSensor("Temp", SensorType.TEMPERATURE);
      station.startSensor(sensor);
      sensor = station.createSensor("Temp", SensorType.HUMIDITY);
      station.startSensor(sensor);
      sensor = station.createSensor("Temp", SensorType.PRESSURE);
      station.startSensor(sensor);
      sensor = station.createSensor("Temp", SensorType.WIND_VELOCITY);
      station.startSensor(sensor);
      
      
//        WeatherData weatherData = new WeatherData();
//        CurrentConditionsDisplay currentDusplay = new CurrentConditionsDisplay(weatherData); 
//        weatherData.setMeasurement(00,30.40f,65);
    }
    
}
