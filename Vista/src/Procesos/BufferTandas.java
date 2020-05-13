/*
 * Controla entre el productor y consumidor
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
    
    public void put(Tanda tanda){
        bufferTandas.add(tanda);
    }
    
    public Tanda remove(){
        return bufferTandas.remove();
    }
    
    public boolean isEmpty(){
        return bufferTandas.isEmpty();
    }
}
