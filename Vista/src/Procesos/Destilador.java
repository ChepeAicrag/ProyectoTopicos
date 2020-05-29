/**
 * Produce mezcal y Consume piñas fermentadas
 */
package Procesos;

import Componentes.ECP;
import Controlador.Controlador;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Destilador extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private int procesos;
    private BufferTandas tandasActualizar;
    private BufferPiniasFermentadas bufferPiniasFermentadas; // Consume de este
    private BufferMezcalDestilado bufferMezcalDestilado; // Produce a este
    private JProgressBar barra;
    private Color color;
    private Controlador c;
    private ECP etqBarra;
    
    public Destilador(int id,BufferPiniasFermentadas bufferPiniasFermentadas, BufferMezcalDestilado bufferMezcalDestilado){
        this.id = id;
        isAvailable = true;
        this.bufferPiniasFermentadas = bufferPiniasFermentadas;
        this.bufferMezcalDestilado = bufferMezcalDestilado;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                    isAvailable = false;
                    color = barra.getBackground();
                    consumir();
                    System.out.println("Destilador terminó");
                    actualizarBarra(0);
                    ajustarBarra(color);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
       bufferMezcalDestilado.put(tanda); // Las produce
    }
    
    /** Produce la pinia molida*/
    private void destilar(Tanda tanda) throws InterruptedException{
        ArrayList<Object> piniasEliminar = new ArrayList();
        ArrayList<Object> mezcales = new ArrayList();
        int total = tanda.getCantidadPinias(),
            cont = 0;
        ajustarBarra(tanda.getColor());
        for(int  i = 0; i < numDestilados(tanda);i++){
            for (Object pinia : tanda.getPinias()) {
                sleep(2000);
                System.out.println(pinia);
                if(i == 0){ // Para que solo 1 vez los agregue
                    Pinia p = (Pinia) pinia;
                    Mezcal mezcal = new Mezcal(p.getTipo()); // Asignamos el tipo
                    mezcal.setEstatus('D');
                    mezcales.add(mezcal);
                    piniasEliminar.add(pinia);
                    System.out.println(mezcal);
                }
                cont++;
                actualizarBarra(cont * 100 / total);
            }
            cont = 0;
            barra.setString("Destilado " + (i + 1));
            sleep(1000);
        }
        tanda.getPinias().removeAll(piniasEliminar); // Quita las piñas
        tanda.getPinias().addAll(mezcales); // Agrega los mezcales
        tanda.setEstado("Destilada");
        tandasActualizar.put(tanda);
        //actualizarTabla(tanda);
        producir(tanda); // Ahora la tanda ya lleva objetos de mezcal
        isAvailable = true;
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = bufferPiniasFermentadas.remove(); // Quita de las fermentadas
        destilar(tanda); // Las consume para hornear
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

    private int numDestilados(Tanda t){
        int numDestilados = 1;
        if (t.getPorcentajeAlcohol() == 48){
            numDestilados = 2;
        }else if (t.getPorcentajeAlcohol() == 37){
            numDestilados = 3;
        }
        return numDestilados;
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
    
    public void setTandasActualizar(BufferTandas tandasActualizar){
        this.tandasActualizar = tandasActualizar;
    }
}
