/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorapp;

import java.sql.SQLException;
import sensorapp.constants.SensorType;
import sensorapp.sensors.Sensor;
import sensorapp.sensors.pojo.Location;
import sensorapp.station.ConcreteDisplay.StationUI;
import sensorapp.station.Station;

/**
 *
 * @author leo
 */
public class SensorApp {

    public static void main(String[] args) throws SQLException {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        StationUI station = StationUI.getInstance();
        station.setVisible(true);

        Sensor sensor1 = Station.getInstance().createSensor("Temp1", SensorType.TEMPERATURE.toString(), new Location());
//        System.out.println(sensor1);
//        Station.getInstance().startSensor(sensor1);
        Sensor sensor2 = Station.getInstance().createSensor("Humi1", SensorType.HUMIDITY.toString(), new Location());
//        System.out.println(sensor2);
//        Station.getInstance().startSensor(sensor2);
        Sensor sensor3 = Station.getInstance().createSensor("Press1", SensorType.PRESSURE.toString(), new Location());
//        System.out.println(sensor3);
//        Station.getInstance().startSensor(sensor3);
        Sensor sensor4 = Station.getInstance().createSensor("Wind1", SensorType.WIND_VELOCITY.toString(), new Location());
//        System.out.println(sensor4);
//        Station.getInstance().startSensor(sensor4);

    }

}
