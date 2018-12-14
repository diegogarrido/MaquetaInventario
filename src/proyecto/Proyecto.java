package proyecto;
import java.util.ArrayList;

public class Proyecto {
    
     public static void main(String[] args) {
         VentanaPrincipal ventana = new VentanaPrincipal(new ArrayList<int[]>(),0);
         ventana.setVisible(true);
     }
}