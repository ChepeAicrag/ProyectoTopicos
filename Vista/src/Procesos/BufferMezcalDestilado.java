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
public class BufferMezcalDestilado {

    private Queue <Tanda> mezcalDestilado;
    
    public BufferMezcalDestilado(){
        mezcalDestilado = new LinkedList();
    }
    
    public synchronized void put(Tanda tanda){
        mezcalDestilado.add(tanda);
        notifyAll();
    }
    
    public synchronized Tanda remove() throws InterruptedException{
        while (isEmpty()) {
            System.out.println("Esperando la tanda destilada: " + Thread.currentThread().getName());
            wait();
        }
        notifyAll();
        return mezcalDestilado.remove();
    }
    
    public boolean isEmpty(){
        return mezcalDestilado.isEmpty();
    }
}
