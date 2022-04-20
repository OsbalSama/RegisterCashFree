/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.commondialogs;

import Controllers.sessionController;
import Controllers.typeContainerController;
import Models.Container;
import Models.Group;
import Models.Messages;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARTEMISA
 */
public class changeTypeCont extends javax.swing.JDialog {

    /**
     * Creates new form changeTypeCont
     */
    String Class = "changeTypeCont";
    typeContainerController Database = typeContainerController.getInstance();
    DefaultTableModel modelo;

    boolean Next = false;
    boolean cats;

    Group respCat;
    Container respCont;

    public boolean isCats() {
        return cats;
    }

    public void setCats(boolean cats) {
        this.cats = cats;
    }

    public Group getRespCat() {
        return respCat;
    }

    public void setRespCat(Group respCat) {
        this.respCat = respCat;
    }

    public Container getRespCont() {
        return respCont;
    }

    public void setRespCont(Container respCont) {
        this.respCont = respCont;
    }

    public boolean isNext() {
        return Next;
    }

    public void setNext(boolean Next) {
        this.Next = Next;
    }

    public changeTypeCont(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        this.setResizable(false);
        btnNewReg.setFocusTraversalKeysEnabled(false);
        tblData.setFocusTraversalKeysEnabled(false);
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

        modelo.addColumn("Content");

        tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    try {
                        actionNext();
                    } catch (Exception e) {
                        new Messages().errorMessage(Class, "loadTable()", e);
                    }
                }
            }
        });
        tblData.setModel(modelo);
    }

    public void loadCats() {
        List<Group> allCats = Database.getTypes();
        lblTitulo.setText("Seleccionar Categoria");
        setCats(true);
        loadTable();
        if (allCats.size() > 0) {
            for (int i = 0; i < allCats.size(); i++) {
                String[] fila = {allCats.get(i).getDescription()};
                modelo.addRow(new String[]{allCats.get(i).getDescription()});
            }
        } else {
            modelo.addRow(new String[]{"SIN CATEGORIAS"});
        }
        tblData.setRowSelectionInterval(0, 0);
        tblData.requestFocus();
    }

    public void loadConts() {
        List<Container> allConts = Database.getCounters();
        lblTitulo.setText("Seleccionar Mostrador");
        loadTable();
        if (allConts.size() > 0) {
            for (int i = 0; i < allConts.size(); i++) {
                modelo.addRow(new String[]{allConts.get(i).getDescription()});
            }
        } else {
            modelo.addRow(new String[]{"SIN MOSTRADORES"});
        }
        tblData.setRowSelectionInterval(0, 0);
        tblData.requestFocus();
    }

    public void actionPreformed() {
        try {
            List<Group> allCats = Database.getTypes();
            if (allCats.size() > 0 || getRespCat() != null) {
                setNext(true);
                setRespCat(allCats.get(tblData.getSelectedRow()));
                this.dispose();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "actionPreformed()", e);
        }
    }

    public void actionPreformed2() {
        try {
            List<Container> allConts = Database.getCounters();
            if ((allConts.size() > 0) || getRespCont() != null) {
                setNext(true);
                setRespCont(allConts.get(tblData.getSelectedRow()));
                this.dispose();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "actionPreformed2()", e);
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

        lblTitulo = new javax.swing.JLabel();
        btnNewReg = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setText("TITULO");

        btnNewReg.setText("Agregar");
        btnNewReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRegActionPerformed(evt);
            }
        });
        btnNewReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNewRegKeyPressed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblData.setRowHeight(30);
        tblData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDataKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewReg)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(btnNewReg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    private void btnNewRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRegActionPerformed
        addNewReg();
        tblData.requestFocus();
    }//GEN-LAST:event_btnNewRegActionPerformed

    private void tblDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnCancel();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            actionNext();
        }

    }//GEN-LAST:event_tblDataKeyPressed

    private void btnNewRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNewRegKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnCancel();
        }
    }//GEN-LAST:event_btnNewRegKeyPressed

    public void btnCancel() {
        this.dispose();
    }

    public void addNewReg() {
        if (isCats()) {
            addTextValue regDesc = new addTextValue(null, true);
            regDesc.setData("Nueva Categoria");
            regDesc.show();
            if (regDesc.isNext()) {
                if (!Database.existType(regDesc.getContent())) {
                    Group newReg = new Group();
                    newReg.genId();
                    newReg.setDescription(regDesc.getContent());
                    if (Database.createType(newReg)) {
//                        new Messages().closeMessage("Agregar Categoria", "Categoria agregada con Exito...");
                        loadCats();
                    }
                } else {
                    if (new Messages().yesNoMessage("Agregar Categoria", "Esta Categoria ya existe... desea vovler a intentarlo?")) {
                        addNewReg();
                    }
                }
            }
        } else {
            try {
                addTextValue contDesc = new addTextValue(null, true);
                contDesc.setData("Nuevo Mostrador");
                contDesc.show();
                if (contDesc.isNext()) {
                    if (!Database.existCounter(contDesc.getContent())) {
                        Container newCont = new Container();
                        newCont.genId();
                        newCont.setDescription(contDesc.getContent());
                        if (Database.createCounter(newCont)) {
                            loadConts();
                        }
                    } else {
                        if (new Messages().yesNoMessage("Agregar Mostrador", "Este Mostrador ya existe... desea vovler a intentarlo?")) {
                            addNewReg();
                        }
                    }
                }
            } catch (Exception e) {
                new Messages().errorMessage(Class, "newCont()", e);
            }
        }
    }

    public void actionNext() {
        if (isCats()) {
            actionPreformed();
        } else {
            actionPreformed2();
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
            java.util.logging.Logger.getLogger(changeTypeCont.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(changeTypeCont.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(changeTypeCont.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(changeTypeCont.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                changeTypeCont dialog = new changeTypeCont(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnNewReg;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
