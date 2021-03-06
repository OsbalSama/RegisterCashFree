/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.adminControlls;

import Controllers.cypherController;
import Controllers.sessionController;
import Controllers.userController;
import Models.Messages;
import Models.User;
import java.awt.Color;
import java.awt.HeadlessException;

/**
 *
 * @author ATENEA
 */
public class newUser extends javax.swing.JDialog {

    /**
     * Creates new form newUser
     */
    String Class = "newUser";
    userController allUsers = userController.getInstance();
    User User;
    boolean next = false;
    int continuar = 0;
    boolean update = false;

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public int getContinuar() {
        return continuar;
    }

    public void setContinuar(int continuar) {
        this.continuar = continuar;
    }

    boolean ok = false;

    public boolean isOk() {
        return ok;
    }

    public newUser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        txtPassword.setTransferHandler(null);
        txtConfirmPassword.setTransferHandler(null);
    }

    public void createUser() {
        btnUpdate.setVisible(false);
        User = new User();
        User.genId();
        txt_id.setText(User.getId());
    }

    public void editUser(User User) {
        setUpdate(true);
        txtTitle.setText("Actualizar cuenta");
        this.User = User;
        btnCreate.setVisible(false);
        txt_id.setText(User.getId());
        txtUsername.setText(User.getUsername());
        txtPassword.setText(User.getPassword());
        cmbRole.setSelectedItem(User.getRole());
    }

    public void addAdminAccount() {
        txtTitle.setText("CREAR CUENTA ADMIN");
        btnUpdate.setVisible(false);
        User = new User();
        User.genId();
        txt_id.setText(User.getId());
        cmbRole.setSelectedItem("Administrador");
        cmbRole.enable(false);
        this.setDefaultCloseOperation(0);
        txtUsername.setText("admin");
        txtPassword.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUpdate.setText("Actualizar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("Datos de cuenta: ");

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCreate.setText("Continuar");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Contrase??a");

        txtTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitle.setText("CREAR CUENTA NUEVA");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel20.setText("Puesto");

        txt_id.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_id.setText("000000000000");

        cmbRole.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cajero", "Administrador" }));
        cmbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRoleActionPerformed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel18.setText("Rep. Cont");

        txtConfirmPassword.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtConfirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPasswordActionPerformed(evt);
            }
        });
        txtConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConfirmPasswordKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Identificador");

        txtUsername.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsername))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addComponent(txtConfirmPassword)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_id)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(txtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitle)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnCreate)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        storeUser();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void cmbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRoleActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        txtConfirmPassword.requestFocus();
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
//        String cadena = (txtPassword.getText());
//        txtPassword.setText(cadena);
        if (txtPassword.getText().isEmpty()) {
            txtPassword.setBackground(txtUsername.getBackground());
            txtPassword.setForeground(txtUsername.getForeground());
        } else {
            if (txtPassword.getText().length() <= 3) {
                txtPassword.setBackground(Color.red);
                txtPassword.setForeground(Color.white);
            } else if (txtPassword.getText().length() > 3 && txtPassword.getText().length() < 6) {
                txtPassword.setBackground(Color.yellow);
                txtPassword.setForeground(Color.black);
            } else {
                txtPassword.setBackground(txtUsername.getBackground());
                txtPassword.setForeground(txtUsername.getForeground());
            }
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    public void updateUser() {
        try {
            if (checkFieldsUpdate()) {
                String text = "<html>"
                        + "<center>"
                        + "<b>"
                        + "??SE ACTUALIZARAN LOS DATOS ASIGNADOS!"
                        + "</b>"
                        + "<br>"
                        + "<br>"
                        + "??Desea continuar?"
                        + "</center>"
                        + "</html>";
                if (new Messages().confirmMessage("Actualizar Cuenta", text)) {
                    User.setUsername(txtUsername.getText().replace(',', ' '));
                    User.setPassword(txtPassword.getText().replace(',', ' '));
                    User.setRole(cmbRole.getSelectedItem().toString());
                    User.setEdited();
                    if (allUsers.update(User)) {
                        setNext(true);
                        dispose();
                    }
                }
            }
        } catch (HeadlessException e) {
            new Messages().errorMessage(Class, "updateUser()", e);
        }
    }

    public boolean checkFieldsUpdate() {
        boolean resp = false;
        if (txtUsername.getText().replace(" ", "").length() > 0) {
            if (txtPassword.getText().replace(" ", "").length() > 0) {
                if (txtConfirmPassword.getText().replace(" ", "").length() > 0) {
//                    if (allUsers.findUserByName(txtUsername.getText().replace(',', ' ')) != null) {
                    if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
                        resp = true;
                    } else {
                        txtConfirmPassword.setText("");
                        txtConfirmPassword.requestFocus();
                    }
                } else {
                    txtConfirmPassword.requestFocus();
                }
            } else {
                txtPassword.requestFocus();
            }
        } else {
            txtUsername.requestFocus();
        }
        return resp;
    }

    public boolean checkFields() {
        boolean resp = false;
        if (txtUsername.getText().length() > 0) {
            if (txtPassword.getText().length() > 0) {
                if (txtConfirmPassword.getText().length() > 0) {
                    if (allUsers.findByName(txtUsername.getText().replace(',', ' ')) == null) {
                        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
                            resp = true;
                        } else {
                            txtConfirmPassword.setText("");
                            txtConfirmPassword.requestFocus();
                        }
                    } else {
                        String text = "<html>"
                                + "<center>"
                                + "<b>"
                                + "??ESTA CUENTA YA EXISTE!"
                                + "</b>"
                                + "<br>"
                                + "<br>"
                                + "Ya existe una Cuenta de Usuario con este Descripcion... Vuelva a intentarlo"
                                + "</center>"
                                + "</html>";
                        new Messages().aceptMessage("Agregar Cuenta", text);

                        txtUsername.requestFocus();
                    }
                } else {
                    txtConfirmPassword.requestFocus();
                }
            } else {
                txtPassword.requestFocus();
            }
        } else {
            txtUsername.requestFocus();
        }
        return resp;
    }

    public void storeUser() {
        try {
            if (checkFields()) {
                String text = "<html>"
                        + "<center>"
                        + "<b>"
                        + "??VERIFIQUE SUS DATOS ANTES DE CONTINUAR!"
                        + "</b>"
                        + "<br>"
                        + "<br>"
                        + "??Desea continuar?"
                        + "</center>"
                        + "</html>";
                if (new Messages().confirmMessage("Agregar Cuenta", text)) {
                    User.setUsername(new cypherController().clearText(txtUsername.getText().replace(',', ' ')));
                    User.setPassword(txtPassword.getText().replace(',', ' '));
                    User.setRole(cmbRole.getSelectedItem().toString());
                    if (allUsers.create(User)) {
                        ok = true;
                        dispose();
                    }
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "storeUser()", e);
        }
    }

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
//        if (txtPassword.getText().length() >= 40) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmPasswordActionPerformed
        actionPreformed();
    }//GEN-LAST:event_txtConfirmPasswordActionPerformed

    public void actionPreformed() {
        if (isUpdate()) {
            updateUser();
        } else {
            storeUser();
        }
    }

    private void txtConfirmPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPasswordKeyReleased

        if (txtConfirmPassword.getText().isEmpty()) {
            txtConfirmPassword.setBackground(txtUsername.getBackground());
            txtConfirmPassword.setForeground(txtUsername.getForeground());
        } else {
            if (!txtConfirmPassword.getText().equals(txtPassword.getText())) {
                txtConfirmPassword.setBackground(Color.red);
                txtConfirmPassword.setForeground(Color.white);
            } else {
                txtConfirmPassword.setBackground(txtUsername.getBackground());
                txtConfirmPassword.setForeground(txtUsername.getForeground());
            }
        }
    }//GEN-LAST:event_txtConfirmPasswordKeyReleased

    private void txtConfirmPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPasswordKeyTyped
//        if (txtConfirmPassword.getText().length() >= 40) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtConfirmPasswordKeyTyped

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        txtPassword.requestFocus();
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setContinuar(2);
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateUser();
    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(newUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                newUser dialog = new newUser(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JLabel txtTitle;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel txt_id;
    // End of variables declaration//GEN-END:variables
}
