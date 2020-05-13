/*
 * Buffer que almacena Tanda con la pinias Cortadas
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
    
    public void put(Tanda tanda ){
        piniasMolidas.add(tanda);
    }
    
    public Tanda remove(){
        return piniasMolidas.remove();
    }
    
    public boolean isEmpty(){
        return piniasMolidas.isEmpty();
    }
}
