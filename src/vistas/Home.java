package vistas;

import db.DBHandler;
import db.orm.*;
import javax.swing.JOptionPane;

public class Home extends javax.swing.JFrame {

    private Region[] regiones = null ;
    private Comuna[] comunas = null;
    private Sucursal[] sucursales = null;
    private Producto[] productos = null;
    DBHandler query = new DBHandler("root", "", "sist_inventario");

    public Home() {

        initComponents();
        query.Conectar();
        regiones = query.ObtenerRegiones();
        for (int i = 0; i < regiones.length; i++) {
            combo_region.addItem(regiones[i].nombre);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_region = new javax.swing.JLabel();
        combo_region = new javax.swing.JComboBox<String>();
        lbl_comuna = new javax.swing.JLabel();
        combo_comuna = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        combo_sucursal = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        lbl_region.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_region.setText("Región");

        combo_region.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_regionActionPerformed(evt);
            }
        });

        lbl_comuna.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_comuna.setText("Comuna");

        combo_comuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_comunaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Sucursal");

        combo_sucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_region, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo_region, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_sucursal, 0, 110, Short.MAX_VALUE))
                .addGap(103, 103, 103)
                .addComponent(lbl_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(combo_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combo_region, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_comuna, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(combo_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_region, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(397, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_comunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_comunaActionPerformed

    private void combo_regionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_regionActionPerformed
        // TODO add your handling code here:        
        comunas = query.ObtenerComunasDeRegion(regiones[combo_region.getSelectedIndex()]);
        for (int i = 0; i < comunas.length; i++) {
            combo_comuna.addItem(comunas[i].nombre);
        }
        combo_comuna.addItem("Agregar comuna");
    }//GEN-LAST:event_combo_regionActionPerformed

    private void AgregarComuna() {
        Comuna com = new Comuna();
        com.setId_region(regiones[combo_region.getSelectedIndex()].id);
        String nom = JOptionPane.showInputDialog(null, "Ingrese nombre de comuna", "Agregar Comuna", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            com.setNombre(nom);
            query.Insertar(com);
        }
    }

    private void AgregarSucursal() {
        Sucursal suc = new Sucursal();
        suc.setId_comuna(combo_comuna.getSelectedIndex());
        String nom = JOptionPane.showInputDialog(null, "Ingrese nombre de sucursal", "Agregar Sucursal", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            String dir = JOptionPane.showInputDialog(null, "Ingrese direccion de sucursal", "Agregar Sucursal", JOptionPane.QUESTION_MESSAGE);
            if (dir != null && dir.length() > 0) {
                suc.setNombre(nom);
                suc.setDireccion(dir);
                query.Insertar(suc);
            }
        }
    }

    private void AgregarProducto() {
        Producto prod = new Producto();
        String nom = JOptionPane.showInputDialog(null, "Ingrese nombre de producto", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            String desc = JOptionPane.showInputDialog(null, "Ingrese descripción de producto (opcional)", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
            prod.setNombre(nom);
            prod.setDescripcion(desc);
            query.Insertar(prod);
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_comuna;
    private javax.swing.JComboBox<String> combo_region;
    private javax.swing.JComboBox<String> combo_sucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_comuna;
    private javax.swing.JLabel lbl_region;
    // End of variables declaration//GEN-END:variables
}
