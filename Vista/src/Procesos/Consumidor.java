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
public class Consumidor extends Thread{
    
    private Monitor m;
    private int time;
    public Consumidor(int time, Monitor m){
        this.time = time;
        this.m = m;
    }
    
    @Override
    public void run(){
        int cont = 0;
        while (cont <= time) {  
            int r = (int)(Math.random() * 10);
            cont = r * 100;
            try {
                sleep(cont);
                System.out.println("Aumentando : " + r);
                m.ocupar();
            } catch (InterruptedException e) {
                System.out.println("Error en consumidor " + e.getMessage());
            }
        }
    }
}
