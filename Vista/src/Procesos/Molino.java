/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

import javax.swing.JProgressBar;
import static java.lang.Thread.sleep;

/**
 * 
 * @author García García José Ángel
 */
public class Molino extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferPiniasHorneadas bufferPiniasHorneadas; // Consume de este
    private BufferPiniasMolidas bufferPiniasMolidas; //  Produce
    
    public Molino(int id,BufferPiniasHorneadas bufferPiniasHorneadas, BufferPiniasMolidas bufferPiniasMolidas){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasHorneadas = bufferPiniasHorneadas;
        this.bufferPiniasMolidas = bufferPiniasMolidas;
    }
    
    @Override
    public synchronized void run(){
        while (true) {            
            try {
                //if (!bufferPiniasHorneadas.isEmpty()) {
                    isAvailable = false;
                    consumir();
                    System.out.println("Molino trabajó");
                /*
                }else{
                    sleep(1000);
                    System.out.println("En wait");
                    return;
                }
                */
                } catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
            
        }
    }
    
    @Override
    public synchronized void producir(Tanda tanda) throws InterruptedException {
       bufferPiniasMolidas.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private synchronized void moler(Tanda tanda) throws InterruptedException{
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('T');
            System.out.println(pinia);
        }
        producir(tanda);
        isAvailable = true;
    }

    @Override
    public synchronized void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasHorneadas.remove(); // Quita de las horneadas
        moler(tanda); // Las consume para hornear
    }

    private JProgressBar barra;
    public void setBarra(JProgressBar barra){
        this.barra = barra;
    }
    public void actualizarBarra(int time){
        //int val = ((barra.getValue() + time ) * 100) / tiempo;
        int val = barra.getValue() + time;
        System.out.println("Valor time : " + time + "\n Valor de barra " + val);
        barra.setValue(val);
    }

    @Override
    public void producir() throws InterruptedException {
    }
    
}
