/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.userControlls;

import Controllers.dataController;
import Controllers.productController;
import Controllers.sessionController;
import Controllers.systemController;
import Models.Messages;
import Models.Product;
import Views.adminControlls.newProd;
import Views.adminControlls.setBulkCant;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import panelComponents.depMenu;

/**
 *
 * @author ATENEA
 */
public class userHome extends javax.swing.JFrame {

    /**
     * Creates new form userHome
     */
    String Class = "userHome";

    DefaultTableModel modelo;
    List<Product> cart = new ArrayList<>();
    List<Product> browse = new ArrayList<>();
    productController inventory = productController.getInstance();
    sessionController session = sessionController.getInstance();

    //BrowseBar GoogleStyle
    JPopupMenu popBrowser;
    JPanel pnlBrowser;
    JList<String> lisBrowser;

    //AccountSettings Like Android
    JPopupMenu popAccountSettings;
    JPanel pnlAccountSettings;
    JList<String> listAccountSettings;

    public userHome() {
        this.setUndecorated(true);
        initComponents();
    }

    public void loadUserIcon() {
        lblLoged.setText("");
        lblLoged.setIcon(systemController.getInstance().getmenuIcon(pnlDeployMenu.getWidth(), pnlDeployMenu.getHeight()));
    }

    public void loadAccountSettings() {
        popAccountSettings = new JPopupMenu();
        pnlAccountSettings = new JPanel();
        listAccountSettings = new JList<>();

        popAccountSettings.setFocusTraversalKeysEnabled(false);
        pnlAccountSettings.setFocusTraversalKeysEnabled(false);
        listAccountSettings.setFocusTraversalKeysEnabled(false);

        popAccountSettings.setBackground(Color.WHITE);
        depMenu deployable = new depMenu();
        deployable.setData();
        popAccountSettings.add(deployable);
    }

    public void setData() {
        loadFrame();
        loadTable();
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
        modelo.addColumn("Descripci??n");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio");
        modelo.addColumn("Cant");
        modelo.addColumn("Total");
        tblCart.setModel(modelo);

        tblCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void loadFrame() {
        txtBrowser.setFocusTraversalKeysEnabled(false);
        tblCart.setFocusTraversalKeysEnabled(false);
        btnGranel.setFocusTraversalKeysEnabled(false);
        btnPayment.setFocusTraversalKeysEnabled(false);
        btnGranel.setFocusTraversalKeysEnabled(false);

        loadUserIcon();
        loadAccountSettings();

        cart = new ArrayList<>();
        this.setTitle("Wellcome to " + systemController.getInstance().getAppName());
        this.setIconImage(systemController.getInstance().getImageIcon().getImage());
        this.setLocationRelativeTo(sessionController.getInstance().getShowedFrame());
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Salir();
            }
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cartTotal();
    }

    public void cartTotal() {
        try {
            txtTotal.setText(dataController.getInstance().getMoneyFormat(dataController.getInstance().getCartTotal(cart)));
        } catch (Exception e) {
            new Messages().errorMessage(Class, "cartTotal()", e);
        }
    }

