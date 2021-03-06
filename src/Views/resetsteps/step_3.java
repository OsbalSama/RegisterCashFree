/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.resetsteps;

import Controllers.sessionController;
import Controllers.systemController;
import Models.Messages;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author ARTEMISA
 */
public class step_3 extends javax.swing.JDialog {

    /**
     * Creates new form step_3
     */
    String Class = "step_3";
    boolean next = false;
    systemController sysConf = systemController.getInstance();

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public step_3(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Cancel();
            }
        });
    }

    public void setData() {
        loadPrinters();
        loadPaperSizes();
        txtTerms.requestFocus();
    }

    public void loadPaperSizes() {
        cmbSizes.removeAllItems();
        cmbSizes.addItem("50mm");
        cmbSizes.addItem("80mm");
        cmbSizes.addItem("Letter");
        cmbSizes.setSelectedIndex(0);
    }

    public void loadPrinters() {
        cmbPrinters.removeAllItems();
        cmbPrinters.addItem("No Imprimir");
        cmbPrinters.addItem("Imprimir como PDF");
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService p : ps) {
            cmbPrinters.addItem(p.getName());
        }
        cmbPrinters.setSelectedIndex(0);
    }

    public void Cancel() {
        if (new Messages().confirmMessage("CANCELAR", "Desea Cancelar?")) {
            dispose();
        }
    }

    public boolean checkFields() {
        boolean resp = false;
        if (txtTerms.getText().trim().replace(" ", "").length() > 0) {
            resp = true;
        } else {
            txtTerms.requestFocus();
            txtTerms.setBackground(Color.yellow);
        }
        return resp;
    }

    public void Continue() {
        if (checkFields()) {
            if (new Messages().continueCancelMessage("Continuar", "Desea continuar?")) {
                sysConf.updatePrinter(cmbPrinters.getSelectedItem().toString());
                sysConf.updatePaperSize(cmbSizes.getSelectedIndex());
                sysConf.updateTerms(txtTerms.getText());
                setNext(true);
                this.dispose();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTerms = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        cmbPrinters = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbSizes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setText("Nota de Venta");

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnContinue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnContinue.setText("Continuar");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        txtTerms.setColumns(20);
        txtTerms.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtTerms.setLineWrap(true);
        txtTerms.setRows(5);
        txtTerms.setWrapStyleWord(true);
        txtTerms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTermsKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtTerms);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Impresora Predeterminada");

        cmbPrinters.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tama??o de hoja...");

        cmbSizes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Terminos y Condiciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 161, Short.MAX_VALUE)
                        .addComponent(btnContinue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbPrinters, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSizes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPrinters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSizes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnContinue))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Cancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        Continue();
    }//GEN-LAST:event_btnContinueActionPerformed

    private void txtTermsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTermsKeyPressed
        txtTerms.setBackground(Color.white);
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.consume();
            btnContinue.requestFocus();
        }
    }//GEN-LAST:event_txtTermsKeyPressed

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
            java.util.logging.Logger.getLogger(step_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(step_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(step_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(step_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                step_3 dialog = new step_3(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnContinue;
    private javax.swing.JComboBox<String> cmbPrinters;
    private javax.swing.JComboBox<String> cmbSizes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txtTerms;
    // End of variables declaration//GEN-END:variables
}
