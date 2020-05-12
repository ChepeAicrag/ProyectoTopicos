/*
 * Corresponde a una tanda de produccion
 */

package Procesos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author García García José Ángel
 */
public class TandaProduccion extends Thread{
    
    private Color color; // Color para identificarla
    private int id; // Id para registrarla en la BD
    private Date fechaInicio, fechaFinal; // Fecha en la que inicia y termina
    private ArrayList<Integer> tiempos; // Tiempo para cada proceso
    
    public TandaProduccion(int id,Color color,ArrayList<Integer> tiempos){
        this.id = id;
        this.fechaInicio = new Date();
        this.tiempos = tiempos;
    } 
    
    @Override
    public void run(){
        
    }
    
}
