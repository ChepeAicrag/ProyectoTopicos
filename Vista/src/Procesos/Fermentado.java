/**
 * Produce piñas fermentadas y Consume piñas trituradas
 */
package Procesos;

import Componentes.ECP;
import Controlador.Controlador;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Fermentado extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private BufferTandas tandasActualizar, bufferPiniasMolidas, bufferPiniasFermentadas;
    //private BufferPiniasMolidas bufferPiniasMolidas; //  Produce
    //private BufferPiniasFermentadas bufferPiniasFermentadas; // Consume de este
    private JProgressBar barra;
    private Color color;
    private Controlador c;
    private ECP etqBarra;
    
    public Fermentado(int id, BufferTandas bufferPiniasMolidas, BufferTandas bufferPiniasFermentadas){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasMolidas = bufferPiniasMolidas;
        this.bufferPiniasFermentadas = bufferPiniasFermentadas;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                    isAvailable = false;
                    color = barra.getBackground();
                    consumir();
                    System.out.println("Fermentador terminado");
                    actualizarBarra(0);
                    ajustarBarra(color);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
       bufferPiniasFermentadas.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private void fermentar(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(),
            cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('F'); // Triturada o molida
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        tanda.setEstado("Fermentada");
        tandasActualizar.put(tanda);
        //actualizarTabla(tanda);
        producir(tanda);
        etqBarra.setDatoBarra("---");
        isAvailable = true;
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasMolidas.remove(); // Quita de las molidas
        tanda.setId_Fermentador(id);
        etqBarra.setDatoBarra("Tanda " + tanda.getId());
        fermentar(tanda); // Las consume para hornear
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
