package db.orm;

public class Sucursal {
    public int id;
    public String nombre;
    public String direccion;
    public int id_comuna;

    public Sucursal(int id, String nombre, String direccion, int id_comuna) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.id_comuna = id_comuna;
    }

    public Sucursal(String nombre, String direccion, int id_comuna) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.id_comuna = id_comuna;
    }
    
    public Sucursal() {
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
    }
       
}
