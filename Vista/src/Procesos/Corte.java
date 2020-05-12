/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Corte extends Thread{
    
    private int id, tiempo;
    private boolean estado;
    private int procesos;
    public Corte(int id){
        this.id = id;
        estado = true;
        procesos = 0; // Ningun proceso terminado
    }
    
    public void setTiempo(int tiempo){
        this.tiempo = tiempo;
    }

    public void run(){
        int cont = 0;
        while (cont <= tiempo) {            
            try {
                int r = (int) (Math.random() * 10);
                cont += r * 100; 
                //barra.setString("Cortando");
                sleep(cont);
                actualizarBarra(r);
            } catch (Exception e) {
                System.out.println("Error sleep de corte");
            }
            if (procesos != 6) {
                run();
            }
            procesos++;
            
        }
        barra.setString("Terminado");
        try {
            wait(); // Espera a que sea usado
        } catch (Exception e) {
        }
        estado = false;
        //notifyAll(); // Le indica a los demás que ha termiando
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
    
    public void setEstado(boolean estado){
        this.estado = estado;
    }
    
    public int getIdCorte(){
        return id;
    }
    
    public boolean getEstado(){
        return estado;
    }
}
