package vistas;

import db.DBHandler;
import db.orm.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.EventObject;
import java.util.Properties;
import javax.swing.CellEditor;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

public class Home extends javax.swing.JFrame {

    private Region[] regiones = null;
    private Comuna[] comunas = null;
    private Sucursal[] sucursales = null;
    private Producto[] productosSucursal = null;
    private Producto[] productos = null;
    DBHandler query;

    public Home() {
        initComponents();
        Properties config = LoadProperties();
        query = new DBHandler(config.getProperty("user"), config.getProperty("password"), config.getProperty("database"));
        query.Conectar();
        ActualizarComboRegion();
        LimpiarProductosRegistrados();
    }

    private void ActualizarComboRegion() {
        combo_region.removeAllItems();
        regiones = query.ObtenerRegiones();
        combo_region.addItem("Seleccionar...");
        for (Region region : regiones) {
            combo_region.addItem(region.nombre);
        }
        combo_region.addItem("Agregar región");
    }

    private void ActualizarComboComuna() {
        combo_comuna.addItem("Seleccionar...");
        comunas = query.ObtenerComunasDeRegion(regiones[combo_region.getSelectedIndex() - 1]);
        for (Comuna comuna : comunas) {
            combo_comuna.addItem(comuna.nombre);
        }
        combo_comuna.addItem("Agregar comuna");
    }

    private void ActualizarComboSucursal() {
        combo_sucursal.addItem("Seleccionar...");
        sucursales = query.ObtenerSucursalesDeComuna(comunas[combo_comuna.getSelectedIndex() - 1]);
        for (Sucursal sucursal : sucursales) {
            combo_sucursal.addItem(sucursal.nombre);
        }
        combo_sucursal.addItem("Agregar sucursal");
    }

    private void ActualizarProductos() {
        LimpiarProductos();
        if (combo_sucursal.getSelectedIndex() > 0) {
            productos = query.ObtenerProductosNoEnSucursal(sucursales[combo_sucursal.getSelectedIndex() - 1]);
            String[] nombres = new String[productos.length];
            for (int i = 0; i < nombres.length; i++) {
                nombres[i] = productos[i].nombre;
            }
            productosRegistrados.setModel(new javax.swing.AbstractListModel() {
                String[] strings = nombres;

                @Override
                public int getSize() {
                    return strings.length;
                }

                @Override
                public Object getElementAt(int i) {
                    return strings[i];
                }
            });
        }
    }

    private void ActualizarProductosEnSucursal() {
        productosSucursal = query.ObtenerProductosEnSucursal(sucursales[combo_sucursal.getSelectedIndex() - 1]);
        Object[][] tabla = new Object[productosSucursal.length][4];
        for (int i = 0; i < productosSucursal.length; i++) {
            tabla[i][0] = productosSucursal[i].nombre;
            tabla[i][1] = productosSucursal[i].descripcion;
            tabla[i][2] = query.ObtenerCantidad(sucursales[combo_sucursal.getSelectedIndex() - 1], productosSucursal[i]);
            tabla[i][3] = query.ObtenerPrecio(sucursales[combo_sucursal.getSelectedIndex() - 1], productosSucursal[i]);
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
                false, false, true, true
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
        botonAgregarProducto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
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
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productosEnSucursal.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                productosEnSucursalComponentRemoved(evt);
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

        botonAgregarProducto.setText("Nuevo producto");
        botonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProductoActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botonQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(lbl_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(72, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAgregarProducto)
                        .addGap(169, 169, 169))))
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(botonQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonAgregarProducto))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Gestión de inventario TecnoBox");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_comunaActionPerformed
        combo_sucursal.removeAllItems();
        LimpiarProductosRegistrados();
        if (combo_comuna.getSelectedIndex() > 0) {
            if (combo_comuna.getSelectedIndex() > comunas.length) {
                AgregarComuna();
                combo_regionActionPerformed(evt);
            } else {
                ActualizarComboSucursal();
            }
        }
    }//GEN-LAST:event_combo_comunaActionPerformed

    private void combo_regionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_regionActionPerformed
        combo_comuna.removeAllItems();
        combo_sucursal.removeAllItems();
        LimpiarProductosRegistrados();
        if (combo_region.getSelectedIndex() > 0) {
            if (combo_region.getSelectedIndex() > regiones.length) {
                AgregarRegion();
                ActualizarComboRegion();
            } else {
                ActualizarComboComuna();
            }
        }
    }//GEN-LAST:event_combo_regionActionPerformed

    private void botonQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonQuitarActionPerformed
        if (productosEnSucursal.getSelectedRow() > -1) {
            query.QuitarProducto(sucursales[combo_sucursal.getSelectedIndex() - 1], productosSucursal[productosEnSucursal.getSelectedRow()]);
            ActualizarProductos();
            ActualizarProductosEnSucursal();
        }
    }//GEN-LAST:event_botonQuitarActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        if (productosRegistrados.getSelectedIndex() > -1) {
            query.AgregarProducto(sucursales[combo_sucursal.getSelectedIndex() - 1], productos[productosRegistrados.getSelectedIndex()]);
            ActualizarProductos();
            ActualizarProductosEnSucursal();
        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void botonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProductoActionPerformed
        AgregarProducto();
    }//GEN-LAST:event_botonAgregarProductoActionPerformed

    private void combo_sucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sucursalActionPerformed
        LimpiarProductosRegistrados();
        if (combo_comuna.getSelectedIndex() > 0 && combo_sucursal.getSelectedIndex() > 0) {
            if (combo_sucursal.getSelectedIndex() > sucursales.length) {
                AgregarSucursal();
                combo_comunaActionPerformed(evt);
            } else {
                ActualizarProductosEnSucursal();
            }
        }
        ActualizarProductos();
    }//GEN-LAST:event_combo_sucursalActionPerformed

    private void productosEnSucursalComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_productosEnSucursalComponentRemoved
        if (evt.paramString().contains("Table.editor")) {
            int row = productosEnSucursal.getSelectedRow();
            int cantidad = Integer.parseInt("" + productosEnSucursal.getValueAt(row, 2));
            int precio = Integer.parseInt("" + productosEnSucursal.getValueAt(row, 3));
            query.ActualizarProductoEnSucursal(sucursales[combo_sucursal.getSelectedIndex() - 1], productosSucursal[row], cantidad, precio);
            ActualizarProductosEnSucursal();
        }
    }//GEN-LAST:event_productosEnSucursalComponentRemoved

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
        suc.setId_comuna(comunas[combo_comuna.getSelectedIndex() - 1].id);
        String nom = JOptionPane.showInputDialog(null, "Ingrese nombre de sucursal", "Agregar Sucursal", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            String dir = JOptionPane.showInputDialog(null, "Ingrese direccion de sucursal", "Agregar Sucursal", JOptionPane.QUESTION_MESSAGE);
            if (dir != null) {
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

    private void AgregarRegion() {
        Region reg = new Region();
        String nom = JOptionPane.showInputDialog(null, "Ingrese nombre de la Región", "Agregar Región", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            reg.setNombre(nom);
            query.Insertar(reg);
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
                false, false, true, true
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

    private void LimpiarProductos() {
        productosRegistrados.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });
    }

    private Properties LoadProperties() {
        try {
            Properties config = new Properties();
            InputStream inputStream = new FileInputStream("config.properties");
            config.load(inputStream);
            return config;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String args[]) {
        Home home = new Home();
        home.setLocationRelativeTo(null);
        home.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarProducto;
    private javax.swing.JButton botonQuitar;
    private javax.swing.JButton botonRegistrar;
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
