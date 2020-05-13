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
public class BufferBarriles{
     private Queue<Tanda> barriles;
    
    public BufferBarriles(){
        barriles = new LinkedList();
    }
    
    public void put(Tanda tanda ){
        barriles.add(tanda);
    }
    
    public Tanda remove(){
        return barriles.remove();
    }
    
    public boolean isEmpty(){
        return barriles.isEmpty();
    }
}
