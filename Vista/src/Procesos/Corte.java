/*
 * Produce piñas cortadas
 */

package Procesos;

import Componentes.ECP;
import java.awt.Color;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Corte extends Thread implements Productor{
    
    private int id;
    private boolean isAvailable;
    private BufferTandas bufferTandas, tandasActualizar, bufferPiniasCortadas;
    //private BufferPiniasCortadas bufferPiniasCortadas;
    private JProgressBar barra;
    private Color color = Color.RED;
    private ECP etqBarra;
    
    public Corte(int id, BufferTandas bufferTandas, BufferTandas bufferPiniasCortadas){
        this.id = id;
        isAvailable = true;
        this.bufferTandas = bufferTandas;
        this.bufferPiniasCortadas = bufferPiniasCortadas;
    }
    
    
    @Override
    public synchronized void run(){
        while(true){
            try {
                        isAvailable = false;
                        color = barra.getBackground();
                        producir();
                        System.out.println("Corte terminado");
                        actualizarBarra(0);
                        ajustarBarra(color);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }
    
    private synchronized void cortar(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(), // Este es el 100
            cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000);
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('C');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        tanda.setEstado("Cortado");
        tandasActualizar.put(tanda); // La manda
        //actualizarTabla(tanda);
        bufferPiniasCortadas.put(tanda);
        etqBarra.setDatoBarra("---");
        isAvailable = true;
    }
    
    @Override
    public synchronized void producir() throws InterruptedException {
      Tanda tanda = bufferTandas.remove();
      System.out.println(" Yo hilo: " + Thread.currentThread().getName()
              + "\nTome la tanda: " + tanda);
      tanda.setId_Cortador(id);
      /** Aquí le inserta el id a tanda para identificar que el trabajó la tanda*/
      tanda.setFechaInicio(new Date());
      etqBarra.setDatoBarra("Tanda: " + tanda.getId());
      cortar(tanda);
    }
    
    @Override
    public void producir(Tanda tanda) throws InterruptedException {}
   
    public void setBarra(JProgressBar barra){
        this.barra = barra; 
    }
    
    public void actualizarBarra(int val){
        barra.setValue(val);
        if(val != 0)
            barra.setString(val + "%");
        else
            barra.setString("Libre...");
    }    
    
    private void ajustarBarra(Color color){
        barra.setBackground(color); // Color de la barra que se rellena
        barra.setForeground(Color.black); // Color de la barra que rellena
        if(this.color != color)
           barra.setBorder(BorderFactory.createLineBorder(color));
        else
           barra.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public boolean disponible(){
        return isAvailable;
    }
    
    public void setIdentificador(ECP etqBarra){
        this.etqBarra = etqBarra;
        this.barra = this.etqBarra.getBarra();
    }
    
    public void setTandasActualizar(BufferTandas tandasActualizar){
        this.tandasActualizar = tandasActualizar;
    }
}
