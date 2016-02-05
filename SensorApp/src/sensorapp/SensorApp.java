/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp;

import java.sql.SQLException;
import sensorapp.constants.DBType;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.SensorFactory;
import sensorapp.constants.SensorType;
import sensorapp.datahelper.DBExecute;
import sensorapp.datahelper.DBUtil;
import sensorapp.station.Station;

/**
 *
 * @author leo
 */
public class SensorApp {

    public static void main(String[] args) throws SQLException {
          
        
      Station station = new Station(new SensorFactory());
      Sensor sensor1 = station.createSensor("Temp", SensorType.TEMPERATURE);
      System.out.println(sensor1);
      station.startSensor(sensor1);
      Sensor sensor2 = station.createSensor("Hum", SensorType.HUMIDITY);
      System.out.println(sensor2);
      station.startSensor(sensor2);
      Sensor sensor3 = station.createSensor("Press", SensorType.PRESSURE);
      System.out.println(sensor3);
      station.startSensor(sensor3);
      Sensor sensor4 = station.createSensor("Wind", SensorType.WIND_VELOCITY);
      System.out.println(sensor4);
      station.startSensor(sensor4);
        DBExecute.selectSQL(DBUtil.getConnection(DBType.POSTGRESQL), "6", sensor1.hashCode());
                
      
      
    }
    
}
