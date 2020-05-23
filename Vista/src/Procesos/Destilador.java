/**
 * Produce mezcal y Consume piñas fermentadas
 */
package Procesos;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Destilador extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferPiniasFermentadas bufferPiniasFermentadas; // Consume de este
    private BufferMezcalDestilado bufferMezcalDestilado; // Produce a este
    private JProgressBar barra;
    
    public Destilador(int id,BufferPiniasFermentadas bufferPiniasFermentadas, BufferMezcalDestilado bufferMezcalDestilado){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasFermentadas = bufferPiniasFermentadas;
        this.bufferMezcalDestilado = bufferMezcalDestilado;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                    isAvailable = false;
                    consumir();
                    System.out.println("Destilador terminó");
                    barra.setString("Libre...");
                    barra.setValue(0);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
       bufferMezcalDestilado.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private void destilar(Tanda tanda) throws InterruptedException{
        ArrayList<Object> piniasEliminar = new ArrayList();
        ArrayList<Object> mezcales = new ArrayList();
        int total = tanda.getCantidadPinias(),
            cont = 0;
        for(int  i = 0; i < numDestilados(tanda);i++){
            for (Object pinia : tanda.getPinias()) {
                sleep(2000);
                System.out.println(pinia);
                if(i == 0){ // Para que solo 1 vez los agregue
                    Pinia p = (Pinia) pinia;
                    Mezcal mezcal = new Mezcal(p.getTipo()); // Asignamos el tipo
                    mezcal.setEstatus('D');
                    mezcales.add(mezcal);
                    piniasEliminar.add(pinia);
                    System.out.println(mezcal);
                }
                cont++;
                actualizarBarra(cont * 100 / total);
            }
            cont = 0;
            barra.setString("Destilado " + (i + 1));
            sleep(1000);
        }
        tanda.getPinias().removeAll(piniasEliminar); // Quita las piñas
        tanda.getPinias().addAll(mezcales); // Agrega los mezcales
        producir(tanda); // Ahora la tanda ya lleva objetos de mezcal
        isAvailable = true;
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasFermentadas.remove(); // Quita de las fermentadas
        destilar(tanda); // Las consume para hornear
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

    private int numDestilados(Tanda t){
        int numDestilados = 1;
        if (t.getPorcentajeAlcohol() == 48){
            numDestilados = 2;
        }else if (t.getPorcentajeAlcohol() == 37){
            numDestilados = 3;
        }
        return numDestilados;
    }   
}
