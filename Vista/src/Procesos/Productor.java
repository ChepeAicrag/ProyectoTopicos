/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

/**
 *
 * @author García García José Ángel
 */
public interface Productor {
    
    public void producir() throws InterruptedException;
    public void producir(Tanda tanda) throws InterruptedException;

}
