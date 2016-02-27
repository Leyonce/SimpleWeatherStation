/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import sensorapp.sensors.pojo.Location;
import sensorapp.constants.SensorType;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leo
 */
class TemperatureSensor extends Sensor {

    public TemperatureSensor(String name, String type) {
        super(name, type);
        this.siUnit = "°C";

    }

    public TemperatureSensor(String name, String type, Location location) {
        super(name, type, location);
        this.siUnit = "°C";
    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
//                sleep(180000);
                sleep(this.UpdateTime);

                try {
                    generateSensorData();
                } catch (SQLException ex) {
                    Logger.getLogger(TemperatureSensor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                currentThread().interrupt();
            }
        }
    }

}
