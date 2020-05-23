/**
 * Produce piñas fermentadas y Consume piñas trituradas
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
    private JProgressBar barra;
    
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
                    isAvailable = false;
                    consumir();
                    System.out.println("Fermentador terminado");
                    barra.setString("Libre...");
                    barra.setValue(0);
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
        int total = tanda.getCantidadPinias(),
            cont = 0;
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('F'); // Triturada o molida
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
        isAvailable = true;
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasMolidas.remove(); // Quita de las molidas
        fermentar(tanda); // Las consume para hornear
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
