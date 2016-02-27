/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import java.sql.SQLException;
import sensorapp.sensors.pojo.Location;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leo
 */
class WindVelocitySensor extends Sensor {

    public WindVelocitySensor(String name, String type) {
        super(name, type);
        this.siUnit = "m/s";

    }

    public WindVelocitySensor(String name,String type, Location location) {
        super(name,type, location);
        this.siUnit = "m/s";

    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
//               sleep(60000);
                sleep(this.UpdateTime);

                try {
                    generateSensorData();
                } catch (SQLException ex) {
                    Logger.getLogger(WindVelocitySensor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                     currentThread().interrupt();
            }
        }
    }

}
