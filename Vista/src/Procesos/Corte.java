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
    //private int procesos;
    private BufferTandas bufferTandas;
    private BufferPiniasCortadas bufferPiniasCortadas;
    
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
                    
                /*
                    }else{
                    //sleep(1000);
                    System.out.println("Aquí entra a wait --------" + Thread.currentThread().getName());
                    isAvailable = true;
                    wait();
                    //return;
                }
                    */
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            } catch (Throwable ex) {
                Logger.getLogger(Corte.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    
    
    private JProgressBar barra;
    public void setBarra(JProgressBar barra){
        this.barra = barra;
    }
    public void actualizarBarra(int time){
        //int val = ((barra.getValue() + time ) * 100) / tiempo;
        //int val = barra.getValue() + time;
        //System.out.println("Valor time : " + time + "\n Valor de barra " + val);
        barra.setValue(time);
        barra.setString(time + "%");
    }
    
    
    
    public int getIdCorte(){
        return id;
    }

    @Override
    public void producir(Tanda tanda) throws InterruptedException {
    }

    
    
    
}
