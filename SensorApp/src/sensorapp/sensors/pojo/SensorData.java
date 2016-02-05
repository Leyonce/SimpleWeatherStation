/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors.pojo;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 *
 * @author leo
 */
public class SensorData {
// Associates value of Data, Date and SIUnit as a single object.  

    private Date date = null;
    private double data;
    private String siUnit = null;
    private Time time= null;

    public SensorData() {
        this.time = new Time(Calendar.getInstance().getTimeInMillis());
        this.date = new Date(Calendar.getInstance().getTimeInMillis());
       
    }
    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public String getSiUnit() {
        return siUnit;
    }

    public void setSiUnit(String siUnit) {
        this.siUnit = siUnit;
    }

    @Override
    public String toString() {
        return "Data is: " + this.getData() + " SI Units: " + this.getSiUnit() + " Date: " + this.getDate();

    }

}
