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
        db.Insertar(new Producto("producto1", "descripcion"));
        Region[] reg = db.ObtenerRegiones();
        for (int i = 0; i < reg.length; i++) {
            System.out.println(reg[i].nombre);
        }
    }

}
