/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp.station;

import java.util.ArrayList;
import sensorapp.appinterfaces.Observer;
import sensorapp.appinterfaces.Subject;
import sensorapp.sensors.pojo.SensorData;

/**
 *
 * @author leo
 */
public class WeatherData implements Subject{
    
  private ArrayList observers; 
  private SensorData data;
  
  
  public WeatherData () {
      observers = new ArrayList();
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
  
  @Override
  public void notifyObservers() {
      for (int i = 0; i < observers.size(); i++) {
          Observer observer = (Observer) observers.get(i);
          observer.update(data);
      }
  }
  
  public void measurementsChanged() {
      notifyObservers();
  }
  public void setMeasurement(SensorData data ){
      this.data = data;
      measurementsChanged();
  }

    
}
