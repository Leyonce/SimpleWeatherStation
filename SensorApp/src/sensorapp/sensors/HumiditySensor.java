/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import sensorapp.sensors.pojo.Location;
import sensorapp.constants.SensorType;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Humidity sensor
 *
 * @author leo
 */
class HumiditySensor extends Sensor {

    public HumiditySensor(String name, String type) {
        super(name, type);
        this.siUnit = "hum";

    }

    public HumiditySensor(String name, String type, Location location) {
        super(name, type, location);
        this.siUnit = "hum";
    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
//                sleep(420000);
                sleep(4000);
                try {
                    generateSensorData();
                } catch (SQLException ex) {
                    Logger.getLogger(HumiditySensor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                currentThread().interrupt();
            }
        }
    }

}
