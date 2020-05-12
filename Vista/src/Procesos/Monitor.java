/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

/**
 * 
 * @author García García José Ángel
 */
public class Monitor {
    
    private boolean estaListo, estado;
    
    public synchronized void desocupar(){
        while (!estaListo) {            
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Monitor error en desocupar" + e.getMessage());
            }
        }
        // Indica que el equipo está ocupado
        estado = estaListo = false;
        notifyAll(); // Le notifica a los hilos
    }
    
    public synchronized boolean ocupar(){
        while (estaListo) {            
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Monitor error en ocupar " + e.getMessage());
            }
        }
        //Establece que está usando el equipo
        estado = estaListo = true;
        notifyAll();
        return estado;
    }
    
    public boolean getEstado(){
        return estado;
    }
}
