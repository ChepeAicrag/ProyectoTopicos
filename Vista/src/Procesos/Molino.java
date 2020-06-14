/**
 * Produce piñas trituradas y Consume piñas horneadas
 */
package Procesos;

import Componentes.ECP;
import Controlador.Controlador;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.BorderFactory;

/**
 * 
 * @author García García José Ángel
 */
public class Molino extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private BufferTandas tandasActualizar, bufferPiniasHorneadas, bufferPiniasMolidas;
    //private BufferPiniasHorneadas bufferPiniasHorneadas; // Consume de este
    //private BufferPiniasMolidas bufferPiniasMolidas; //  Produce
    private JProgressBar barra;
    private Color color;
    private Controlador c;
    private ECP etqBarra;
    
    public Molino(int id,BufferTandas bufferPiniasHorneadas, BufferTandas bufferPiniasMolidas){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasHorneadas = bufferPiniasHorneadas;
        this.bufferPiniasMolidas = bufferPiniasMolidas;
    }
    
    @Override
    public synchronized void run(){
        while (true) {            
            try {
                    isAvailable = false;
                    color = barra.getBackground();
                    consumir();
                    System.out.println("Molino trabajó");
                    actualizarBarra(0);
                    ajustarBarra(color);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public synchronized void producir(Tanda tanda) throws InterruptedException {
       bufferPiniasMolidas.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private synchronized void moler(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(),
            cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('T');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        tanda.setEstado("Trituarada");
        tandasActualizar.put(tanda);
        /**
         * Esta forma no me gusta, rompe el patrón
         * actualizarTabla(tanda);
         */
        producir(tanda);
        etqBarra.setDatoBarra("---");
        isAvailable = true;
    }

    @Override
    public synchronized void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasHorneadas.remove(); // Quita de las horneadas
        tanda.setId_Triturador(id);
        etqBarra.setDatoBarra("Tanda " + tanda.getId());
        moler(tanda); // Las consume para hornear
    }

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
    
    public void conectarControlador(Controlador c){
        this.c = c;
    }
    
    public void actualizarTabla(Tanda t){
        c.m.updateEstadoTanda(t);
        c.cargarDatosTandas();
    }
    
    public void setIdentificador(ECP etqBarra){
        this.etqBarra = etqBarra;
        this.barra = this.etqBarra.getBarra();
    }

    public boolean disponible(){return isAvailable;}

    public void setTandasActualizar(BufferTandas tandasActualizar){
        this.tandasActualizar = tandasActualizar;
    }
}    
