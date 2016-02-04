/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp;

import java.sql.SQLException;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.SensorFactory;
import sensorapp.constants.SensorType;
import sensorapp.datahelper.Session;
import sensorapp.station.ConcreteDisplay.CurrentConditionsDisplay;
import sensorapp.station.Station;
import sensorapp.station.WeatherData;

/**
 *
 * @author leo
 */
public class SensorApp {

    public static void main(String[] args) throws SQLException {
          
        Session session  = new Session();
        session.getConnection();
//      Station station = new Station(new SensorFactory());
//      Sensor sensor1 = station.createSensor("Temp", SensorType.TEMPERATURE);
//      System.out.println(sensor1);
//      station.startSensor(sensor1);
//      Sensor sensor2 = station.createSensor("Hum", SensorType.HUMIDITY);
//      System.out.println(sensor2);
//      station.startSensor(sensor2);
//      Sensor sensor3 = station.createSensor("Press", SensorType.PRESSURE);
//      System.out.println(sensor3);
//      station.startSensor(sensor3);
//      Sensor sensor4 = station.createSensor("Wind", SensorType.WIND_VELOCITY);
//      System.out.println(sensor4);
//      station.startSensor(sensor4);
      
      
      
    }
    
}
