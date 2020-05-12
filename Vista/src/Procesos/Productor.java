/*
 * El productor es cualquier proceso para producir
 */

package Procesos;

import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Productor extends Thread{
    
    private int idProductor, tiempo; // Solo hay 18
    private JProgressBar barra;
    private Monitor m;
    
    public Productor(JProgressBar barra, Monitor m){
        this.barra = barra;
        this.m = m;
        barra.setString("Disponible");
    }
    
    @Override
    public void run(){
        int cont = 0;
        while (!m.getEstado()){            
            try {
                m.desocupar();
                System.out.println("Estado: " + m.getEstado());
                int r = (int) (Math.random() * 10);
                cont += r * 100; 
                sleep(cont);
                actualizarBarra(r);
                barra.setString("Ocupado");
                } catch (Exception e) {
                System.out.println("Error sleep de corte " + e.getMessage());
            }
        }
        barra.setString("Disponible");
        barra.setValue(0);
    }
    
    public void actualizarBarra(int time){
        int val = barra.getValue() + time; //* 100 / tiempo;
        System.out.println("Valor time : " + time + "\n Valor de barra " + val);
        barra.setValue(val);
    }
    
}
