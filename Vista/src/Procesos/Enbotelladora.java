/**
 * Produce barriles de mezcal y Consume mezcal
 */
package Procesos;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Enbotelladora extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferMezcalDestilado bufferMezcalDestilado; // Consume
    private BufferBarriles bufferBarriles; // Produce
    private JProgressBar barra;
    
    public Enbotelladora(int id, BufferMezcalDestilado bufferMezcalDestilado, BufferBarriles bufferBarriles){
        this.id = id;
        isAvailable = true;
        this.bufferMezcalDestilado = bufferMezcalDestilado;
        this.bufferBarriles = bufferBarriles;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                    isAvailable = false;
                    consumir();
                    barra.setString("Libre...");
                    barra.setValue(0);
                }catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        System.out.println("Tanda terminada lista >>>> \n " + tanda);
       bufferBarriles.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private void destilar(Tanda tanda) throws InterruptedException{
        ArrayList<Object> mezcalesEliminar = new ArrayList();
        ArrayList<Object> barriles = new ArrayList();
        int total = tanda.getCantidadPinias(),
            cont = 0;
        for (int i = 0; i < numAnejamiento(tanda); i++) {
            for (Object mezcal : tanda.getPinias()) {
                sleep(2000); // Tiempo por estimar
                System.out.println(mezcal);
                Mezcal m = (Mezcal) mezcal;
                if(i == 0){
                    Barril barril = new Barril(m.getTipo());
                    barril.setEstatus('E'); // Listo  E: Enbotellado
                    barriles.add(barril);
                    mezcalesEliminar.add(mezcal);
                    System.out.println(barril);
                }
                cont++;
                actualizarBarra(cont * 100 / total);
            }
            cont = 0;
            barra.setString("Fase " + (i + 1));
            sleep(1000);
        }
        
        tanda.getPinias().removeAll(mezcalesEliminar); // Quita los mezcales
        tanda.getPinias().addAll(barriles); // Agrega los mezcales
        producir(tanda); // Ahora la tanda ya lleva objetos de mezcal
        isAvailable = true;
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = bufferMezcalDestilado.remove(); // Quita de las fermentadas
        destilar(tanda); // Las consume para hornear
    }

    public void setBarra(JProgressBar barra){
        this.barra = barra;
    }
    public void actualizarBarra(int time){
        barra.setValue(time);
        barra.setString(time + "%");
    }

    @Override
    public void producir() throws InterruptedException {}
    
    private int numAnejamiento(Tanda t){
        int numAnejamientos = 1;
        /** Blanco 2 meses * Resposado 12 meses * Añejo 24 meses * Madurado 10 meses*/
        String tipo = t.getTipoMezcal(); 
        if (tipo.equals("Añejo")) 
            numAnejamientos = 12; // Por 2 meses
        else if(tipo.equals("Reposado"))
            numAnejamientos = 6;
        else if(tipo.equals("Madurado"))
            numAnejamientos = 5;
        return numAnejamientos;    
    }
}
