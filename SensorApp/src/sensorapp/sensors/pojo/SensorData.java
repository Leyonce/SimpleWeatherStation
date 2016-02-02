/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors.pojo;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author leo
 */
public class SensorData {
// Associates value of Data, Date and SIUnit as a single object.  
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String date= null;
    private double sensordata;
    private String siUnit = null;

    
    public SensorData() {
     
      this.date = dateFormat.format(new Date());
  } 
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSensordata() {
        return sensordata;
    }

    public void setSensordata(double sensordata) {
        this.sensordata = sensordata;
    }
       
    public String getSiUnit() {
        return siUnit;
    }

    public void setSiUnit(String siUnit) {
        this.siUnit = siUnit;
    } 

    @Override
    public String toString() {
        return "Data is: " + this.getSensordata() + " SI Units: " +this.getSiUnit()+" Date: " + this.getDate();
        
        
    }
   
    
}
