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
    
    public void put(Tanda tanda){
        mezcalDestilado.add(tanda);
    }
    
    public Tanda remove(){
        return mezcalDestilado.remove();
    }
    
    public boolean isEmpty(){
        return mezcalDestilado.isEmpty();
    }
}
