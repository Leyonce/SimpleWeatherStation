/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp.sensors.pojo;

/**
 *
 * @author leo
 */
public class Location {
    private double longitude = 0.0;
    private double latitude = 0.0;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
    return this.longitude+","+this.latitude ;
    }
    
          
    
}
