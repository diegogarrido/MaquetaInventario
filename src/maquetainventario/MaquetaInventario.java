package maquetainventario;

import db.DBHandler;
import db.orm.*;

public class MaquetaInventario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBHandler db = new DBHandler("admin", "admin", "sist_inventario");
        db.Conectar();
        Producto p = new Producto("producto1", "descripcion");
        db.Insertar(p);
        db.AgregarProducto(db.ObtenerSucursal(1), db.ObtenerProducto(1), 5);
    }
    
}
