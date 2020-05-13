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
public class Corte extends Thread implements Productor{
    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferTandas bufferTandas;//no se ocupa
    
    public Corte(int id, BufferTandas bufferTandas){
        this.id = id;
        isAvailable = true;
        this.bufferTandas = bufferTandas;
    }
    
    
    @Override
    public void run(){
        while(true){
            try {
                if(!bufferTandas.isEmpty()){
                   isAvailable = false;
                   producir();
                }else{
                    sleep(1000);
                    return;
                }
                
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }
    
    private void cortar(Tanda tanda) throws InterruptedException{
        for (Pinia pinia : tanda.getPinias()) {
            sleep(2000);
            System.out.println(pinia);
            pinia.setEstatus('C');
            System.out.println(pinia);
        }
        isAvailable = true;
    }
    
    @Override
    public void producir() throws InterruptedException {
      Tanda tanda = bufferTandas.remove();
      System.out.println(tanda);
      cortar(tanda);
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
    
    
    
    public int getIdCorte(){
        return id;
    }

    
    
    
}
