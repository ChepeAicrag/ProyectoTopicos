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
    
    public synchronized void put(Tanda tanda ){
        barriles.add(tanda);
        notifyAll();
    }
    
    public synchronized Tanda remove() throws InterruptedException{
        while (isEmpty()) {
            System.out.println("Espernado la tanda de Barriles " + Thread.currentThread().getName());
            wait();
        }
        notifyAll();
        return barriles.remove();
    }
    
    public boolean isEmpty(){
        return barriles.isEmpty();
    }
}
