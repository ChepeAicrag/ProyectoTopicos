/*
            //SwingUtilities.invokeLater(() -> {
            //});
 */
package Procesos;

import Vista.Trailer;
import Vista.VistaTraslado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author García García José Ángel
 */
public class Transportista extends Thread {

    private int id;
    private boolean isAvaliable;
    private BufferTandas tandasActualizar, bufferBarriles;
    private VistaTraslado v;

    public Transportista(int id, BufferTandas bufferBarriles, VistaTraslado v) {
        this.id = id;
        this.v = v;
        bufferBarriles = bufferBarriles;
    }

    public void run() {
        int i = 0;
        boolean op = true;
        while (true) {
            transporte();
        }
    }

    private synchronized void transporte() {
        //Tanda tanda = bufferBarriles.remove();
        System.out.println("Yo auto " + id );//+ " transpoto a la tanda \n " + tanda);
        int cliente = 1;//(int) Math.random() * 4;
        if (id == 1) 
            rutaTrailer1(cliente);
        else if(id == 2)
            rutaTrailer2(cliente);
        else
            rutaTrailer3(cliente);
        //tanda.setEstado("Entregada");
        //andasActualizar.put(tanda); // La manda a actualizar
        // 12 rutas
    }
    
    
    private void rutaTrailer1(int cliente){
       switch(cliente){
            case 1:
                entregaRecta(v.trailer1);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;   
        }
    }
    
    private void rutaTrailer2(int cliente){
        switch(cliente){
            case 1:
                entregaRecta(v.trailer2);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;    
        }
    }
    
    private void rutaTrailer3(int cliente){
        switch(cliente){
            case 1:
                entregaRecta(v.trailer3);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;    
        }
    }
    
    private void entregaRecta(Trailer t){
        while(t.getLocation().x <= 660){
                    v.mover(t,true);
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
        }
        System.out.println("Entregado");        
    }
    
    public void setTandasActualizar(BufferTandas tandasActualizar){
        this.tandasActualizar = tandasActualizar;
    }
}
