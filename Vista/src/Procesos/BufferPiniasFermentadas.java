/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author García García José Ángel
 */
public class BufferPiniasFermentadas {
    
    private Queue<Tanda> piniasFermentadas; 
    
    public BufferPiniasFermentadas(){
        piniasFermentadas = new LinkedList();
    }
    
    public void put(Tanda tanda ){
        piniasFermentadas.add(tanda);
    }
    
    public Tanda remove(){
        return piniasFermentadas.remove();
    }
    
    public boolean isEmpty(){
        return piniasFermentadas.isEmpty();
    }
}
