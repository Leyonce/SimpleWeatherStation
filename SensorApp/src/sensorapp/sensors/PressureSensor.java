/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors;

import sensorapp.sensors.pojo.Location;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leo
 */
class PressureSensor extends Sensor {

    
    
    public PressureSensor(String name,String type) {
        super(name, type);
        this.siUnit = "pascal";
    }

    public PressureSensor(String name,String type, Location location) {
        super(name,type, location);
        this.siUnit = "pascal";
    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
//                sleep(120000);
                sleep(this.UpdateTime);
                try {
                    generateSensorData();
                } catch (SQLException ex) {
                    Logger.getLogger(PressureSensor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                currentThread().interrupt();
            }
        }
    }

   
}
