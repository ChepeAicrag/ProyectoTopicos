/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author García García José Ángel
 */
public class BufferPiniasCortadas {
    private Queue<Tanda> piniasCortadas; 
    
    public BufferPiniasCortadas(){
        piniasCortadas = new LinkedList();
    }
    
    public synchronized void put(Tanda tanda ){
        piniasCortadas.add(tanda);
        notifyAll();
    }
    
    public synchronized Tanda remove(){
        while (isEmpty()) {            
            System.out.println("Esperando la tanda cortada: " + Thread.currentThread().getName());
            try{ 
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error esperando la tanda cortada\n " + ex.getCause());
            }
        }
        notifyAll();
        return piniasCortadas.remove();
    }
    
    public boolean isEmpty(){
        return piniasCortadas.isEmpty();
    }
}
