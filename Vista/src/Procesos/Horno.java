/*
 * Produce piñas horneadas y Consume piñas cortadas
 */

package Procesos;

import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Horno extends Thread implements Productor, Consumidor{

    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferPiniasCortadas bufferPiniasCortadas; // Consume de este
    private BufferPiniasHorneadas bufferPiniasHorneadas; // Produce en este
    
    public Horno(int id, BufferPiniasCortadas bufferPiniasCortadas, BufferPiniasHorneadas bufferPiniasHorneadas){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasCortadas = bufferPiniasCortadas;
        this.bufferPiniasHorneadas = bufferPiniasHorneadas;
    }
    
    @Override
    public synchronized void run(){
        while (true) {            
            try {
                
                //if (!bufferPiniasCortadas.isEmpty()) {
                    isAvailable = false;
                    consumir();
                    System.out.println("Horno consumió");
                    barra.setString("Libre...");
                    barra.setValue(0);
                /*
                    }
                else{
                    
                    System.out.println("En wait horno ");
                    bufferPiniasHorneadas.wait();
                }
                    */
                } catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public synchronized void producir(Tanda tanda) throws InterruptedException {
        bufferPiniasHorneadas.put(tanda); // Las produce
    }
    
    /** Produce la pinia horneada*/
    private synchronized void hornear(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(),
            cont = 0;
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('H');
            cont++;
            System.out.println(pinia);
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
        isAvailable = true;
    }

    @Override
    public synchronized void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasCortadas.remove(); // Quita de las cortadas
        hornear(tanda); // Las consume para hornear
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

    @Override
    public void producir() throws InterruptedException {
    }
    
    
}
