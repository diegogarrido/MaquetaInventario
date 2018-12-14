package maquetainventario;

import db.DBHandler;
import db.orm.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MaquetaInventario {

    public static void main(String[] args) {
        Properties config = new MaquetaInventario().LoadProperties();
        DBHandler db = new DBHandler(config.getProperty("user"), config.getProperty("password"), config.getProperty("database"));
        db.Conectar();
        Producto p = new Producto("producto1", "descripcion");
        db.Insertar(p);
        db.AgregarProducto(db.ObtenerSucursal(1), db.ObtenerProducto(1), 5);
    }

    public Properties LoadProperties() {
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
}
