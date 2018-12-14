package db.orm;

public class Comuna {
    public int id;
    public String nombre;
    public int id_region;

    public Comuna(int id, String nombre, int id_region) {
        this.id = id;
        this.nombre = nombre;
        this.id_region = id_region;
    }

    public Comuna(String nombre, int id_region) {
        this.nombre = nombre;
        this.id_region = id_region;
    }

    public Comuna() {
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

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }
    
}
