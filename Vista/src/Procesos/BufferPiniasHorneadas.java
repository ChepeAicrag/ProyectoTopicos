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
    
    public void put(Tanda tanda ){
        piniasHorneadas.add(tanda);
    }
    
    public Tanda remove(){
        return piniasHorneadas.remove();
    }
    
    public boolean isEmpty(){
        return piniasHorneadas.isEmpty();
    }
}
