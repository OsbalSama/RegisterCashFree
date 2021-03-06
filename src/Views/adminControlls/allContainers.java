/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.adminControlls;

import Controllers.dataController;
import Controllers.sessionController;
import Controllers.systemController;
import Controllers.typeContainerController;
import Models.Container;
import Models.Messages;
import views.commondialogs.addTextValue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ATENEA
 */
public class allContainers extends javax.swing.JFrame {

    /**
     * Creates new form gestProds
     */
    String Class = "allContainers";
    typeContainerController dbContainers = typeContainerController.getInstance();
    DefaultTableModel modelo;

    public allContainers() {
        initComponents();
        btnClose.requestFocus();
    }

    public void loadFrame() {
        this.setTitle("Mostrar Contenedores");
        this.setIconImage(systemController.getInstance().getImageIcon().getImage());
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        loadTable();
        showDatabase();
    }

    public void setColumnSize(int Column, int Size) {
        TableColumn columna;
        int tamano = Size;
        columna = tableData.getColumnModel().getColumn(Column);
        columna.setPreferredWidth(tamano);
        columna.setMaxWidth(tamano);
        columna.setMinWidth(tamano);
    }

    public void loadTable() {
        try {
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
            modelo.addColumn("Descripci??n");
            modelo.addColumn("Creado");
            modelo.addColumn("Editado");
            tableData.setModel(modelo);

            tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableData.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent Mouse_evt) {
                    JTable table = (JTable) Mouse_evt.getSource();
                    Point point = Mouse_evt.getPoint();
                    int row = table.rowAtPoint(point);
                    if (Mouse_evt.getClickCount() == 2 && Mouse_evt.getButton() == MouseEvent.BUTTON1) {
                        try {
                            updateContainer(dbContainers.findCounterbyDesc(tableData.getValueAt(tableData.getSelectedRow(), 1).toString()));
                        } catch (Exception e) {
                            new Messages().errorMessage(Class, "loadTable()", e);
                        }
                    }
                }
            }
            );
        } catch (Exception e) {
            new Messages().errorMessage(Class, "loadTable()", e);
        }
    }

    public void updateContainer(Container Cont) {
        try {
            addTextValue contDesc = new addTextValue(null, true);
            contDesc.setData("Ver Mostrador", Cont.getDescription());
            contDesc.show();
            if (contDesc.isNext()) {
                if (contDesc.isDelete()) {
                    if (dbContainers.deleleCounter(Cont)) {
                        new Messages().closeMessage("Eliminar Mostrador", "Mostrador Eliminado...");
                    }
                } else {
                    Cont.setDescription(contDesc.getContent());
                    if (dbContainers.updateCounter(Cont)) {
                        new Messages().closeMessage("Actualizar Mostrador", "Mostrador Actualizado...");
                    }
                }
                showDatabase();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "updateContainer()", e);
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        btnNewProd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("All Containers");

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableData.setRowHeight(30);
        jScrollPane1.setViewportView(tableData);

        btnClose.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnNewProd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewProd.setText("Nuevo Mostrador");
        btnNewProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNewProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnNewProd))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnNewProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProdActionPerformed
        newReg();
    }//GEN-LAST:event_btnNewProdActionPerformed

    public void ImportDB() {
        dataController.getInstance().importDatabase();
    }

    public void ExportDB() {
        dataController.getInstance().exportDatabase();
    }

    public void downloadLayout() {
        dataController.getInstance().downloadLayout();
    }

    public void newReg() {
        try {
            addTextValue contDesc = new addTextValue(null, true);
            contDesc.setData("Nuevo Mostrador");
            contDesc.show();
            if (contDesc.isNext()) {
                if (!dbContainers.existCounter(contDesc.getContent())) {
                    Container newCont = new Container();
                    newCont.genId();
                    newCont.setDescription(contDesc.getContent());
                    if (dbContainers.createCounter(newCont)) {
                        new Messages().closeMessage("Agregar Mostrador", "Mostrador agregada con Exito...");
                        showDatabase();
                    }
                } else {
                    String text = "<html>"
                            + "<center>"
                            + "<b>"
                            + "??YA EXISTE ESTE REGISTRO!"
                            + "</b>"
                            + "<br>"
                            + "<br>"
                            + "??Desea volver a intentarlo?"
                            + "</center>"
                            + "</html>";
                    if (new Messages().yesNoMessage("Agregar Mostrador", text)) {
                        newReg();
                    }
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "newCont()", e);
        }
    }

    public void showDatabase() {
        modelo.setRowCount(0);
        try {
            for (int i = 0; i < dbContainers.getCounters().size(); i++) {
                Container temp = dbContainers.getCounters().get(i);
                String[] fila = {temp.getId() + "",
                    temp.getDescription(),
                    temp.getCreatedToString(),
                    temp.getEditedToString()};
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "showDatabase()", e);
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
            java.util.logging.Logger.getLogger(allContainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(allContainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(allContainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(allContainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new allContainers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnNewProd;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableData;
    // End of variables declaration//GEN-END:variables
}
