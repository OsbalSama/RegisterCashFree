/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panelComponents;

import Controllers.systemController;
import Models.Messages;
import Models.SystemConfigurations;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author ARTEMISA
 */
public class updateData extends javax.swing.JPanel {

    /**
     * Creates new form updateData
     */
    String Class = "updateData";
    systemController sysConf = systemController.getInstance();
    SystemConfigurations Configs;

    public updateData() {
        initComponents();
        this.setName("Act. Datos");
        txtPhone.setTransferHandler(null);
    }

    public void updateConfigs() {
        Configs = sysConf.getSystemConfig();
    }

    public void setData() {
        try {

            updateConfigs();
            txtStoreName.setText(Configs.getStorename());
            chkNoRFC.setSelected(Configs.isNorfc());
            if (!Configs.isNorfc()) {
                txtRFC.setText(Configs.getRfc());
            }
            txtAdress.setText(Configs.getAdress());
            chkNoPhone.setSelected(Configs.isNophone());
            if (!Configs.isNophone()) {
                txtPhone.setText(Configs.getPhone());
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "setData()", e);
        }
    }

    public void enableResetMode() {
        btnSave.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        txtStoreName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        chkNoRFC = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        chkNoPhone = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAdress = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setBorder(null);

        txtStoreName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStoreNameKeyPressed(evt);
            }
        });

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPhoneKeyTyped(evt);
            }
        });

        jLabel2.setText("Nombre o razón social");

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave.setText("Actualizar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        chkNoRFC.setText("SIN RFC");
        chkNoRFC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkNoRFCItemStateChanged(evt);
            }
        });

        jLabel3.setText("Clave RFC");

        chkNoPhone.setText("SIN TEL");
        chkNoPhone.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkNoPhoneItemStateChanged(evt);
            }
        });

        txtAdress.setColumns(20);
        txtAdress.setLineWrap(true);
        txtAdress.setRows(5);
        txtAdress.setWrapStyleWord(true);
        txtAdress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdressKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtAdress);

        jLabel5.setText("Dirección");

        txtRFC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRFCKeyPressed(evt);
            }
        });

        jLabel6.setText("Teléfono ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Datos de Empresa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkNoPhone))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStoreName)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtRFC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkNoRFC))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkNoRFC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkNoPhone)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Update();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtStoreNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStoreNameKeyPressed
        txtStoreName.setBackground(Color.white);
    }//GEN-LAST:event_txtStoreNameKeyPressed

    private void chkNoRFCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkNoRFCItemStateChanged
        if (chkNoRFC.isSelected()) {
            txtRFC.setText("Sin RFC");
            txtRFC.setEnabled(false);
        } else {
            txtRFC.setText("");
            txtRFC.setEnabled(true);
            txtRFC.requestFocus();
        }
    }//GEN-LAST:event_chkNoRFCItemStateChanged

    private void chkNoPhoneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkNoPhoneItemStateChanged
        if (chkNoPhone.isSelected()) {
            txtPhone.setText("Sin Telefono");
            txtPhone.setEnabled(false);
        } else {
            txtPhone.setText("");
            txtPhone.setEnabled(true);
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_chkNoPhoneItemStateChanged

    private void txtRFCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRFCKeyPressed
        txtRFC.setBackground(Color.white);
    }//GEN-LAST:event_txtRFCKeyPressed

    private void txtAdressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdressKeyPressed
        txtAdress.setBackground(Color.white);
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.consume();
            txtPhone.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_txtAdressKeyPressed

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        txtPhone.setBackground(Color.white);
    }//GEN-LAST:event_txtPhoneKeyPressed

    private void txtPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '-') {
            evt.consume();
        }
    }//GEN-LAST:event_txtPhoneKeyTyped

    public boolean checkFields() {
        boolean resp = false;
        if (txtStoreName.getText().trim().replace(" ", "").length() > 0) {
            if (txtRFC.getText().trim().replace(" ", "").length() > 0) {
                if (txtAdress.getText().trim().replace(" ", "").length() > 0) {
                    if (txtPhone.getText().trim().replace(" ", "").length() > 0) {
                        resp = true;
                    } else {
                        txtPhone.requestFocus();
                        txtPhone.setBackground(Color.yellow);
                    }
                } else {
                    txtAdress.requestFocus();
                    txtAdress.setBackground(Color.yellow);
                }
            } else {
                txtRFC.requestFocus();
                txtRFC.setBackground(Color.yellow);
            }
        } else {
            txtStoreName.requestFocus();
            txtStoreName.setBackground(Color.yellow);
        }
        return resp;
    }

    public void Update() {
        try {
            if (checkFields()) {
                String text = "<html>"
                        + "<center>"
                        + "<b>"
                        + "¡VERIFIQUE SUS DATOS ANTES DE CONTINUAR!"
                        + "</b>"
                        + "<br>"
                        + "<br>"
                        + "¿Desea continuar?"
                        + "</center>"
                        + "</html>";
                if (new Messages().confirmMessage("Actualizar", text)) {
                    sysConf.updateStorename(txtStoreName.getText());
                    if (chkNoRFC.isSelected()) {
                        sysConf.updateNoRFC(true);
                    } else {
                        sysConf.updateNoRFC(false);
                    }
                    sysConf.updateRFC(txtRFC.getText());
                    sysConf.updateAdress(txtAdress.getText());
                    if (chkNoPhone.isSelected()) {
                        sysConf.updateNoPhone(true);
                    } else {
                        sysConf.updateNoPhone(false);
                    }
                    sysConf.updatePhone(txtPhone.getText());
                    new Messages().aceptMessage("Actualizar", "Datos Actualizados con Exito...");

                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateSystemConfigFile()", e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkNoPhone;
    private javax.swing.JCheckBox chkNoRFC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtAdress;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtStoreName;
    // End of variables declaration//GEN-END:variables
}