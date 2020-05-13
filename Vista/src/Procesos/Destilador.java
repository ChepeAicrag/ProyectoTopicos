/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private BufferMezcalDestilado bufferMezcalDestilado;
    
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
                
                if (!bufferPiniasFermentadas.isEmpty()) {
                    isAvailable = false;
                    consumir();
                }else{
                    sleep(1000);
                    System.out.println("En wait");
                    return;
                }
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
        
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            Pinia p = (Pinia) pinia;
            Mezcal mezcal = new Mezcal(p.getTipo()); // Asignamos el tipo
            mezcal.setEstatus('D');
            mezcales.add(mezcal);
            piniasEliminar.add(pinia);
            System.out.println(mezcal);
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
