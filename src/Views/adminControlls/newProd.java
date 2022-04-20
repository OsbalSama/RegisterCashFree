/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.adminControlls;

import Controllers.productController;
import Controllers.sessionController;
import Controllers.typeContainerController;
import Models.Container;
import Models.Group;
import Models.Messages;
import Models.Product;
import views.commondialogs.changeTypeCont;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author terra
 */
public class newProd extends javax.swing.JDialog {

    /**
     * Creates new form newProd
     */
    String Class = "newProd";
    Product product;
    boolean updateProd = false;
    productController inventory = productController.getInstance();
    typeContainerController TypesCounters = typeContainerController.getInstance();

    public newProd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        btnCancelar.requestFocus();
        txtDesc.setFocusTraversalKeysEnabled(false);
        txtBrand.setFocusTraversalKeysEnabled(false);
        txtInventory.setFocusTraversalKeysEnabled(false);
        txtPrice.setFocusTraversalKeysEnabled(false);
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
    }

    public void updateProd(Product prod) {
        this.product = prod;
        chkgenId.setEnabled(false);
        this.setTitle("VER PRODUCTO");
        lblTitle.setText("VER PRODUCTO");
        btnAceptar.setText("Actualizar");

        txtBarcode.setText(product.getId() + "");
        txtDesc.setText(product.getDescription());
        txtBrand.setText(product.getBrand());
        txtType.setText(product.getType());
        txtContainer.setText(product.getContainer());
        txtInventory.setText(product.getInventory() + "");
        txtPrice.setText(product.getValue() + "");
        chkGranel.setSelected(product.isBulk());

        lblCreated.setText("Created: " + product.getCreatedToString());
        lblEdited.setText("Edited: " + product.getEditedToString());
        updateProd = true;
    }

    public void newProduct() {
        this.setTitle("NUEVO PRODUCTO");
        lblTitle.setText("NUEVO PRODUCTO");
        btnAceptar.setText("Guardar");
        txtDesc.requestFocus();
        txtInventory.setTransferHandler(null);
        txtPrice.setTransferHandler(null);
        btnDelete.setVisible(false);
        product = new Product();
        btnDelete.setVisible(false);
        lblCreated.setVisible(false);
        lblEdited.setVisible(false);
        chkgenId.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblEdited = new javax.swing.JLabel();
        lblCreated = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        chkgenId = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtBrand = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtInventory = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtType = new javax.swing.JTextField();
        btnChangeType = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtContainer = new javax.swing.JTextField();
        btnChangeContainer = new javax.swing.JButton();
        chkGranel = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear nuevo producto");
        setBackground(new java.awt.Color(255, 0, 0));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAceptar.setText("Save/Update");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblTitle.setBackground(new java.awt.Color(0, 0, 0));
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("AÑADIR A INVENTARIO");

        lblEdited.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblEdited.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdited.setText("Edited: 00/00/0000");

        lblCreated.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCreated.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreated.setText("Created: 00/00/0000");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Barcode");

        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });
        txtBarcode.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtBarcodePropertyChange(evt);
            }
        });
        txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyReleased(evt);
            }
        });

        chkgenId.setText("Autogen");
        chkgenId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkgenId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkgenIdItemStateChanged(evt);
            }
        });

        txtDesc.setColumns(20);
        txtDesc.setLineWrap(true);
        txtDesc.setRows(5);
        txtDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtDesc);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Descripción");

        txtBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrandActionPerformed(evt);
            }
        });
        txtBrand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBrandKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Marca");

        txtInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInventoryActionPerformed(evt);
            }
        });
        txtInventory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInventoryKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel6.setText("Precio");

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPriceKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("Existencias");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setText("Tipo");

        txtType.setEditable(false);
        txtType.setText("SIN CATEGORIA");
        txtType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeActionPerformed(evt);
            }
        });

        btnChangeType.setText("Cambiar");
        btnChangeType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeTypeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel13.setText("Mostrador");

        txtContainer.setEditable(false);
        txtContainer.setText("MOSTRADOR GENERAL");

        btnChangeContainer.setText("Cambiar");
        btnChangeContainer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeContainerActionPerformed(evt);
            }
        });

        chkGranel.setText("Vender a Ganel");
        chkGranel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBarcode)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCreated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEdited, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBrand, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtInventory))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangeType))
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtContainer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangeContainer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(chkgenId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkGranel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdited)
                    .addComponent(lblCreated))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkgenId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkGranel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeContainer))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        actionPreformed();
    }//GEN-LAST:event_btnAceptarActionPerformed

    public boolean checkFields() {
        boolean resp = false;
        if (txtBarcode.getText().trim().replace(" ", "").length() > 0 && product.getId() != null) {
            if (txtDesc.getText().trim().replace(" ", "").length() > 0) {
                if (txtBrand.getText().trim().replace(" ", "").length() > 0) {
                    if (txtInventory.getText().trim().replace(" ", "").length() > 0) {
                        if (txtPrice.getText().trim().replace(" ", "").length() > 0) {
                            if (inventory.findbyDesc(txtDesc.getText()) == null) {
                                if (inventory.findbyID(txtBarcode.getText()) == null) {
                                    if (txtType.getText().trim().length() > 0) {
                                        if (txtContainer.getText().trim().length() > 0) {
                                            resp = true;
                                        } else {
                                            new Messages().aceptMessage("Agregar al Inventario", "Falta asignar un Mostrador al Producto... Vuelva a intentarlo");
                                            btnChangeContainer.requestFocus();
                                        }
                                    } else {
                                        new Messages().aceptMessage("Agregar al Inventario", "Falta asignar una Categoria al Producto... Vuelva a intentarlo");
                                        btnChangeType.requestFocus();
                                    }
                                } else {
                                    new Messages().aceptMessage("Agregar al Inventario", "Ya existe un Producto registrado con este Codigo de Barras... Vuelva a intentarlo");
                                    txtBarcode.requestFocus();
                                }
                            } else {
                                new Messages().aceptMessage("Agregar al Inventario", "Ya existe un Producto registrado con esta Descripcion... Vuelva a intentarlo");
                                txtDesc.requestFocus();
                            }
                        } else {
                            txtPrice.requestFocus();
                        }
                    } else {
                        txtInventory.requestFocus();
                    }
                } else {
                    txtBrand.requestFocus();
                }
            } else {
                txtDesc.requestFocus();
            }
        } else {
            txtBarcode.requestFocus();
        }
        return resp;
    }

    public boolean checkFieldstoUpdate() {
        boolean resp = false;
        if (txtBarcode.getText().replace(" ", "").length() > 0) {
            if (txtDesc.getText().replace(" ", "").length() > 0) {
                if (txtBrand.getText().replace(" ", "").length() > 0) {
                    if (txtInventory.getText().replace(" ", "").length() > 0) {
                        if (txtPrice.getText().replace(" ", "").length() > 0) {
                            resp = true;
                        } else {
                            txtPrice.requestFocus();
                        }
                    } else {
                        txtInventory.requestFocus();
                    }
                } else {
                    txtBrand.requestFocus();
                }
            } else {
                txtDesc.requestFocus();
            }
        } else {
            txtBarcode.requestFocus();
        }
        return resp;
    }

    public void updateProd() {
        if (checkFieldstoUpdate()) {
            product.setDescription(txtDesc.getText());
            product.setBrand(txtBrand.getText());
            product.setType(txtType.getText());
            product.setContainer(txtContainer.getText());
            product.setValue(Double.parseDouble(txtPrice.getText()));
            product.setInventory(Double.parseDouble(txtInventory.getText()));
            if (chkGranel.isSelected()) {
                product.setBulk(true);
            } else {
                product.setBulk(false);
            }
            product.setEdited();
            String text = "<html>"
                    + "<center>"
                    + "<b>"
                    + "¡SE ACTUALIZARAN LOS DATOS ASIGNADOS!"
                    + "</b>"
                    + "<br>"
                    + "<br>"
                    + "¿Desea continuar?"
                    + "</center>"
                    + "</html>";
            if (new Messages().confirmMessage("Actualizar Producto", text)) {
                if (inventory.update(product)) {
//                    new Messages().closeMessage("Actualizar Producto", "Producto Actualizado con Exito...");
                    this.dispose();
                }
            }
        }
    }

    public void createProd() {
        if (checkFields()) {
            product.setDescription(txtDesc.getText());
            product.setBrand(txtBrand.getText());
            product.setType(txtType.getText());
            product.setContainer(txtContainer.getText());
            product.setValue(Double.parseDouble(txtPrice.getText()));
            product.setInventory(Float.parseFloat(txtInventory.getText()));
            if (chkGranel.isSelected()) {
                product.setBulk(true);
            } else {
                product.setBulk(false);
            }
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
            if (new Messages().confirmMessage("Agregar al Inventario", text)) {
                if (inventory.create(product)) {

                    Group newReg = new Group();
                    newReg.genId();
                    newReg.setDescription(product.getType());
                    typeContainerController.getInstance().createType(newReg);

                    Container newCont = new Container();
                    newCont.genId();
                    newCont.setDescription(product.getContainer());
                    typeContainerController.getInstance().createCounter(newCont);
                    this.dispose();
                }
            }
        }
    }

    public void actionPreformed() {
        try {
            if (updateProd == true) {
                updateProd();
            } else {
                createProd();
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "saveUpdateProd()", e);
        }

    }
    private void txtPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrice.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPriceKeyTyped

    private void txtInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInventoryActionPerformed
        txtPrice.requestFocus();
    }//GEN-LAST:event_txtInventoryActionPerformed

    private void txtInventoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInventoryKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtInventoryKeyTyped

    private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyReleased

    }//GEN-LAST:event_txtDescKeyReleased

    private void txtBrandKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrandKeyReleased

    }//GEN-LAST:event_txtBrandKeyReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delProd();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtDescKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();
            txtBrand.requestFocus();
        }
    }//GEN-LAST:event_txtDescKeyPressed

    private void txtBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrandActionPerformed
        txtInventory.requestFocus();
    }//GEN-LAST:event_txtBrandActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        actionPreformed();
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyReleased
        if (!txtPrice.getText().replace(" ", "").equals("")) {
            if (txtPrice.getText().equals(".")) {
                txtPrice.setText("0.");
            }
        }
    }//GEN-LAST:event_txtPriceKeyReleased

    private void chkgenIdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkgenIdItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            product.setId(null);
            product.genId();
            txtBarcode.setText(product.getId() + "");
            txtBarcode.setEditable(false);
        } else {
            product.setId(null);
            txtBarcode.setEditable(true);
            txtBarcode.setText("");
        }
    }//GEN-LAST:event_chkgenIdItemStateChanged

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        verifyBarcode();
    }//GEN-LAST:event_txtBarcodeActionPerformed

    public void verifyBarcode() {
        try {
            if (!txtBarcode.getText().replace(" ", "").equals("")) {
                if (inventory.findbyID(product.getId()) != null) {
                    new Messages().aceptMessage("Agregar al Inventario", "Existe un Producto registrado con este Codigo de Barras... Vuelva a intentarlo");
                    txtBarcode.setBackground(Color.yellow);
                } else {
                    txtBarcode.setBackground(Color.WHITE);
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "verifyBarcode()", e);
        }
    }

    private void txtBarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyReleased
        txtBarcode.setText(txtBarcode.getText().replace(" ", ""));
        writeID(txtBarcode.getText());
    }//GEN-LAST:event_txtBarcodeKeyReleased

    private void txtBarcodePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtBarcodePropertyChange

    }//GEN-LAST:event_txtBarcodePropertyChange

    private void txtTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTypeActionPerformed

    private void btnChangeTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeTypeActionPerformed
        changeType();
    }//GEN-LAST:event_btnChangeTypeActionPerformed

    private void btnChangeContainerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeContainerActionPerformed
        changeContainer();
    }//GEN-LAST:event_btnChangeContainerActionPerformed

    public void changeType() {
        try {
            changeTypeCont ctc = new changeTypeCont(null, true);
            ctc.loadCats();
            ctc.show();
            if (ctc.isNext()) {
                txtType.setText(ctc.getRespCat().getDescription());
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "changeType()", e);
        }
    }

    public void changeContainer() {
        try {
            changeTypeCont ctc = new changeTypeCont(null, true);
            ctc.loadConts();
            ctc.show();
            if (ctc.isNext()) {
                txtContainer.setText(ctc.getRespCont().getDescription());
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "changeContainer()", e);
        }
    }

    public void writeID(String text) {
        if (product != null) {
            product.setId(null);
            if (text.replace(" ", "").length() > 0) {
                product.setId(text);
            }
        }

//        txtBarcode.setText(product.getId() + "");
    }

    public void delProd() {
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
        if (new Messages().confirmMessage("Eliminar Producto", text)) {
            if (inventory.delele(product)) {
//                new Messages().aceptMessage("Eliminar Producto", "Producto eliminado...");
                dispose();
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
            java.util.logging.Logger.getLogger(newProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                newProd dialog = new newProd(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnChangeContainer;
    private javax.swing.JButton btnChangeType;
    private javax.swing.JButton btnDelete;
    private javax.swing.JCheckBox chkGranel;
    private javax.swing.JCheckBox chkgenId;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCreated;
    private javax.swing.JLabel lblEdited;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtContainer;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JTextField txtInventory;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
