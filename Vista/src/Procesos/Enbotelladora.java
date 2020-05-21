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
public class Enbotelladora extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferMezcalDestilado bufferMezcalDestilado;
    private BufferBarriles bufferBarriles;
    
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
                
                //if (!bufferMezcalDestilado.isEmpty()) {
                    isAvailable = false;
                    consumir();
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
       bufferBarriles.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private void destilar(Tanda tanda) throws InterruptedException{
        ArrayList<Object> mezcalesEliminar = new ArrayList();
        ArrayList<Object> barriles = new ArrayList();
        
        for (Object mezcal : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(mezcal);
            Mezcal m = (Mezcal) mezcal;
            Barril barril = new Barril(m.getTipo());
            barril.setEstatus('E'); // Listo  E: Enbotellado
            barriles.add(barril);
            mezcalesEliminar.add(mezcal);
            System.out.println(barril);
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
