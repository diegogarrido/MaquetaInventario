package db.orm;

public class SucursalTieneProducto {

    public int id;
    public int id_sucursal;
    public int id_producto;
    public int cantidad;
    public int precio_unitario;

    public SucursalTieneProducto(int id, int id_sucursal, int id_producto, int cantidad, int precio_unitario) {
        this.id = id;
        this.id_sucursal = id_sucursal;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public SucursalTieneProducto(int id_sucursal, int id_producto, int cantidad,int precio_unitario) {
        this.id_sucursal = id_sucursal;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public SucursalTieneProducto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

}
