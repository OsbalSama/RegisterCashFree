/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.userControlls;

import Controllers.dataController;
import Controllers.productController;
import Controllers.sessionController;
import Models.Messages;
import Models.Product;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ATENEA
 */
public class SelectProd extends javax.swing.JDialog {

    /**
     * Creates new form SelectProd
     */
    String Class = "SelectProd";
    List<Product> toLoad = new ArrayList<>();
    productController inventory = productController.getInstance();
    DefaultTableModel modelo;
    Product resp;
    boolean Next = false;

    public Product getResp() {
        return resp;
    }

    public void setResp(Product resp) {
        this.resp = resp;
    }

    public boolean isNext() {
        return Next;
    }

    public void setNext(boolean Next) {
        this.Next = Next;
    }

    public SelectProd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        txtBrowser.setFocusTraversalKeysEnabled(false);
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        this.setResizable(false);

    }

    public void setData() {
        loadTabla();
        aplyFilters(txtBrowser.getText());
    }

    public void aplyFilters(String Pista) {
        toLoad = new ArrayList<>();
        try {
            if (Pista.trim().length() > 0) {
                findInInventory(Pista);
            } else {
                showAllInventory();
            }
            loadFindedData();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "aplyFilters()", e);
        }
    }

    public void loadFindedData() {
        modelo.setRowCount(0);
        if (!toLoad.isEmpty()) {
            for (int i = 0; i < toLoad.size(); i++) {
                Product temp = toLoad.get(i);
                String[] Row = new String[10];
                Row[0] = temp.getId();
                Row[1] = temp.getDescription();
                Row[2] = temp.getBrand();
                Row[3] = temp.getType();
                Row[4] = temp.getContainer();
                Row[5] = dataController.getInstance().getMoneyFormat(temp.getValue());
                Row[6] = dataController.getInstance().getKgFormat(temp.getCant());
                Row[7] = "Granel";
                Row[8] = temp.getCreatedToString();
                Row[9] = temp.getEditedToString();
                modelo.addRow(Row);
            }
        }
    }

    public void showAllInventory() {
        try {
            toLoad = new ArrayList<>();
            for (int i = 0; i < inventory.getInventory().size(); i++) {
                Product temp = inventory.getInventory().get(i);
                if (temp.isBulk()) {
                    toLoad.add(temp);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "showAllInventory()", e);
        }
    }

    public void findInInventory(String Index) {
        try {
            List<Product> invent = inventory.getDBProdbyIndex(Index);
            if (invent.size() > 0) {
                for (int i = 0; i < invent.size(); i++) {
                    Product temp = invent.get(i);
                    if (temp.isBulk()) {
                        toLoad.add(temp);
                    }
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "findInInventory()", e);
        }
    }

    public void loadTabla() {
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
        modelo.addColumn("Descripción");
        modelo.addColumn("Marca");
        modelo.addColumn("Tipo");
        modelo.addColumn("Mostrador");
        modelo.addColumn("Precio");
        modelo.addColumn("Cant");
        modelo.addColumn("Granel");
        modelo.addColumn("Creado");
        modelo.addColumn("Editado");
        tableData.setModel(modelo);

        tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setColumnSize(0, 60);

        setColumnSize(2, 60);
        setColumnSize(3, 80);
        setColumnSize(4, 80);

        setColumnSize(5, 50);
        setColumnSize(6, 50);
        setColumnSize(7, 50);
        setColumnSize(8, 75);
        setColumnSize(9, 75);

        tableData.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    try {
                        setResp(inventory.findbyID(tableData.getValueAt(tableData.getSelectedRow(), 0).toString()));
                        setNext(true);
                        closeApp();
                    } catch (Exception e) {
                        new Messages().errorMessage(Class, "loadTabla()", e);
                    }
                }
            }
        }
        );
    }

    public void setColumnSize(int Column, int Size) {
        TableColumn columna;
        int tamano = Size;
        columna = tableData.getColumnModel().getColumn(Column);
        columna.setPreferredWidth(tamano);
        columna.setMaxWidth(tamano);
        columna.setMinWidth(tamano);
    }

    public void closeApp() {
        this.dispose();
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
        txtBrowser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtBrowser.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtBrowser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBrowserKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBrowserKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBrowserKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("BUSCAR");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("INVENTARIO");

        tableData.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableData.setEditingColumn(0);
        tableData.setEditingRow(0);
        tableData.setRowHeight(60);
        tableData.getTableHeader().setReorderingAllowed(false);
        tableData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableDataKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableData);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("CERRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void txtBrowserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrowserKeyReleased
        if (evt.getKeyCode() == 40) {
            selectFirstRow();
        } else {
            aplyFilters(txtBrowser.getText());
        }

    }//GEN-LAST:event_txtBrowserKeyReleased


    private void txtBrowserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrowserKeyTyped

    }//GEN-LAST:event_txtBrowserKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBrowserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrowserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closeApp();
        }

        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            selectFirstRow();
        }

    }//GEN-LAST:event_txtBrowserKeyPressed


    private void tableDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDataKeyPressed
        if (evt.getKeyCode() == 38) {
            txtBrowser.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closeApp();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tableActionPreformed();
        }
    }//GEN-LAST:event_tableDataKeyPressed

    public void tableActionPreformed() {
        setResp(inventory.findbyID(tableData.getValueAt(tableData.getSelectedRow(), 0).toString()));
        setNext(true);
        closeApp();
    }

    public void selectFirstRow() {
        if (tableData.getRowCount() > 0) {
            tableData.requestFocus();
            tableData.setRowSelectionInterval(0, 0);
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
            java.util.logging.Logger.getLogger(SelectProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SelectProd dialog = new SelectProd(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableData;
    private javax.swing.JTextField txtBrowser;
    // End of variables declaration//GEN-END:variables
}
