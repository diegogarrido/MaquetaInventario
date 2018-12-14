package db;

import db.orm.Comuna;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import db.orm.Producto;
import db.orm.Region;
import db.orm.Sucursal;

public class DBHandler {

    private Connection conn;
    private Statement stat;

    public String user;
    public String password;
    public String database;

    public DBHandler(String user, String password, String database) {
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public boolean Conectar() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?user=" + user + "&password=" + password);
            return true;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1049) {
                System.out.println("Database no encontrada");
                System.out.println("Creando database");
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=" + user + "&password=" + password + "&useSSL=false");
                    stat = conn.createStatement();
                    stat.execute("CREATE DATABASE " + database + ";");
                    System.out.println("Database " + database + " creada!");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?user=" + user + "&password=" + password + "&useSSL=false");
                    stat.close();
                    CrearTablas();
                    return Conectar();
                } catch (SQLException ex1) {
                    System.out.println("SQLException: " + ex1.getMessage());
                    System.out.println("SQLState: " + ex1.getSQLState());
                    System.out.println("VendorError: " + ex1.getErrorCode());
                    return false;
                }
            } else if (ex.getErrorCode() == 1146) {
                System.out.println("No existen las tablas, creando...");
                CrearTablas();
                return Conectar();
            } else {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return false;
            }
        }
    }

    public boolean InsertarProducto(Producto prod) {
        try {
            stat = conn.createStatement();
            return stat.execute("INSERT INTO producto (nombre,descripcion) values ('" + prod.nombre + "','" + prod.descripcion + "');");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1146) {
                System.out.println("No existe la tabla Producto, creando...");
                CrearTablaProducto();
                return InsertarProducto(prod);
            } else {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return false;
            }
        }
    }

    public boolean InsertarRegion(Region reg) {
        try {
            stat = conn.createStatement();
            return stat.execute("INSERT INTO region (nombre) values ('" + reg.nombre + "');");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1146) {
                System.out.println("No existe la tabla Region, creando...");
                CrearTablaRegion();
                return InsertarRegion(reg);
            } else {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return false;
            }
        }
    }

    public boolean InsertarComuna(Comuna com) {
        try {
            stat = conn.createStatement();
            return stat.execute("INSERT INTO comuna (nombre,id_region) values ('" + com.nombre + "'," + com.id_region + ");");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1146) {
                System.out.println("No existe la tabla Comuna, creando...");
                CrearTablaComuna();
                return InsertarComuna(com);
            } else {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return false;
            }
        }
    }

    public boolean InsertarSucursal(Sucursal suc) {
        try {
            stat = conn.createStatement();
            return stat.execute("INSERT INTO sucursal (nombre,direccion,id_comuna) values ('" + suc.nombre + "','" + suc.direccion + "'," + suc.id_comuna + ");");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1146) {
                System.out.println("No existe la tabla Sucursal, creando...");
                CrearTablaSucursal();
                return InsertarSucursal(suc);
            } else {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return false;
            }
        }
    }

    public boolean CrearTablas() {
        boolean prod = CrearTablaProducto();
        boolean reg = CrearTablaRegion();
        boolean com = CrearTablaComuna();
        boolean suc = CrearTablaSucursal();
        boolean sucTprod = CreatTablaSucursalTieneProducto();
        return (prod && reg && com && suc && sucTprod);
    }

    private boolean CrearTablaProducto() {
        try {
            stat = conn.createStatement();
            boolean result = stat.execute("CREATE TABLE IF NOT EXISTS Producto\n"
                    + "(\n"
                    + "  id INT NOT NULL AUTO_INCREMENT,\n"
                    + "  nombre VARCHAR(30) NOT NULL,\n"
                    + "  descripcion VARCHAR(50),\n"
                    + "  PRIMARY KEY (id),\n"
                    + "  UNIQUE (nombre)\n"
                    + ");\n"
                    + "");
            stat.close();
            System.out.println("Tabla Producto creada");
            return result;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }

    private boolean CrearTablaRegion() {
        try {
            stat = conn.createStatement();
            boolean result = stat.execute("CREATE TABLE IF NOT EXISTS Region\n"
                    + "(\n"
                    + "  id INT NOT NULL AUTO_INCREMENT,\n"
                    + "  nombre VARCHAR(50) NOT NULL,\n"
                    + "  PRIMARY KEY (id)\n"
                    + ");");
            stat.close();
            InsertarRegion(new Region("Tarapaca"));
            InsertarRegion(new Region("Antofagasta"));
            InsertarRegion(new Region("Atacama"));
            InsertarRegion(new Region("Coquimbo"));
            InsertarRegion(new Region("Valparaiso"));
            InsertarRegion(new Region("Libertador General Bernardo O’Higgins"));
            InsertarRegion(new Region("Maule"));
            InsertarRegion(new Region("Bio Bio"));
            InsertarRegion(new Region("Araucania"));
            InsertarRegion(new Region("Los lagos"));
            InsertarRegion(new Region("Aysen del General Carlos Ibanez del Campo"));
            InsertarRegion(new Region("Magallanes y Antartica Chilena"));
            InsertarRegion(new Region("Metropolitana de Santiago"));
            InsertarRegion(new Region("Los Rios"));
            InsertarRegion(new Region("Arica y Parinacota"));
            InsertarRegion(new Region("Nuble"));
            System.out.println("Tabla Region creada");
            return result;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }

    private boolean CrearTablaComuna() {
        try {
            stat = conn.createStatement();
            boolean result = stat.execute("CREATE TABLE IF NOT EXISTS Comuna\n"
                    + "(\n"
                    + "  id INT NOT NULL AUTO_INCREMENT,\n"
                    + "  nombre VARCHAR(20) NOT NULL,\n"
                    + "  id_region INT NOT NULL,\n"
                    + "  PRIMARY KEY (id),\n"
                    + "  FOREIGN KEY (id_region) REFERENCES Region(id)\n"
                    + ");");
            stat.close();
            System.out.println("Tabla Comuna creada");
            InsertarComuna(new Comuna("Temuco", 9));
            InsertarComuna(new Comuna("Villarrica", 9));
            InsertarComuna(new Comuna("Lautaro", 9));
            return result;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }

    private boolean CrearTablaSucursal() {
        try {
            stat = conn.createStatement();
            boolean result = stat.execute("CREATE TABLE IF NOT EXISTS Sucursal\n"
                    + "(\n"
                    + "  id INT NOT NULL AUTO_INCREMENT,\n"
                    + "  nombre VARCHAR(20) NOT NULL,\n"
                    + "  direccion VARCHAR(50) NOT NULL,\n"
                    + "  id_comuna INT NOT NULL,\n"
                    + "  PRIMARY KEY (id),\n"
                    + "  FOREIGN KEY (id_comuna) REFERENCES Comuna(id)\n"
                    + ");");
            stat.close();
            System.out.println("Tabla Sucursal creada");
            InsertarSucursal(new Sucursal("Sucursal Mackenna", "Gral Mackenna 327", 1));
            return result;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }

    private boolean CreatTablaSucursalTieneProducto() {
        try {
            stat = conn.createStatement();
            boolean result = stat.execute("CREATE TABLE IF NOT EXISTS SucursalTieneProducto\n"
                    + "(\n"
                    + "  id INT NOT NULL AUTO_INCREMENT,\n"
                    + "  id_sucursal INT NOT NULL,\n"
                    + "  id_producto INT NOT NULL,\n"
                    + "  PRIMARY KEY (id),\n"
                    + "  FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id),\n"
                    + "  FOREIGN KEY (id_producto) REFERENCES Producto(id)\n"
                    + ");");
            stat.close();
            System.out.println("Tabla SucursalTieneProducto creada");
            return result;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }
}
