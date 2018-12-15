package vistas;

import db.DBHandler;
import db.orm.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import maquetainventario.MaquetaInventario;

public class Home extends javax.swing.JFrame {

    private Region[] regiones = null;
    private Comuna[] comunas = null;
    private Sucursal[] sucursales = null;
    private Producto[] productos = null;
    DBHandler query;

    public Home() {
        initComponents();
        Properties config = new MaquetaInventario().LoadProperties();
        query = new DBHandler(config.getProperty("user"), config.getProperty("password"), config.getProperty("database"));
        query.Conectar();
        regiones = query.ObtenerRegiones();
        combo_region.addItem("Seleccionar...");
        for (int i = 0; i < regiones.length; i++) {
            combo_region.addItem(regiones[i].nombre);
        }
        LimpiarProductosRegistrados();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        productosEnSucursal = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        productosRegistrados = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botonQuitar = new javax.swing.JButton();
        botonRegistrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        botonAgregarProducto = new javax.swing.JButton();
        botonVender = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));

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
        jLabel1.setText("Productos registrados");

        combo_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_sucursalActionPerformed(evt);
            }
        });

        productosEnSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripcón", "Cantidad", "Precio Unitario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(productosEnSucursal);

        productosRegistrados.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 5", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 5", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 5", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 5", "Item 5Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 5", "Item 5Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 5", "Item 5Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 5", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(productosRegistrados);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Sucursal");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Productos en sucursal");

        botonQuitar.setText("→");
        botonQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonQuitarActionPerformed(evt);
            }
        });

        botonRegistrar.setText("←");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_region, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combo_region, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo_sucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lbl_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(147, 147, 147))
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
                    .addComponent(combo_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(botonQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Gestión de inventario TecnoBox");

        botonAgregarProducto.setText("Nuevo producto");
        botonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProductoActionPerformed(evt);
            }
        });

        botonVender.setText("Vender Seleccionado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(botonVender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAgregarProducto)
                .addGap(164, 164, 164))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(325, 325, 325))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregarProducto)
                    .addComponent(botonVender))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_comunaActionPerformed
        combo_sucursal.removeAllItems();
        LimpiarProductosRegistrados();
        if (combo_comuna.getSelectedIndex() > 0) {
            if (combo_comuna.getSelectedIndex() == comunas.length) {
            } else {
                combo_sucursal.addItem("Seleccionar...");
                sucursales = query.ObtenerSucursalesDeComuna(comunas[combo_comuna.getSelectedIndex() - 1]);
                for (int i = 0; i < sucursales.length; i++) {
                    combo_sucursal.addItem(sucursales[i].nombre);
                }
                combo_sucursal.addItem("Agregar sucursal");
            }
        }
    }//GEN-LAST:event_combo_comunaActionPerformed

    private void combo_regionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_regionActionPerformed
        combo_comuna.removeAllItems();
        combo_sucursal.removeAllItems();
        LimpiarProductosRegistrados();
        if (combo_region.getSelectedIndex() > 0) {
            combo_comuna.addItem("Seleccionar...");
            comunas = query.ObtenerComunasDeRegion(regiones[combo_region.getSelectedIndex() - 1]);
            for (int i = 0; i < comunas.length; i++) {
                combo_comuna.addItem(comunas[i].nombre);
            }
            combo_comuna.addItem("Agregar comuna");
        }
    }//GEN-LAST:event_combo_regionActionPerformed

    private void botonQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonQuitarActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void botonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProductoActionPerformed
        AgregarProducto();
    }//GEN-LAST:event_botonAgregarProductoActionPerformed

    private void combo_sucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sucursalActionPerformed
        LimpiarProductosRegistrados();
        if (combo_comuna.getSelectedIndex() > 0 && combo_sucursal.getSelectedIndex() > 0) {
            Producto[] prods = query.ObtenerProductosEnSucursal(sucursales[combo_sucursal.getSelectedIndex() - 1]);
            Object[][] tabla = new Object[prods.length][4];
            for (int i = 0; i < prods.length; i++) {
                tabla[i][0] = prods[i].nombre;
                tabla[i][1] = prods[i].descripcion;
                tabla[i][2] = 1;//prods[i].
                tabla[i][3] = 100;//prods[i].nombre;
            }
            productosEnSucursal.setModel(new javax.swing.table.DefaultTableModel(
                    tabla,
                    new String[]{
                        "Nombre", "Descripcón", "Cantidad", "Precio Unitario"
                    }
            ) {
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        }
    }//GEN-LAST:event_combo_sucursalActionPerformed

    private void AgregarComuna() {
        Comuna com = new Comuna();
        com.setId_region(regiones[combo_region.getSelectedIndex() - 1].id);
        String nom = JOptionPane.showInputDialog(null, "Ingrese nombre de comuna", "Agregar Comuna", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            com.setNombre(nom);
            query.Insertar(com);
        }
    }

    private void AgregarSucursal() {
        Sucursal suc = new Sucursal();
        suc.setId_comuna(combo_comuna.getSelectedIndex() - 1);
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

    private void LimpiarProductosRegistrados() {
        productosEnSucursal.setModel(new javax.swing.table.DefaultTableModel(
                new Object[0][0],
                new String[]{
                    "Nombre", "Descripcón", "Cantidad", "Precio Unitario"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
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
    private javax.swing.JButton botonAgregarProducto;
    private javax.swing.JButton botonQuitar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonVender;
    private javax.swing.JComboBox<String> combo_comuna;
    private javax.swing.JComboBox<String> combo_region;
    private javax.swing.JComboBox<String> combo_sucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_comuna;
    private javax.swing.JLabel lbl_region;
    private javax.swing.JTable productosEnSucursal;
    private javax.swing.JList productosRegistrados;
    // End of variables declaration//GEN-END:variables
}
