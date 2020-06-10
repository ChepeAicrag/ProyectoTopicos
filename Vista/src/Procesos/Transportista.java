/*
            //SwingUtilities.invokeLater(() -> {
            //});
 */
package Procesos;

import Vista.Trailer;
import Vista.VistaTraslado;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author García García José Ángel
 */
public class Transportista extends Thread {

    private int id;
    private boolean isAvaliable;
    private BufferTandas tandasActualizar, bufferBarriles;
    ArrayList<Integer> tandasTransportadas;
    private VistaTraslado v;
    private final int limite = 660;

    public Transportista(int id, BufferTandas bufferBarriles, VistaTraslado v) {
        this.id = id;
        this.v = v;
        this.bufferBarriles = bufferBarriles;
    }

    public synchronized void run() {
        while (true) {
            try {
                transporte();
            }catch (Exception e){
                System.out.println("Error trasnportistada : " + e);
            }
        }
    }

    private synchronized void transporte() {
        Tanda tanda = bufferBarriles.remove();
        if(tanda == null){
            System.out.println("Tanda es null");
            return;
        }
        System.out.println("Yo auto " + id + " transpoto a la tanda \n " + tanda);
        int cliente = new Random().nextInt(4) + 1;
        if (id == 1) {
            rutaTrailer1(cliente);
        } else if (id == 2) {
            rutaTrailer2(cliente);
        } else {
            rutaTrailer3(cliente);
        }
        tanda.setEstado("Entregada");
        // Falta modificar la base de datos para asignarle quien lo entregó
        tanda.setId_Transportador(id);
        tanda.setId_Cliente(cliente);
        tandasActualizar.put(tanda); // La manda a actualizar
        tandasTransportadas.add(tanda.getId()); // La guarda para indicar que la ha transportado
        System.out.println("Termine transporte\n" + tanda);
    }

    private synchronized void rutaTrailer1(int cliente) {
        Point p = v.trailer1.getLocation();
        switch (cliente) {
            case 1:
                entregaRecta(v.trailer1, limite);
                break;
            case 2:
                Trailer1_ClienteN(80);
                break;
            case 3:
                Trailer1_ClienteN(180);
                break;
            case 4:
                Trailer1_ClienteN(280);
                break;
            case 5:
                Trailer1_ClienteN(390);
                break;
        }
        v.trailer1.setLocation(p);
    }

    private synchronized void rutaTrailer2(int cliente) {
        Point p = v.trailer2.getLocation();
        switch (cliente) {
            case 1:
                Trailer2_ClienteArriba(v.trailer2.getLocation().y - 190);
                break;
            case 2:
                Trailer2_ClienteArriba(v.trailer2.getLocation().y - 80);
                break;
            case 3:
                entregaRecta(v.trailer2, limite);
                break;
            case 4:
                Trailer2_ClienteAbajo(v.trailer2.getLocation().y + 100);
                break;
            case 5:
                Trailer2_ClienteAbajo(v.trailer2.getLocation().y + 210);
                break;
        }
        v.trailer2.setLocation(p);
    }

    private synchronized void rutaTrailer3(int cliente) {
        Point p = v.trailer3.getLocation();
        switch (cliente) {
            case 1:
                Trailer3_ClienteN(v.trailer3.getLocation().y - 390);
                break;
            case 2:
                Trailer3_ClienteN(v.trailer3.getLocation().y - 290);
                break;
            case 3:
                Trailer3_ClienteN(v.trailer3.getLocation().y - 190);
                break;
            case 4:
                Trailer3_ClienteN(v.trailer3.getLocation().y - 80);
                break;
            case 5:
                entregaRecta(v.trailer3, limite);
                break;
        }
        v.trailer3.setLocation(p);
    }

    private synchronized void entregaRecta(Trailer t, int maximo) {
        try {
            while (t.getLocation().x <= maximo) {
                v.mover(t, true);
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Va recto, gira hacia abajo
     */
    private synchronized void entregaGiroDerecha(Trailer t, int distanciaMaxima) {
        t.rotar(Math.toRadians(90));
        while (t.getLocation().y <= distanciaMaxima) {
            v.mover(t, false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized void Trailer1_ClienteN(int bajar) {
        entregaRecta(v.trailer1, 330);
        entregaGiroDerecha(v.trailer1, bajar);
        v.trailer1.setRotacion(0);
        entregaRecta(v.trailer1, limite);
    }

    private synchronized void entregaGiroIzquierda(Trailer t, int distanciaMaxima) {
        t.rotar(Math.toRadians(-90));
        while (t.getLocation().y >= distanciaMaxima) {
            v.moverArriba(t);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized void Trailer2_ClienteArriba(int arriba) {
        entregaRecta(v.trailer2, 220);
        entregaGiroIzquierda(v.trailer2, arriba);
        v.trailer2.setRotacion(0);
        entregaRecta(v.trailer2, limite);
    }

    private synchronized void Trailer2_ClienteAbajo(int bajar) {
        entregaRecta(v.trailer2, 220);
        entregaGiroDerecha(v.trailer2, bajar);
        v.trailer2.setRotacion(0);
        entregaRecta(v.trailer2, limite);
    }

    private synchronized void Trailer3_ClienteN(int arriba) {
        entregaRecta(v.trailer3, 300);
        entregaGiroIzquierda(v.trailer3, arriba);
        v.trailer3.rotar(0);
        entregaRecta(v.trailer3, limite);
    }

    public void setTandasTransportadas(ArrayList<Integer> tandasTransportar){
        this.tandasTransportadas = tandasTransportadas;
    }
    public void setTandasActualizar(BufferTandas tandasActualizar) {
        this.tandasActualizar = tandasActualizar;
    }
}
