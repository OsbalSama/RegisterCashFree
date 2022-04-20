/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.adminControlls;

import Controllers.dataController;
import Controllers.salesController;
import Controllers.sessionController;
import Controllers.systemController;
import Controllers.ticketController;
import Models.BoxCut;
import Models.Messages;
import Views.commondialogs.printerService;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARTEMISA
 */
public class allBoxCuts extends javax.swing.JFrame {

    /**
     * Creates new form allSales
     */
    String Class = "allBoxCuts";

    DefaultTableModel modelo;
    Date Now = new Date();
    List<BoxCut> filtredData;
    salesController DataBase = salesController.getInstance();

    public allBoxCuts() {
        initComponents();
    }

    public void loadFrame() {
        this.setTitle("Registro de Cortes Z");
        this.setIconImage(systemController.getInstance().getImageIcon().getImage());
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        loadTable();
        setNowDate();
        btnClose.requestFocus();
    }

    public void setNowDate() {
        dateFrom.setDate(Now);
        dateTo.setDate(Now);
    }

    public void loadTable() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas < 0) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Usuario");
        modelo.addColumn("Fecha de Corte");
        modelo.addColumn("Hora de Corte");
        tblData.setModel(modelo);

        tblData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblData.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2 && Mouse_evt.getButton() == MouseEvent.BUTTON1) {
                    try {
                        boxCutReport sr = new boxCutReport(null, true);
                        sr.setData(filtredData.get(tblData.getSelectedRow()));
                        sr.show();
                    } catch (Exception e) {
                        new Messages().errorMessage(Class, "loadTable()", e);
                    }
                }
            }
        }
        );
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
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateFrom = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dateTo = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnNewBoxCut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("A");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Registro Cortes Z");

        dateFrom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dateFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateFromPropertyChange(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("DE");

        dateTo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dateTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateToPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblData.setRowHeight(30);
        jScrollPane1.setViewportView(tblData);

        btnClose.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPrint.setText("Imprimir Reporte");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnNewBoxCut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewBoxCut.setText("Generar Corte Z");
        btnNewBoxCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewBoxCutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewBoxCut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnPrint)
                    .addComponent(btnNewBoxCut))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateFromPropertyChange
        aplyFilters();
    }//GEN-LAST:event_dateFromPropertyChange

    private void dateToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateToPropertyChange
        aplyFilters();
    }//GEN-LAST:event_dateToPropertyChange

    public void aplyFilters() {
        if ((dateFrom.getDate() != null) && (dateTo.getDate() != null)) {
            List<BoxCut> allRegs = DataBase.findBoxCutbyDateRange(dateFrom.getDate(), dateTo.getDate());
            filtredData = new ArrayList<>();
            for (int i = 0; i < allRegs.size(); i++) {
                filtredData.add(allRegs.get(i));
            }
            showDatabase();
        }
    }

    public void showDatabase() {
        try {
            modelo.setRowCount(0);
            if (filtredData.size() > 0) {
                for (int i = 0; i < filtredData.size(); i++) {
                    BoxCut temp = filtredData.get(i);
                    String[] newRow = new String[5];
                    newRow[0] = temp.getId();
                    newRow[1] = temp.getUser();
                    newRow[2] = temp.getDateCreatedToString();
                    newRow[3] = temp.getTimeCreatedToString();
                    modelo.addRow(newRow);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "showDatabase()", e);
        }
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        printDocument();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnNewBoxCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewBoxCutActionPerformed
        newBoxCut();
    }//GEN-LAST:event_btnNewBoxCutActionPerformed

    public void newBoxCut() {
        dataController.getInstance().genBoxCut();
        aplyFilters();
    }

    public void printDocument() {
        if (dateFrom.getDate() != null && dateFrom.getDate() != null) {
            if (new Messages().yesNoMessage("Imprimi4r Reporte", "Desea Imprimir Reporte?")) {
                printerService selectPrinter = new printerService(sessionController.getInstance().getMainFrame(), true);
                selectPrinter.setData();
                selectPrinter.show();
                if (selectPrinter.isNext()) {
                    String SelectedPrinter = selectPrinter.getSelectedPrinter();
                    if (new ticketController().printBoxCutsReport(dateFrom.getDate(), dateTo.getDate(), filtredData, SelectedPrinter)) {
                        String text = "<html>"
                                + "<center>"
                                + "<b>"
                                + "¡REPORTE IMPRESO CORRECTAMENTE!"
                                + "</b>"
                                + "<br>"
                                + "<br>"
                                + "Clic para cerrar…"
                                + "</center>"
                                + "</html>";
                        new Messages().aceptMessage("Imprimir Documento", text);
                    }
                }
            }
        }
    }

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
            java.util.logging.Logger.getLogger(allBoxCuts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(allBoxCuts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(allBoxCuts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(allBoxCuts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new allBoxCuts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnNewBoxCut;
    private javax.swing.JButton btnPrint;
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
