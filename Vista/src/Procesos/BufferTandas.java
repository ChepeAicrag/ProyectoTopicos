/*
 * Controla almacena la tanda para trasladarla al corte
 */

package Procesos;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author García García José Ángel
 */
public class BufferTandas {
    
    private Queue<Tanda> bufferTandas;
   
    public BufferTandas(){
        bufferTandas = new LinkedList();
    }
    
    public synchronized void put(Tanda tanda){
        bufferTandas.add(tanda);
    }
    
    public synchronized Tanda remove(){
        while (isEmpty()) {    
            System.out.println("Espera tanda inicial ::: " + Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error esperando tanda inicial ::: " + ex.getCause());
            }
        }
        notifyAll();
       return bufferTandas.remove();
    }
    
    public boolean isEmpty(){
        return bufferTandas.isEmpty();
    }
}
