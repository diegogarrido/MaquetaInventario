package db.orm;

public class SucursalTieneProducto {

    public int id;
    public int id_sucursal;
    public int id_producto;
    public int cantidad;

    public SucursalTieneProducto(int id, int id_sucursal, int id_producto, int cantidad) {
        this.id = id;
        this.id_sucursal = id_sucursal;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public SucursalTieneProducto(int id_sucursal, int id_producto, int cantidad) {
        this.id_sucursal = id_sucursal;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
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
