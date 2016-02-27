/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.station.ConcreteDisplay;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensorapp.datahelper.DBExecute;

/**
 *
 * @author leo
 */
public class RemoveSensorUI extends javax.swing.JFrame {
 
    /**
     * Creates new form RemoveSensorUI
     */
    public RemoveSensorUI() {
        initComponents();
        setHideOnClose();
    }

    private void setHideOnClose() {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE );
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SensorNameComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        DeleteButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        SensorTypeComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SensorNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SensorNameComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Sensor Name: ");

        DeleteButton.setText("Delete!");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        jButton2.setText("cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Sensor Type: ");

        SensorTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TEMPERATURE", "PRESSURE", "HUMIDITY", "WIND_VELOCITY" }));
        SensorTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SensorTypeComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(17, 17, 17)
                        .addComponent(DeleteButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SensorTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SensorNameComboBox, 0, 145, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SensorTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(SensorNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeleteButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void SensorTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SensorTypeComboBoxActionPerformed
        // TODO add your handling code here:
         SensorNameComboBox.removeAllItems();
         for (int i = 0; i < DBExecute.getSensorList(SensorTypeComboBox.getSelectedItem().toString()).size(); i++) {
            SensorNameComboBox.addItem(DBExecute.getSensorList(SensorTypeComboBox.getSelectedItem().toString()).get(i));

        }
    }//GEN-LAST:event_SensorTypeComboBoxActionPerformed

    private void SensorNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SensorNameComboBoxActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_SensorNameComboBoxActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
     try {
         // TODO add your handling code here:
         DBExecute.deleteSensorSQL(SensorNameComboBox.getSelectedItem().toString());
     } catch (SQLException ex) {
         Logger.getLogger(RemoveSensorUI.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }//GEN-LAST:event_DeleteButtonActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteButton;
    private javax.swing.JComboBox SensorNameComboBox;
    private javax.swing.JComboBox SensorTypeComboBox;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
