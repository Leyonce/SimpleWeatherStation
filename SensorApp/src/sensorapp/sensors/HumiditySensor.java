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
class HumiditySensor extends Sensor {

    public HumiditySensor(String name,SensorType type) {
        super(name, type);
        this.siUnit = "hum";

    }

    public HumiditySensor(String name, Location location) {
        super(name, location);

    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
                sleep(7000);
                generateSensorData();
            } catch (InterruptedException ex) {
                Logger.getLogger(WindVelocitySensor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
