/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.commondialogs;

import Controllers.sessionController;
import Models.Messages;
import java.awt.event.KeyEvent;

/**
 *
 * @author ARTEMISA
 */
public class addTextValue extends javax.swing.JDialog {

    /**
     * Creates new form addTextValue
     */
    boolean Next = false;
    boolean delete = false;
    boolean update = false;
    String Content;

    public boolean isNext() {
        return Next;
    }

    public void setNext(boolean Next) {
        this.Next = Next;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public addTextValue(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        this.setResizable(false);
        txtContent.requestFocus();
    }

    public void setData(String titulo) {
        lblTitulo.setText(titulo);
        btnAcept.setText("Aceptar");
        btnDropReg.setVisible(false);
        this.revalidate();
        this.repaint();
    }

    public void setData(String titulo, String content) {
        lblTitulo.setText(titulo);
        txtContent.setText(content);
        btnAcept.setText("Actualizar");
        setUpdate(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnAcept = new javax.swing.JButton();
        btnDropReg = new javax.swing.JButton();
        txtContent = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setText("TITULO");

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelKeyPressed(evt);
            }
        });

        btnAcept.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAcept.setText("Save/Delete");
        btnAcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptActionPerformed(evt);
            }
        });
        btnAcept.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAceptKeyPressed(evt);
            }
        });

        btnDropReg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDropReg.setText("Eliminar");
        btnDropReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropRegActionPerformed(evt);
            }
        });
        btnDropReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDropRegKeyPressed(evt);
            }
        });

        txtContent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDropReg, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAcept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .addComponent(txtContent))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAcept)
                        .addComponent(btnDropReg)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean checkFields() {
        boolean resp = true;
        if (txtContent.getText().trim().length() < 1) {
            txtContent.requestFocus();
            resp = false;
        }
        return resp;
    }

    public void addReg() {
        if (checkFields()) {
            setContent(txtContent.getText());
            setNext(true);
            this.dispose();
        }
    }

    public void updateReg() {
        if (checkFields()) {
            if (new Messages().confirmMessage("Actualizar Registro", "Desea Actualizar esta Registro?")) {
                setContent(txtContent.getText());
                setNext(true);
                this.dispose();
            }

        }
    }

    public void btnCancel() {
        this.dispose();
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        btnCancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnCancel();
        }

        if (evt.getKeyCode() == 37) {
            btnAcept.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCancel();
        }
    }//GEN-LAST:event_btnCancelKeyPressed

    public void setactionPreformed() {
        if (isUpdate()) {
            updateReg();
        } else {
            addReg();
        }
    }

    private void btnAceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptActionPerformed
        setactionPreformed();
    }//GEN-LAST:event_btnAceptActionPerformed

    private void btnAceptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnCancel();
        }

        if (evt.getKeyCode() == 39) {
            btnCancel.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setactionPreformed();
        }
    }//GEN-LAST:event_btnAceptKeyPressed

    private void btnDropRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropRegActionPerformed
        dropItem();
    }//GEN-LAST:event_btnDropRegActionPerformed

    public void dropItem() {
        String text = "<html>"
                + "<center>"
                + "<b>"
                + "¡ESTA ACCIÓN ES IRREVERSIBLE!"
                + "</b>"
                + "<br>"
                + "<br>"
                + "¿Desea continuar?"
                + "</center>"
                + "</html>";
        if (new Messages().confirmMessage("Eliminar Registro", text)) {
            setDelete(true);
            setNext(true);
            this.dispose();
        }
    }
    private void btnDropRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDropRegKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnCancel();
        }

        if (evt.getKeyCode() == 39) {
            btnCancel.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dropItem();
        }
    }//GEN-LAST:event_btnDropRegKeyPressed

    private void txtContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContentActionPerformed
        setactionPreformed();
    }//GEN-LAST:event_txtContentActionPerformed

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
            java.util.logging.Logger.getLogger(addTextValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addTextValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addTextValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addTextValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addTextValue dialog = new addTextValue(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAcept;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDropReg;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtContent;
    // End of variables declaration//GEN-END:variables
}