    //CLASES
    public void Salir() {
        sessionController.getInstance().closeApp();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCart = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBrowser = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        txtTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnGranel = new javax.swing.JButton();
        pnlDeployMenu = new javax.swing.JPanel();
        lblLoged = new javax.swing.JLabel();
        mainMenuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuLogout = new javax.swing.JMenuItem();
        menuExitApp = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuAdmin = new javax.swing.JMenu();
        menuCart = new javax.swing.JMenu();
        menuSell = new javax.swing.JMenuItem();
        menuCancel = new javax.swing.JMenuItem();
        menuFind = new javax.swing.JMenuItem();
        menuGranel = new javax.swing.JMenuItem();
        menuNewCorteZ = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuGetHelp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Buscar");

        txtBrowser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrowserActionPerformed(evt);
            }
        });
        txtBrowser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBrowserKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBrowserKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBrowser)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblCart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCart.setRowHeight(30);
        tblCart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCartKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCart);

        txtTotal.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotal.setText("$0.00");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCancel.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnPayment.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        btnPayment.setText("Cobrar");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        btnGranel.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        btnGranel.setText("Granel");
        btnGranel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGranelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGranel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPayment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnPayment)
                    .addComponent(btnGranel))
                .addContainerGap())
        );

        pnlDeployMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlDeployMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDeployMenuMouseClicked(evt);
            }
        });

        lblLoged.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLoged.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlDeployMenuLayout = new javax.swing.GroupLayout(pnlDeployMenu);
        pnlDeployMenu.setLayout(pnlDeployMenuLayout);
        pnlDeployMenuLayout.setHorizontalGroup(
            pnlDeployMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLoged, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlDeployMenuLayout.setVerticalGroup(
            pnlDeployMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLoged, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout pnlCartLayout = new javax.swing.GroupLayout(pnlCart);
        pnlCart.setLayout(pnlCartLayout);
        pnlCartLayout.setHorizontalGroup(
            pnlCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCartLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDeployMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCartLayout.setVerticalGroup(
            pnlCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDeployMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuFile.setText("Archivo");
        menuFile.add(jSeparator1);

        menuLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuLogout.setText("Cerrar Sesi??n");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        menuFile.add(menuLogout);

        menuExitApp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuExitApp.setText("Salir");
        menuExitApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitAppActionPerformed(evt);
            }
        });
        menuFile.add(menuExitApp);
        menuFile.add(jSeparator2);
        menuFile.add(jSeparator3);

        mainMenuBar.add(menuFile);

        menuAdmin.setText("Administracion");

        menuCart.setText("Carrito de Compras");

        menuSell.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_MASK));
        menuSell.setText("Cobrar");
        menuSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSellActionPerformed(evt);
            }
        });
        menuCart.add(menuSell);

        menuCancel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuCancel.setText("Cancelar");
        menuCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCancelActionPerformed(evt);
            }
        });
        menuCart.add(menuCancel);

        menuFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuFind.setText("Encontrar");
        menuFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFindActionPerformed(evt);
            }
        });
        menuCart.add(menuFind);

        menuGranel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuGranel.setText("Granel");
        menuGranel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGranelActionPerformed(evt);
            }
        });
        menuCart.add(menuGranel);

        menuNewCorteZ.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuNewCorteZ.setText("Nuevo Corte Z");
        menuNewCorteZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewCorteZActionPerformed(evt);
            }
        });
        menuCart.add(menuNewCorteZ);

        menuAdmin.add(menuCart);

        mainMenuBar.add(menuAdmin);

        menuHelp.setText("Ayuda");

        menuGetHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuGetHelp.setText("Obtener Ayuda");
        menuGetHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGetHelpActionPerformed(evt);
            }
        });
        menuHelp.add(menuGetHelp);

        mainMenuBar.add(menuHelp);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        Loggout();
    }//GEN-LAST:event_menuLogoutActionPerformed

    public void Loggout() {
        sessionController.getInstance().Loggout();
    }

    private void menuExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitAppActionPerformed
        Salir();
    }//GEN-LAST:event_menuExitAppActionPerformed

    public void infoProd() {
        systemController.getInstance().infoProd();
    }

    private void menuSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSellActionPerformed
        Cobrar();
    }//GEN-LAST:event_menuSellActionPerformed

    public void Cobrar() {
        try {
            if (!cart.isEmpty()) {
                if (dataController.getInstance().saveSale(cart)) {
                    new Messages().closeMessage("Comprar Carrito", "Operacion Terminada Correctamente...");
                    cart = new ArrayList<>();
                    loadCart();
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "Cobrar()", e);
        }
    }

    public void loadCart() {
        modelo.setRowCount(0);
        try {
//            double cartTotal = 0;
            for (int i = 0; i < cart.size(); i++) {
                Product temp = cart.get(i);
                String[] newRow = new String[6];
                newRow[0] = temp.getId();
                newRow[1] = temp.getDescription();
                newRow[2] = temp.getBrand();
                newRow[3] = dataController.getInstance().getMoneyFormat(temp.getValue());
                if (temp.isBulk()) {
                    newRow[4] = dataController.getInstance().getKgFormat(temp.getCant());
                } else {
                    newRow[4] = dataController.getInstance().getPzFormat(temp.getCant());
                }
                newRow[5] = dataController.getInstance().getMoneyFormat(temp.getValue() * temp.getCant());
                modelo.addRow(newRow);
            }
            cartTotal();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "loadCart()", e);
        }
    }

    private void menuCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCancelActionPerformed
        Cancelar();
    }//GEN-LAST:event_menuCancelActionPerformed

    public void Cancelar() {
        if (!cart.isEmpty()) {
            if (new Messages().confirmMessage("Cancelar Operacion", "??Est?? seguro que desea Cancelar la Compra?")) {
                cart = new ArrayList<>();
                loadCart();
            }
        }
    }

    private void menuFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFindActionPerformed
        findProduct();
    }//GEN-LAST:event_menuFindActionPerformed

    public void findProduct() {
        txtBrowser.setBackground(Color.yellow);
        txtBrowser.requestFocus();
    }

    private void menuGranelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGranelActionPerformed
        Granel();
    }//GEN-LAST:event_menuGranelActionPerformed

    private void menuNewCorteZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewCorteZActionPerformed
        dataController.getInstance().genBoxCut();
    }//GEN-LAST:event_menuNewCorteZActionPerformed

    private void menuGetHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGetHelpActionPerformed
        getHelp();
    }//GEN-LAST:event_menuGetHelpActionPerformed

    public void getHelp() {
        systemController.getInstance().getappPage();
    }

    private void txtBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrowserActionPerformed
        txtBrowserFuntion();
    }//GEN-LAST:event_txtBrowserActionPerformed

    public void newProd() {
        newProd np = new newProd(this, true);
        np.newProduct();
        np.show();
    }

    public void txtBrowserFuntion() {
        if (txtBrowser.getText().trim().length() > 0) {
            if (!browse.isEmpty()) {
                addProductInCart(browse.get(0));
            } else {
                if (new Messages().yesNoMessage("No Encontrado", "El producto que buscas no existe... Deseas agregarlo?")) {
                    newProd();
                }
            }
            loadCart();
            txtBrowser.setText("");
            txtBrowser.requestFocus();
        }
    }

    public boolean addProductInCart(Product findedProduct) {
        boolean resp = false;
        try {
            if (!findedProduct.noInventory()) {
                if (cart.contains(findedProduct)) {
                    for (int i = 0; i < cart.size(); i++) {
                        if (cart.get(i).getId().equals(findedProduct.getId())) {
                            Product inCart = cart.get(i);
                            if (findedProduct.canBeAdded(inCart.getCant())) {
                                if (inCart.isBulk()) {
                                    setBulkCant ap = new setBulkCant(this, true);
                                    ap.setData(findedProduct);
                                    ap.show();
                                    if (ap.isNext()) {
                                        cart.get(i).setCant(ap.getNewCant());
                                    }
                                } else {
                                    cart.get(i).addCant();
                                }
                                resp = true;
                            } else {
                                inventory.maxProductInventory(findedProduct);
                            }
                            break;
                        }
                    }
                } else {
                    if (findedProduct.isBulk()) {
                        setBulkCant ap = new setBulkCant(this, true);
                        ap.setData(findedProduct);
                        ap.show();
                        if (ap.isNext()) {
                            findedProduct.setCant(ap.getNewCant());
                            cart.add(findedProduct);
                        }
                    } else {
                        cart.add(findedProduct);
                    }
                    resp = true;
                }

            } else {
                inventory.noProductInventory(findedProduct);
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "addProductInCart()", e);
        }
        loadCart();
        return resp;
    }

    private void txtBrowserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrowserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                if (!cart.isEmpty()) {
                    tblCart.requestFocus();
                    tblCart.setRowSelectionInterval(0, 0);
                }
            } catch (Exception e) {
                new Messages().errorMessage(Class, "txtBrowserKeyPressed()", e);
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            try {
                txtBrowser.setText("");
            } catch (Exception e) {
                new Messages().errorMessage(Class, "txtBrowserKeyPressed()", e);
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                if (!browse.isEmpty()) {
                    lisBrowser.requestFocus();
                    lisBrowser.setSelectedIndex(0);
                } else if (browse.isEmpty()) {
                    if (!cart.isEmpty()) {
                        tblCart.requestFocus();
                        tblCart.setRowSelectionInterval(0, 0);
                    }
                }
            } catch (Exception e) {
                new Messages().errorMessage(Class, "txtBrowserKeyPressed()", e);
            }
        }
    }//GEN-LAST:event_txtBrowserKeyPressed

    private void txtBrowserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrowserKeyReleased
        txtBrowser.setBackground(Color.WHITE);
        applyFilters();
    }//GEN-LAST:event_txtBrowserKeyReleased

    public void applyFilters() {
        String Index = txtBrowser.getText().trim();
        if (Index.length() > 0) {
            browse = inventory.getDBProdbyIndex(Index);
            if (!browse.isEmpty()) {
                enableBrowserList();
            } else {
                browse = new ArrayList<>();
                disableBrowserList();
            }
        } else {
            browse = new ArrayList<>();
            disableBrowserList();
        }
    }

    public void disableBrowserList() {
        if ((popBrowser != null)) {
            popBrowser.setVisible(false);
        }
    }

    public void enableBrowserList() {
        try {
            popBrowser = new JPopupMenu();
            pnlBrowser = new JPanel();
            lisBrowser = new JList<>();

            popBrowser.setFocusTraversalKeysEnabled(false);
            pnlBrowser.setFocusTraversalKeysEnabled(false);
            lisBrowser.setFocusTraversalKeysEnabled(false);

            pnlBrowser.setBackground(Color.WHITE);

            DefaultListModel<String> model = new DefaultListModel<>();
            for (int i = 0; i < browse.size(); i++) {
                String bulk = "";
                if (browse.get(i).isBulk()) {
                    bulk = "Granel";
                } else {
                    bulk = "Unit";
                }
                String Item = "BARCODE: [" + browse.get(i).getId() + "] DESCRIPTION: [" + browse.get(i).getDescription() + ", " + browse.get(i).getBrand() + "] INVENTORY: [" + browse.get(i).getInventory() + "] Sale As [" + bulk + "]";
                model.addElement(Item);
            }

            loadBrowseBar();

            lisBrowser.setModel(model);
            pnlBrowser.add(lisBrowser);
            popBrowser.add(pnlBrowser);

            Dimension maximunSize = new Dimension(txtBrowser.getWidth(), (this.getHeight() / 4));
            popBrowser.setMaximumSize(maximunSize);
            lisBrowser.setMaximumSize(maximunSize);

            Dimension preferenSize = new Dimension(txtBrowser.getWidth(), ((this.getHeight() / 4)));
            lisBrowser.setPreferredSize(preferenSize);

            popBrowser.show(txtBrowser, 0, txtBrowser.getHeight());
            txtBrowser.requestFocus();

        } catch (Exception e) {
            new Messages().errorMessage(Class, "enableBrowserList()", e);
        }
    }

    public void loadBrowseBar() {
        lisBrowser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    try {
                        addProductInCart(browse.get(lisBrowser.getSelectedIndex()));
                    } catch (Exception e) {
                        new Messages().errorMessage(Class, "loadBrowser()", e);
                    }
                    txtBrowser.setText("");
                    txtBrowser.requestFocus();
                }
            }
        });

        lisBrowser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    try {
                        if (lisBrowser.getSelectedIndex() == 0) {
                            txtBrowser.requestFocus();
                        }
                    } catch (Exception e) {
                        new Messages().errorMessage(Class, "loadBrowser()", e);
                    }
                }

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        addProductInCart(browse.get(lisBrowser.getSelectedIndex()));
                        txtBrowser.setText("");
                        txtBrowser.requestFocus();
                    } catch (Exception e) {
                        new Messages().errorMessage(Class, "loadBrowser()", e);
                    }
                    txtBrowser.setText("");
                    txtBrowser.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    try {
                        txtBrowser.setText("");
                        txtBrowser.requestFocus();
                    } catch (Exception e) {
                        new Messages().errorMessage(Class, "loadBrowser()", e);
                    }

                }
            }
        });
    }


    private void tblCartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCartKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            try {
                if (tblCart.getSelectedRow() == 0) {
                    txtBrowser.requestFocus();
                }
            } catch (Exception e) {
                new Messages().errorMessage(Class, "tblCartKeyPressed()", e);
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            try {
                int selectedRow = tblCart.getSelectedRow();
                if (removeProductToCant(selectedRow)) {
                    txtBrowser.requestFocus();
                } else {
                    tblCart.setRowSelectionInterval(selectedRow, 0);
                }
            } catch (Exception e) {
                new Messages().errorMessage(Class, "tblCartKeyPressed()", e);
            }
        }
    }//GEN-LAST:event_tblCartKeyPressed

    public boolean removeProductToCant(int Posicion) {
        boolean resp = false;
        if (new Messages().yesNoMessage("Eliminar Producto", "Desea eliminar este producto del carrito?")) {
            cart.remove(Posicion);
            resp = true;
        }
        loadCart();
        return resp;
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Cancelar();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        Cobrar();
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnGranelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGranelActionPerformed
        Granel();
    }//GEN-LAST:event_btnGranelActionPerformed

    public void Granel() {
        try {
            if (!cart.isEmpty()) {
                setBulkCant ap = new setBulkCant(this, true);
                ap.setData();
                ap.show();
                if (ap.isNext()) {
                    Product temp = ap.getProd();
                    if (cart.contains(temp)) {
                        for (int i = 0; i < cart.size(); i++) {
                            if (cart.get(i).getId().equals(temp.getId())) {
                                cart.get(i).setCant(ap.getNewCant());
                                break;
                            }
                        }
                    } else {
                        cart.add(ap.getProd());
                    }
                }

            } else {
                setBulkCant ap = new setBulkCant(this, true);
                ap.setData();
                ap.show();
                if (ap.isNext()) {
                    Product temp = ap.getProd();
                    temp.setCant(ap.getNewCant());
                    cart.add(ap.getProd());
                }
            }
        } catch (Exception e) {
            new Messages().errorMessage(Class, "Granel()", e);
        }
        loadCart();
    }

    private void pnlDeployMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDeployMenuMouseClicked
        try {
            enableAccountSettings();
        } catch (Exception e) {
            new Messages().errorMessage(Class, "jPanel3MouseClicked()", e);
        }
    }//GEN-LAST:event_pnlDeployMenuMouseClicked
    public void enableAccountSettings() {
        popAccountSettings.show(pnlDeployMenu, (pnlDeployMenu.getWidth() * -1), pnlDeployMenu.getHeight());
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
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGranel;
    private javax.swing.JButton btnPayment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblLoged;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu menuAdmin;
    private javax.swing.JMenuItem menuCancel;
    private javax.swing.JMenu menuCart;
    private javax.swing.JMenuItem menuExitApp;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFind;
    private javax.swing.JMenuItem menuGetHelp;
    private javax.swing.JMenuItem menuGranel;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenuItem menuNewCorteZ;
    private javax.swing.JMenuItem menuSell;
    private javax.swing.JPanel pnlCart;
    private javax.swing.JPanel pnlDeployMenu;
    private javax.swing.JTable tblCart;
    private javax.swing.JTextField txtBrowser;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
