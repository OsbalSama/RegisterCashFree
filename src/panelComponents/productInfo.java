/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panelComponents;

import Controllers.systemController;

/**
 *
 * @author ARTEMISA
 */
public class productInfo extends javax.swing.JPanel {

    /**
     * Creates new form productInfo
     */
    systemController system = systemController.getInstance();

    public productInfo() {
        initComponents();
        btnHelp.requestFocus();
    }

    public void setData() {
        this.setName("Info. Product");
        lblAppname.setText(system.getAppName());
        lblAppVersion.setText(system.getVersion());
        lblCompanyName.setText(system.getCompanyName());
        lblDev.setText(system.getMainDev());
        lblEmail.setText(system.getMainEmail());
        lblPhrase.setText(system.getPopularPhrase());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lblPhrase = new javax.swing.JLabel();
        btnHelp = new javax.swing.JButton();
        btnWebsite = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblAppname = new javax.swing.JLabel();
        lblAppVersion = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCompanyName = new javax.swing.JLabel();
        lblDev = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnAbout = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setBorder(null);

        lblPhrase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPhrase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhrase.setText("Texto Presenfacion");
        lblPhrase.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblPhrase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPhrase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPhraseMouseClicked(evt);
            }
        });

        btnHelp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHelp.setText("Ayuda");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        btnWebsite.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnWebsite.setText("Sitio Web");
        btnWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWebsiteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Informacion del Producto");

        lblAppname.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAppname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAppname.setText("Nombre del Software");

        lblAppVersion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblAppVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAppVersion.setText("Version");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Desarrollo y Soporte:");

        lblCompanyName.setText("Proverbius Software S.A. de C.V.");

        lblDev.setText("Osbaldo Toledo Ramos - FreelancerDev");

        lblEmail.setText("Email");

        btnAbout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAbout.setText("Acerda De...");
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAppname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAppVersion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCompanyName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAbout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnWebsite)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHelp))
                            .addComponent(lblPhrase, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAppname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAppVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCompanyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPhrase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHelp)
                    .addComponent(btnWebsite)
                    .addComponent(btnAbout))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        getHelp();
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWebsiteActionPerformed
        getHomePage();
    }//GEN-LAST:event_btnWebsiteActionPerformed

    private void lblPhraseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhraseMouseClicked
        lblPhrase.setText(system.getPopularPhrase());
    }//GEN-LAST:event_lblPhraseMouseClicked

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        getAbout();
    }//GEN-LAST:event_btnAboutActionPerformed

    public void getHelp() {
        system.getContactPage();
    }

    public void getHomePage() {
        system.gethomePage();
    }

    public void getAbout() {
        system.getappPage();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnWebsite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAppVersion;
    private javax.swing.JLabel lblAppname;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblDev;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblPhrase;
    // End of variables declaration//GEN-END:variables
}
