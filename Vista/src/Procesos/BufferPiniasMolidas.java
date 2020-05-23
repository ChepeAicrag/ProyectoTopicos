/*
 * Buffer que almacena Tanda con la pinias molidas
 */

package Procesos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author García García José Ángel
 */
public class BufferPiniasMolidas {
    
    private Queue<Tanda> piniasMolidas; 
    
    public BufferPiniasMolidas(){
        piniasMolidas = new LinkedList();
    }
    
    public synchronized void put(Tanda tanda ){
        piniasMolidas.add(tanda);
        notifyAll();
    }
    
    public synchronized Tanda remove() throws InterruptedException{
        while(isEmpty()){
            System.out.println("Esperando la tanda molida: " + Thread.currentThread().getName());
            wait();
            
        }
        notifyAll();
        return piniasMolidas.remove();
    }
    
    public boolean isEmpty(){
        return piniasMolidas.isEmpty();
    }
}
