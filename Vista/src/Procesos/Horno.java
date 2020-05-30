/*
 * Produce piñas horneadas y Consume piñas cortadas
 */

package Procesos;

import Componentes.ECP;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Horno extends Thread implements Productor, Consumidor{

    
    private int id;
    private boolean isAvailable;
    //private BufferPiniasCortadas bufferPiniasCortadas;
    private BufferTandas tandasActualizar, bufferPiniasHorneadas, bufferPiniasCortadas; // Consume de este
    //private BufferPiniasHorneadas bufferPiniasHorneadas; // Produce en este
    private JProgressBar barra;
    private Color color;
    private ECP etqBarra;
    
    public Horno(int id, BufferTandas bufferPiniasCortadas, BufferTandas bufferPiniasHorneadas){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasCortadas = bufferPiniasCortadas;
        this.bufferPiniasHorneadas = bufferPiniasHorneadas;
    }
    
    @Override
    public synchronized void run(){
        while (true) {            
            try {
                    isAvailable = false;
                    color = barra.getBackground();
                    consumir();
                    System.out.println("Horno consumió");
                    actualizarBarra(0);
                    ajustarBarra(color);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public synchronized void producir(Tanda tanda) throws InterruptedException {
        bufferPiniasHorneadas.put(tanda); // Las produce
    }
    
    /** Produce la pinia horneada*/
    private synchronized void hornear(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(),
            cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('H');
            cont++;
            System.out.println(pinia);
            actualizarBarra(cont * 100 / total);
        }
        tanda.setEstado("Horneada");
        tandasActualizar.put(tanda); // Le indica al controlador que actulice la BD
        producir(tanda);
        etqBarra.setDatoBarra("---");
        isAvailable = true;
    }

    @Override
    public synchronized void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasCortadas.remove(); // Quita de las cortadas
        tanda.setId_Cortador(id);
        etqBarra.setDatoBarra("Tanda " + tanda.getId());
        hornear(tanda); // Las consume para hornear
    }
    
    @Override
    public void producir() throws InterruptedException {}

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

    public void setIdentificador(ECP etqBarra){
        this.etqBarra = etqBarra;
        this.barra = this.etqBarra.getBarra();
    }
    
    public void setTandasActualizar(BufferTandas tandasActualizar){
        this.tandasActualizar = tandasActualizar;
    }
}
