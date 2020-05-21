/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

import static java.lang.Thread.sleep;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Fermentado extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferPiniasMolidas bufferPiniasMolidas; //  Produce
    private BufferPiniasFermentadas bufferPiniasFermentadas; // Consume de este
    
    public Fermentado(int id, BufferPiniasMolidas bufferPiniasMolidas, BufferPiniasFermentadas bufferPiniasFermentadas){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasMolidas = bufferPiniasMolidas;
        this.bufferPiniasFermentadas = bufferPiniasFermentadas;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                
                //if (!bufferPiniasMolidas.isEmpty()) {
                    isAvailable = false;
                    consumir();
                    System.out.println("Fermentador terminado");
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
    public void producir(Tanda tanda) throws InterruptedException {
       bufferPiniasFermentadas.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private void fermentar(Tanda tanda) throws InterruptedException{
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('F'); // Triturada o molida
            System.out.println(pinia);
        }
        producir(tanda);
        isAvailable = true;
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasMolidas.remove(); // Quita de las molidas
        fermentar(tanda); // Las consume para hornear
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
