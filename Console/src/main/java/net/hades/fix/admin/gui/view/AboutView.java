/*
 * Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * AboutView.java
 *
 * Created on 19/02/2012, 12:06:09 PM
 */
package net.hades.fix.admin.gui.view;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.UIManager;

/**
 * About view.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class AboutView extends JDialog {

    /**
     * Creates new form AboutView
     * @param parent
     * @param modal  
     */
    public AboutView(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel versionLabel = new javax.swing.JLabel();
        javax.swing.JLabel homepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVersionLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel appHomepageLabel = new javax.swing.JLabel();
        appTitleLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("net/hades/fix/admin/gui/resources/HadesAdminConsole"); // NOI18N
        setTitle(bundle.getString("AboutView.title")); // NOI18N
        setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        setMaximumSize(new java.awt.Dimension(500, 300));
        setMinimumSize(new java.awt.Dimension(500, 250));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("AboutDialog");
        setPreferredSize(new java.awt.Dimension(500, 250));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        vendorLabel.setText(bundle.getString("AboutView.vendorLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        getContentPane().add(vendorLabel, gridBagConstraints);

        versionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        versionLabel.setText(bundle.getString("AboutView.versionLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(versionLabel, gridBagConstraints);

        homepageLabel.setText(bundle.getString("AboutView.homepageLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        getContentPane().add(homepageLabel, gridBagConstraints);

        appVersionLabel.setText(bundle.getString("Application.version")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        getContentPane().add(appVersionLabel, gridBagConstraints);

        appVendorLabel.setText(bundle.getString("Application.vendor")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        getContentPane().add(appVendorLabel, gridBagConstraints);

        appHomepageLabel.setText(bundle.getString("Application.homepage")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        getContentPane().add(appHomepageLabel, gridBagConstraints);

        appTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        appTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appTitleLabel.setText(bundle.getString("Application.description")); // NOI18N
        appTitleLabel.setAlignmentY(0.0F);
        appTitleLabel.setFocusable(false);
        appTitleLabel.setMaximumSize(new java.awt.Dimension(400, 14));
        appTitleLabel.setMinimumSize(new java.awt.Dimension(200, 14));
        appTitleLabel.setPreferredSize(new java.awt.Dimension(400, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        getContentPane().add(appTitleLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        getContentPane().add(jSeparator1, gridBagConstraints);

        closeButton.setText(bundle.getString("AboutView.closeButton.text")); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        getContentPane().add(closeButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AboutView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                AboutView dialog = new AboutView(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel appTitleLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
