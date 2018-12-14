package maquetainventario;

import db.DBHandler;
import db.orm.Producto;

public class MaquetaInventario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBHandler db = new DBHandler("admin", "admin","sist_inventario");
        db.Conectar();
        db.InsertarProducto(new Producto("producto1","descripcion"));
    }
    
}
