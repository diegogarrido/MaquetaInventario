package proyecto;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class VentanaResumen extends javax.swing.JFrame {

    ArrayList<int[]> arrayVendido;
    int total;
    
    public VentanaResumen(ArrayList <int[]> array,int tot) {
        initComponents();
        this.arrayVendido=array;
        this.total=tot;
        crearListas();
        txTotal.setText(""+tot);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        volver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txTotal = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        lista2 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        lista3 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Resumen diario");
        setIconImage(getIconImage());
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel1.setText("Resumen del día");

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        jLabel2.setText("Total vendido en el día:");

        txTotal.setEditable(false);
        txTotal.setBorder(null);
        txTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(txTotal);
        txTotal.getAccessibleContext().setAccessibleParent(txTotal);

        jLabel3.setText("Producto");

        jLabel4.setText("Cantidad");

        jLabel5.setText("Precio unitario");

        lista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "No" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lista.setAutoscrolls(false);
        lista.setEnabled(false);
        lista.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(lista);

        lista2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Hay" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lista2.setAutoscrolls(false);
        lista2.setEnabled(false);
        lista2.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(lista2);

        lista3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Ventas" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lista3.setEnabled(false);
        jScrollPane5.setViewportView(lista3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)
                        .addGap(40, 40, 40)
                        .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(81, 81, 81))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("proyecto/Logo.png"));
        return retValue;
    }
    
    private void crearListas(){
        DefaultListModel def = new DefaultListModel();
        VentanaPrincipal principal= new VentanaPrincipal(this.arrayVendido,this.total);
        if(!this.arrayVendido.isEmpty()){
            for(int i = 0; i < this.arrayVendido.size() ; i++){
                try{
                    def.addElement(principal.inv.array.get(this.arrayVendido.get(i)[0]).getNombre());
                }catch(java.lang.IndexOutOfBoundsException ex){def.addElement("Producto eliminado");}
            }
            lista.setModel(def);
            def=new DefaultListModel();
            for(int i = 0; i < this.arrayVendido.size() ; i++){
                def.addElement(this.arrayVendido.get(i)[1]);
            }
            lista2.setModel(def);
            def=new DefaultListModel();
            for(int i = 0; i < this.arrayVendido.size() ; i++){
                try{
                    def.addElement(principal.inv.array.get(this.arrayVendido.get(i)[0]).getPrecio());
                }catch(java.lang.IndexOutOfBoundsException ex){def.addElement("Producto eliminado");}
            }
            lista3.setModel(def);
        }/*else{
            def.addElement(principal.inv.array.get(this.arrayVendido.get(0)[0]).getNombre());
            lista.setModel(def);
            def=new DefaultListModel();
            def.addElement(this.arrayVendido.get(0)[1]);
            lista2.setModel(def);
            def=new DefaultListModel();
            def.addElement(principal.inv.array.get(this.arrayVendido.get(0)[0]).getPrecio());
            lista3.setModel(def);
        }*/
    }
    
    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        VentanaPrincipal principal= new VentanaPrincipal(this.arrayVendido,this.total);
        principal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> lista;
    private javax.swing.JList<String> lista2;
    private javax.swing.JList<String> lista3;
    private javax.swing.JTextPane txTotal;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
