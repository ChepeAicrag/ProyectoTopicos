/*
 * Produce piñas cortadas
 */

package Procesos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Corte extends Thread implements Productor{
    
    private int id;
    private boolean isAvailable;
    private BufferTandas bufferTandas;
    private BufferPiniasCortadas bufferPiniasCortadas;
    private JProgressBar barra;
    
    public Corte(int id, BufferTandas bufferTandas,BufferPiniasCortadas bufferPiniasCortadas){
        this.id = id;
        isAvailable = true;
        this.bufferTandas = bufferTandas;
        this.bufferPiniasCortadas = bufferPiniasCortadas;
    }
    
    
    @Override
    public synchronized void run(){
        while(true){
            try {
                        isAvailable = false;
                        producir();
                        System.out.println("Corte terminado");
                        barra.setString("Libre...");
                        barra.setValue(0);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }
    
    private synchronized void cortar(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(), // Este es el 100
            cont = 0;
        for (Object pinia : tanda.getPinias()) {
            sleep(2000);
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('C');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        bufferPiniasCortadas.put(tanda);
        barra.setString("Completado...");
        isAvailable = true;
    }
    
    @Override
    public synchronized void producir() throws InterruptedException {
      Tanda tanda = bufferTandas.remove();
      System.out.println(" Yo hilo: " + Thread.currentThread().getName()
              + "\nTome la tanda: " + tanda);
      cortar(tanda);
    }
    
    @Override
    public void producir(Tanda tanda) throws InterruptedException {}
   
    public void setBarra(JProgressBar barra){
        this.barra = barra;
    }
    
    public void actualizarBarra(int time){
        barra.setValue(time);
        barra.setString(time + "%");
    }    
}
