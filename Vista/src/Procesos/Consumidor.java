/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

import java.util.Date;

/**
 * 
 * @author García García José Ángel
 */
public class Consumidor extends Thread{
    
    private BufferTandas m;
    private int time, procesos;
    private DespachadorPalenque p;
    
    public Consumidor(int time,DespachadorPalenque p){
        System.out.println("Inicio en : " + new Date());
        this.time = time;
        //this.m = m;
        this.p = p;
    }
    
    public void run(){
        System.out.println("*********Inicia el Proceso de Producción*******");
        int cont = 0;
        while (time <= time) {
           cont += 10;
           p.elegir().actualizarBarra(cont);
        }
    }
    /*
    @Override
    public void run(){
        int cont = 0;
        while (cont <= time) {  
            int r = (int)(Math.random() * 10);
            cont = r * 100;
            try {
                m.ocupar();
                sleep(cont);
                System.out.println("Aumentando : " + r);
            } catch (InterruptedException e) {
                System.out.println("Error en consumidor " + e.getMessage());
            }
        }
        if(procesos == 6){
             System.out.println("He termiando en " + new Date());
             destroy();
             return;
        }else{
            run();
        }
        procesos++;
    }
    */
}
