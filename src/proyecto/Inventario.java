package proyecto;

import java.util.ArrayList;
import db.orm.Producto;

public class Inventario {
protected ArrayList<Producto> array;
protected ArrayList<int[]> arrayVendido;
protected int total=0;
    
    public Inventario() {
        this.array = new ArrayList<>();
        this.arrayVendido = new ArrayList<>();
    }

    /*public static void main(String args[]){
        array.add(new Producto("nombre","codigo",500,15));
        vender(0,5);
        System.out.println("total:"+total);
        System.out.println("0:"+arrayVendido.get(0)[0]+" , 1:"+arrayVendido.get(0)[1]);
        System.out.println("Nueva cantidad: "+array.get(0).getCantidad());
    }*/
    public ArrayList<int[]> getArrayVendido(){
        return this.arrayVendido;
    }
    
    public int getTotal(){
        return this.total;
    }
    
    public void eliminar(int i){
        this.array.remove(i);
    }
    
    public void editar(int i,String nombre,String codigo,int cantidad,int precio){
        if(nombre.length()>0){    
            this.array.get(i).setNombre(nombre);
        }
        if(codigo.length()>0){
            this.array.get(i).setCodigo(codigo);
        }
        if(cantidad>0){
            this.array.get(i).setCantidad(cantidad);
        }
        if(precio>=0){
            this.array.get(i).setPrecio(precio);
        }
    }

    public void vender(int i, int cant){
        this.array.get(i).setCantidad(array.get(i).getCantidad()-cant);
        this.total+=(array.get(i).getPrecio())*cant;
        this.arrayVendido.add(new int[]{i,cant});
    }

    public void crearProducto(String n, String c, int cant, int p){
        Producto prod = new Producto(n,c,cant,p);
        this.array.add(prod);
        //guardarCsv();
    }

    public boolean buscar(String bus){
        for(int i=0;i<array.size();i++){
           if(array.get(i).getNombre().contains(bus)){
                return true;            
            }
        }
        for(int i=0;i<array.size();i++){
            if(array.get(i).getCodigo().contains(bus)){
                return true; 
            }
        }
        return false;
    }

}
