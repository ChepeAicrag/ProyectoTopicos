/*
 * Controla entre el productor y consumidor
 */

package Procesos;

/**
 * 
 * @author García García José Ángel
 */
public class Monitor {
    
    private boolean disponible, estado;
   
    public Monitor(){
        disponible = true;
    }
    
    public synchronized void put(){
        
    }
    
    
    
    
    
    
    
    /*
    
    public synchronized void desocupar(){
        // Si está siendo usado entonces espera a que termine
        while (!disponible) {            
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Monitor error en desocupar" + e.getMessage());
            }
        }
        // Indica que el equipo está ocupado
        estado = disponible = false;
        notifyAll(); // Le notifica a los hilos
    }
    
    public synchronized boolean ocupar(){
        // si está en uso debe esperar
        while (disponible) {            
            try {
                wait();
                } catch (Exception e) {
                System.out.println("Monitor error en ocupar " + e.getMessage());
            }
        }
        //Establece que termino de usar el equipo
        estado = false; 
        disponible = true;
        notifyAll();
        return estado;
    }
    */
    public boolean getEstado(){
        return estado;
    }
}
