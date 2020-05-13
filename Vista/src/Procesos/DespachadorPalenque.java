/*
 * Controla al hilo Tanda para indicarle a que proceso avanzar
 * Solo existen 3 despachadores
 */

package Procesos;

import java.util.ArrayList;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class DespachadorPalenque {
    
    // Tiene 3 productores
    private ArrayList<ProductorCorte> productores;
    
    // Necesita las barras para sus productores
    public DespachadorPalenque(ArrayList<JProgressBar> barras){
        productores = new ArrayList<>();
        cargar(barras);
        iniciarProcesos();
    }
    
    /** Carga las barras para cada proceso*/
    private void cargar(ArrayList<JProgressBar> barras){
        for(JProgressBar b : barras)
            productores.add(new ProductorCorte(b));
    }
    // Retorna el estado de un proceso dado
    public boolean getEstadoProceso(int pos){
        return (productores != null) ? productores.get(pos).getEstado() : false; 
    }
    
    public void iniciarProcesos(){
        if (productores != null) {
            for (ProductorCorte p : productores) {
                p.start();
            }
        }   
    }
    
    public synchronized void accion(){
        
    }
    
    // Busca quien está libre y lo retorna
    public ProductorCorte elegir(){
        ProductorCorte pro = null;
        for (ProductorCorte p : productores) {
            if(!p.getEstado()){
                pro = p; // Lo asignamos
                pro.setEstado(true);
                System.out.println("Retorna a");
                return p;
            }
        }
        return pro;
    }
}
