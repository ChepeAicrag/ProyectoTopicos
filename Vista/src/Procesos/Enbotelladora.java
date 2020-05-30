/**
 * Produce barriles de mezcal y Consume mezcal
 */
package Procesos;

import Componentes.ECP;
import Controlador.Controlador;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Enbotelladora extends Thread implements Productor, Consumidor{
    
    private int id;
    private boolean isAvailable;
    private BufferTandas tandasActualizar, bufferMezcalDestilado, bufferBarriles;
    //private BufferMezcalDestilado bufferMezcalDestilado; // Consume
    //private BufferBarriles bufferBarriles; // Produce
    private JProgressBar barra;
    private Color color;
    private Controlador c;
    private ECP etqBarra;
    
    public Enbotelladora(int id, BufferTandas bufferMezcalDestilado, BufferTandas bufferBarriles){
        this.id = id;
        isAvailable = true;
        this.bufferMezcalDestilado = bufferMezcalDestilado;
        this.bufferBarriles = bufferBarriles;
    }
    
    @Override
    public void run(){
        while (true) {            
            try {
                    isAvailable = false;
                    color = barra.getBackground();
                    consumir();
                    actualizarBarra(0);
                    ajustarBarra(color);
                }catch (InterruptedException ex) {
                    System.err.println(ex.getCause());
                }
        }
    }
    
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setFechaFinal(new Date());
        System.out.println("Tanda terminada lista >>>> \n " + tanda + " \n Fecha >> " + tanda.getFechaFinal());
        bufferBarriles.put(tanda); // Las produce
    }
    
    /** Produce los barriles*/
    private void enbotellar(Tanda tanda) throws InterruptedException{
        ArrayList<Object> mezcalesEliminar = new ArrayList();
        ArrayList<Object> barriles = new ArrayList();
        int total = tanda.getCantidadPinias(),
            cont = 0;
        ajustarBarra(tanda.getColor());
        for (int i = 0; i < numAnejamiento(tanda); i++) {
            for (Object mezcal : tanda.getPinias()) {
                sleep(2000); // Tiempo por estimar
                System.out.println(mezcal);
                Mezcal m = (Mezcal) mezcal;
                if(i == 0){
                    Barril barril = new Barril(m.getTipo());
                    barril.setEstatus('E'); // Listo  E: Enbotellado
                    barriles.add(barril);
                    mezcalesEliminar.add(mezcal);
                    System.out.println(barril);
                }
                cont++;
                actualizarBarra(cont * 100 / total);
            }
            cont = 0;
            barra.setString("Fase " + (i + 1));
            sleep(1000);
        }
        
        tanda.getPinias().removeAll(mezcalesEliminar); // Quita los mezcales
        tanda.getPinias().addAll(barriles); // Agrega los barriles
        tanda.setFechaFinal(new Date()); // Coloca la ficha final
        tanda.setEstado("Enbarrilada");
        tandasActualizar.put(tanda);
        //actualizarTabla(tanda);
        producir(tanda); // Ahora la tanda ya lleva objetos de mezcal
        etqBarra.setDatoBarra("---");
        isAvailable = true;
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = bufferMezcalDestilado.remove(); // Quita de las destilados
        tanda.setId_Enbotelladora(id);
        etqBarra.setDatoBarra("Tanda " + tanda.getId());
        enbotellar(tanda); // Las consume para hornear
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

    @Override
    public void producir() throws InterruptedException {}
    
    private int numAnejamiento(Tanda t){
        int numAnejamientos = 1;
        /** Blanco 2 meses * Resposado 12 meses * Añejo 24 meses * Madurado 10 meses*/
        int tipo = t.getTipoMezcal(); 
        if (tipo == 1) 
            numAnejamientos = 12; // Por 2 meses
        else if(tipo == 2)
            numAnejamientos = 6;
        else if(tipo == 3)
            numAnejamientos = 5;
        return numAnejamientos;    
    }
        
    public void setIdentificador(ECP etqBarra){
        this.etqBarra = etqBarra;
        this.barra = this.etqBarra.getBarra();
    }
    
    public void setTandasActualizar(BufferTandas tandasActualizar){
        this.tandasActualizar = tandasActualizar;
    }
}
