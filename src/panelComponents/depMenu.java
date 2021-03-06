/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panelComponents;

import Controllers.sessionController;
import Controllers.systemController;
/**
 *
 * @author ARTEMISA
 */
public class depMenu extends javax.swing.JPanel {

    /**
     * Creates new form depMenu
     */
    sessionController Session = sessionController.getInstance();

    public depMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUser = new javax.swing.JLabel();
        btnSettings = new javax.swing.JButton();
        btnCloseApp = new javax.swing.JButton();
        lblRole = new javax.swing.JLabel();
        btnLoggout = new javax.swing.JButton();
        btnHelo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("Username");
        lblUser.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnSettings.setBackground(new java.awt.Color(255, 255, 255));
        btnSettings.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSettings.setText("Ajustes");
        btnSettings.setBorder(null);
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        btnCloseApp.setBackground(new java.awt.Color(255, 255, 255));
        btnCloseApp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCloseApp.setText("Salir");
        btnCloseApp.setBorder(null);
        btnCloseApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseAppActionPerformed(evt);
            }
        });

        lblRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRole.setText("Role");
        lblRole.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnLoggout.setBackground(new java.awt.Color(255, 255, 255));
        btnLoggout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLoggout.setText("Cerrar Sesion");
        btnLoggout.setBorder(null);
        btnLoggout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoggoutActionPerformed(evt);
            }
        });

        btnHelo.setBackground(new java.awt.Color(255, 255, 255));
        btnHelo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHelo.setText("Ayuda");
        btnHelo.setBorder(null);
        btnHelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(btnCloseApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLoggout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLoggout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCloseApp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnHelo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setData() {
        lblUser.setText(Session.getLogedUser().getUsername());
        lblRole.setText(Session.getLogedUser().getRole());
        if (!Session.getLogedUser().isAdmin()) {
            btnSettings.setVisible(false);
        }
    }

    private void btnCloseAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseAppActionPerformed
        sessionController.getInstance().closeApp();
    }//GEN-LAST:event_btnCloseAppActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        systemController.getInstance().updateStoreData();
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnLoggoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoggoutActionPerformed
        sessionController.getInstance().Loggout();
    }//GEN-LAST:event_btnLoggoutActionPerformed

    private void btnHeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeloActionPerformed
        systemController.getInstance().getContactPage();
    }//GEN-LAST:event_btnHeloActionPerformed

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseApp;
    private javax.swing.JButton btnHelo;
    private javax.swing.JButton btnLoggout;
    private javax.swing.JButton btnSettings;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables
}
