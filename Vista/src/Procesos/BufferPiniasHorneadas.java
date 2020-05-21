/*
 * Buffer que almacena Tanda con la pinias Horneadas
 */

package Procesos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author García García José Ángel
 */
public class BufferPiniasHorneadas {
    
    private Queue<Tanda> piniasHorneadas;
    
    public BufferPiniasHorneadas(){
        piniasHorneadas = new LinkedList();
    }
    
    public synchronized void put(Tanda tanda ){
        piniasHorneadas.add(tanda);
        notifyAll();
    }
    
    public synchronized Tanda remove() throws InterruptedException{
        while (isEmpty()) {        
            System.out.println("Esperando la tanda horneada: " + Thread.currentThread().getName());
            wait();
        }
        notifyAll();
        return piniasHorneadas.remove();
    }
    
    public boolean isEmpty(){
        return piniasHorneadas.isEmpty();
    }
}
