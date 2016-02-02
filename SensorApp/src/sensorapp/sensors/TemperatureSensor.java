/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import sensorapp.sensors.pojo.Location;
import sensorapp.constants.SensorType;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leo
 */
class TemperatureSensor extends Sensor {

    public TemperatureSensor(String name,SensorType type) {
        super(name,type);
        this.siUnit = "°C";

    }

    public TemperatureSensor(String name, Location location) {
        super(name, location);

    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
                sleep(3000);
                generateSensorData();
            } catch (InterruptedException ex) {
                Logger.getLogger(WindVelocitySensor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   

}
