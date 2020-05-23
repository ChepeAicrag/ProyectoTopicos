/**
 * Produce piñas trituradas y Consume piñas horneadas
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
    private JProgressBar barra;
    
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
                    isAvailable = false;
                    consumir();
                    System.out.println("Molino trabajó");
                    barra.setString("Libre...");
                    barra.setValue(0);
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
        int total = tanda.getCantidadPinias(),
            cont = 0;
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('T');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
        isAvailable = true;
    }

    @Override
    public synchronized void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasHorneadas.remove(); // Quita de las horneadas
        moler(tanda); // Las consume para hornear
    }
    
    @Override
    public void producir() throws InterruptedException {}

    public void setBarra(JProgressBar barra){
        this.barra = barra;
    }
    
    public void actualizarBarra(int time){
        barra.setValue(time);
        barra.setString(time + "%");
    }
}
