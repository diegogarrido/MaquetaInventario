package db;

import db.orm.*;
import java.sql.*;

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

    /**
     * Insertar un objeto a la base de datos. Se puede entregar cualquier objeto
     * de las clases: Producto, Comuna, Region y Sucursal
     *
     * @param o Objeto de cualquiera de las clases especificadas
     * @return Verdadero si se inserta, falso si hay error.
     */
    public boolean Insertar(Object o) {
        if (o.getClass() == Producto.class) {
            return InsertarProducto((Producto) o);
        } else if (o.getClass() == Comuna.class) {
            return InsertarComuna((Comuna) o);
        } else if (o.getClass() == Region.class) {
            return InsertarRegion((Region) o);
        } else if (o.getClass() == Sucursal.class) {
            return InsertarSucursal((Sucursal) o);
        } else {
            System.out.println("Objeto no es Producto ni Comuna ni Region ni Sucursal");
            return false;
        }
    }

    public void AgregarProducto(Sucursal suc, Producto prod, int cantidad) {
        if (this.conn != null) {
            try {
                Producto[] productos = ObtenerProductosEnSucursal(suc);
                boolean existe = false;
                for (int i = 0; i < productos.length; i++) {
                    if (productos[i].id == prod.id) {
                        existe = true;
                        break;
                    }
                }
                stat = conn.createStatement();
                if (existe) {
                    stat.execute("UPDATE sucursaltieneproducto SET cantidad=" + (ObtenerCantidadDeProductoEnSucursal(suc, prod) + cantidad) + " WHERE id_sucursal=" + suc.id + " AND id_producto=" + prod.id);
                } else {
                    stat.execute("INSERT INTO sucursaltieneproducto (id_sucursal,id_producto,cantidad,precio_unitario) VALUES (" + suc.id + "," + prod.id + "," + cantidad + "," + 0 + ")");
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    public void QuitarProducto(Sucursal suc, Producto prod) {
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("DELETE FROM sucursaltieneproducto WHERE (id_sucursal=" + suc.id + " AND id_producto=" + prod.id + ")");
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    public Producto[] ObtenerProductosNoEnSucursal(Sucursal suc) {
        Producto[] productos = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM producto where id NOT IN (SELECT id_producto FROM sucursaltieneproducto WHERE id_sucursal=" + suc.id + ")");
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    productos = new Producto[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < productos.length; i++) {
                        productos[i] = ObtenerProducto(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return productos;
    }

    public int ObtenerCantidadDeProductoEnSucursal(Sucursal suc, Producto prod) {
        int cant = -1;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT cantidad FROM sucursaltieneproducto WHERE id_sucursal=" + suc.id + " AND id_producto=" + prod.id);
                try (ResultSet res = stat.getResultSet()) {
                    res.first();
                    cant = (res.getInt("cantidad"));
                    stat.close();
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return cant;
    }

    public SucursalTieneProducto[] ObtenerSucursalTieneProductos(Sucursal suc) {
        SucursalTieneProducto[] productos = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM sucursaltieneproducto WHERE id_sucursal=" + suc.id);
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    productos = new SucursalTieneProducto[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < productos.length; i++) {
                        productos[i] = ObtenerSucursalTieneProducto(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return productos;
    }

    public SucursalTieneProducto ObtenerSucursalTieneProducto(int id) {
        SucursalTieneProducto suc = null;
        if (this.conn != null) {
            try {
                Statement st = conn.createStatement();
                if (st.execute("SELECT * FROM sucursaltieneproducto WHERE id=" + id)) {
                    ResultSet res = st.getResultSet();
                    res.first();
                    suc = new SucursalTieneProducto();
                    suc.setId(res.getInt("id"));
                    suc.setId_producto(res.getInt("id_producto"));
                    suc.setId_sucursal(res.getInt("id_sucursal"));
                    suc.setPrecio_unitario(res.getInt("precio_unitario"));
                    suc.setCantidad(res.getInt("cantidad"));
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return suc;
    }

    public Producto[] ObtenerProductosEnSucursal(Sucursal suc) {
        Producto[] productos = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id_producto FROM sucursaltieneproducto WHERE id_sucursal=" + suc.id);
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    productos = new Producto[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < productos.length; i++) {
                        productos[i] = ObtenerProducto(res.getInt("id_producto"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return productos;
    }

    public Region[] ObtenerRegiones() {
        Region[] regiones = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM region");
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    regiones = new Region[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < regiones.length; i++) {
                        regiones[i] = ObtenerRegion(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return regiones;
    }

    public Region ObtenerRegion(int id) {
        Region region = null;
        if (this.conn != null) {
            try {
                Statement st = conn.createStatement();
                if (st.execute("SELECT * FROM region WHERE id=" + id)) {
                    ResultSet res = st.getResultSet();
                    res.first();
                    region = new Region();
                    region.setId(res.getInt("id"));
                    region.setNombre(res.getString("nombre"));
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return region;
    }

    public Sucursal[] ObtenerSucursales() {
        Sucursal[] sucursales = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM sucursal");
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    sucursales = new Sucursal[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < sucursales.length; i++) {
                        sucursales[i] = ObtenerSucursal(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return sucursales;
    }

    public Sucursal[] ObtenerSucursalesDeComuna(Comuna com) {
        Sucursal[] sucursales = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM sucursal WHERE id_comuna=" + com.id);
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    sucursales = new Sucursal[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < sucursales.length; i++) {
                        sucursales[i] = ObtenerSucursal(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return sucursales;
    }

    public Sucursal ObtenerSucursal(int id) {
        Sucursal sucursal = null;
        if (this.conn != null) {
            try {
                Statement st = conn.createStatement();
                if (st.execute("SELECT * FROM sucursal WHERE id=" + id)) {
                    ResultSet res = st.getResultSet();
                    res.first();
                    sucursal = new Sucursal();
                    sucursal.setId(res.getInt("id"));
                    sucursal.setNombre(res.getString("nombre"));
                    sucursal.setDireccion(res.getString("direccion"));
                    sucursal.setId_comuna(res.getInt("id_comuna"));
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return sucursal;
    }

    public Comuna[] ObtenerComunas() {
        Comuna[] comunas = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM comuna");
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    comunas = new Comuna[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < comunas.length; i++) {
                        comunas[i] = ObtenerComuna(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return comunas;
    }

    public Comuna[] ObtenerComunasDeRegion(Region reg) {
        Comuna[] comunas = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM comuna WHERE id_region=" + reg.id);
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    comunas = new Comuna[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < comunas.length; i++) {
                        comunas[i] = ObtenerComuna(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return comunas;
    }

    public Comuna ObtenerComuna(int id) {
        Comuna comuna = null;
        if (this.conn != null) {
            try {
                Statement st = conn.createStatement();
                if (st.execute("SELECT * FROM comuna WHERE id=" + id)) {
                    ResultSet res = st.getResultSet();
                    res.first();
                    comuna = new Comuna();
                    comuna.setId(res.getInt("id"));
                    comuna.setNombre(res.getString("nombre"));
                    comuna.setId_region(res.getInt("id_region"));
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return comuna;
    }

    public Producto[] ObtenerProductos() {
        Producto[] productos = null;
        if (this.conn != null) {
            try {
                stat = conn.createStatement();
                stat.execute("SELECT id FROM producto");
                try (ResultSet res = stat.getResultSet()) {
                    res.last();
                    productos = new Producto[res.getRow()];
                    res.beforeFirst();
                    res.first();
                    for (int i = 0; i < productos.length; i++) {
                        productos[i] = ObtenerProducto(res.getInt("id"));
                        res.next();
                    }
                }
                stat.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return productos;
    }

    public Producto ObtenerProducto(int id) {
        Producto producto = null;
        if (this.conn != null) {
            try {
                Statement st = conn.createStatement();
                if (st.execute("SELECT * FROM producto WHERE id=" + id)) {
                    ResultSet res = st.getResultSet();
                    res.first();
                    producto = new Producto();
                    producto.setId(res.getInt("id"));
                    producto.setNombre(res.getString("nombre"));
                    producto.setDescripcion(res.getString("descripcion"));
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return producto;
    }

    private boolean InsertarProducto(Producto prod) {
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

    private boolean InsertarRegion(Region reg) {
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

    private boolean InsertarComuna(Comuna com) {
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

    private boolean InsertarSucursal(Sucursal suc) {
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
            /*InsertarRegion(new Region("Tarapaca"));
             InsertarRegion(new Region("Antofagasta"));
             InsertarRegion(new Region("Atacama"));
             InsertarRegion(new Region("Coquimbo"));
             InsertarRegion(new Region("Valparaiso"));
             InsertarRegion(new Region("Libertador General Bernardo O’Higgins"));
             InsertarRegion(new Region("Maule"));*/
            InsertarRegion(new Region("Bio Bio"));
            InsertarRegion(new Region("Araucania"));
            /*InsertarRegion(new Region("Los lagos"));
             InsertarRegion(new Region("Aysen del General Carlos Ibanez del Campo"));
             InsertarRegion(new Region("Magallanes y Antartica Chilena"));
             InsertarRegion(new Region("Metropolitana de Santiago"));
             InsertarRegion(new Region("Los Rios"));
             InsertarRegion(new Region("Arica y Parinacota"));
             InsertarRegion(new Region("Nuble"));*/
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
                    + "  cantidad INT NOT NULL,\n"
                    + "  precio_unitario INT NOT NULL,\n"
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
