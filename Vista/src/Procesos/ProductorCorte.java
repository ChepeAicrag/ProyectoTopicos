/*
 * El productor es cualquier proceso para producir
 * 18 productores tenemos 
 */

package Procesos;

import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class ProductorCorte extends Thread{
    
    private int idProductor, tiempo; // Solo hay 18
    private JProgressBar barra;
    private BufferTandas m;
    private boolean uso;
    
    public ProductorCorte(JProgressBar barra){
        this.barra = barra;
        barra.setString("Disponible");
        uso = false; // No está siendo usado al crarse
    }
    
    public void run(){
        // Mientras es usado
        while(uso){
            barra.setString("Ocupado");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error en run de productor");
            }
        }
        uso = true; // Para que vuelva a ser usado
    }
    
    /*
    @Override
    public void run(){
        int cont = 0;
        // Si no está siendo usado
        while (getEstado()){            
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
        setEstado(true); // Le indica que está libre
        barra.setValue(0);
        // Indicar al hilo consumidor que puede avanzar 
    }
    */
    public void actualizarBarra(int time){
        int val = barra.getValue() + time; //* 100 / tiempo;
        System.out.println("Valor time : " + time + "\n Valor de barra " + val);
        barra.setValue(val);
    }
    
    // false si esta libre
    public boolean getEstado(){
        return uso;
    }
    
    public void setEstado(boolean estado){
        uso = estado;
    }
    
}
