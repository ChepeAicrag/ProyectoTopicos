package Procesos;

import Vista.Trailer;
import Vista.VistaTraslado;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase para representar al transportador de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Transportista extends Thread {

    // Variable de instancia - Identificador.
    private int id;

    // Variable de instancia - Disponibilidad.
    private boolean isAvailable;

    // Variables de instancia - Buffers de tandas para consumir y colocar.
    private BufferTandas tandasActualizar, bufferBarriles;

    // Variable de instancia - Array de indetificadores de tandas completadas.
    ArrayList<Integer> tandasTransportadas;

    // Variable de instancia - Vista que acualiza.
    private VistaTraslado v;

    // Constante de clase - Limite de proyección.
    private final int limite = 660;

    /**
     * Constructor para objetos de Transportista.
     * @param id Identificador del trasnportista.
     * @param bufferBarriles Buffer del que se consume las tandas.
     * @param v Vista que actualizará.
     */
    public Transportista(int id, BufferTandas bufferBarriles, VistaTraslado v) {
        this.id = id;
        this.v = v;
        this.bufferBarriles = bufferBarriles;
    }

    /**
     * Método para ejecutar las acciones de transporte.
     */
    public synchronized void run() {
        while (true) {
            try {
                transporte();
            }catch (Exception e){
                System.out.println("Error trasnportista : " + e);
            }
        }
    }

    /**
     * Método que determina que ruta se sigue.
     */
    private synchronized void transporte() {
        Tanda tanda = bufferBarriles.remove();
        if(tanda == null){
            System.out.println("Tanda es null");
            return;
        }
        System.out.println("Yo auto " + id + " transpoto a la tanda \n " + tanda);
        tanda.setEstado("Transportando");
        tandasActualizar.put(tanda);
        int cliente = new Random().nextInt(4) + 1;
        if (id == 1) {
            rutaTrailer1(cliente);
        } else if (id == 2) {
            rutaTrailer2(cliente);
        } else {
            rutaTrailer3(cliente);
        }
        tanda.setEstado("Entregada");
        tanda.setId_Transportador(id);
        tanda.setId_Cliente(cliente);
        tandasActualizar.put(tanda); // La manda a actualizar
        tandasTransportadas.add(tanda.getId()); // La guarda para indicar que la ha transportado
        System.out.println("Termine transporte\n" + tanda);
    }

    /**
     * Realiza la ruta del trailer 1.
     *
     * @param cliente Identificador del cliente al que se le entrega.
     */
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

    /**
     * Realiza la ruta del trailer 2.
     *
     * @param cliente Identificador del cliente al que se le entrega.
     */
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

    /**
     * Realiza la ruta del trailer 2.
     *
     * @param cliente Identificador del cliente al que se le entrega.
     */
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

    /**
     * Realiza el movimiento avanzar en una recta.
     * @param t Trailer que ejecuta el movimiento.
     * @param maximo Distancia máxima a avanzar.
     */
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
     * Simula un giro a la derecha.
     *
     * @param t Trailer que ejecuta la acción.
     * @param distanciaMaxima Distancia máxima para continuar despues del giro.
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

    /**
     * Entrega del trailer 1 a cualquier cliente
     * @param bajar Cantidad máxima para bajar.
     */
    private synchronized void Trailer1_ClienteN(int bajar) {
        entregaRecta(v.trailer1, 330);
        entregaGiroDerecha(v.trailer1, bajar);
        v.trailer1.setRotacion(0);
        entregaRecta(v.trailer1, limite);
    }

    /**
     * Simula un giro a la izquierda.
     *
     * @param t Trailer que ejecuta la acción.
     * @param distanciaMaxima Distancia máxima a recorrer despues del giro.
     */
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

    /**
     * Simula la ruta del trailer 2 al cliente 1 o 2.
     *
     * @param arriba Cantidad máxima para subir.
     */
    private synchronized void Trailer2_ClienteArriba(int arriba) {
        entregaRecta(v.trailer2, 220);
        entregaGiroIzquierda(v.trailer2, arriba);
        v.trailer2.setRotacion(0);
        entregaRecta(v.trailer2, limite);
    }

    /**
     * Simula la ruta del trailer 2 al cliente 4 o 5.
     * @param bajar Cantidad máxima a bajar.
     */
    private synchronized void Trailer2_ClienteAbajo(int bajar) {
        entregaRecta(v.trailer2, 220);
        entregaGiroDerecha(v.trailer2, bajar);
        v.trailer2.setRotacion(0);
        entregaRecta(v.trailer2, limite);
    }

    /**
     * Simula la ruta del trailer 3 a cualquier cliente.
     *
     * @param arriba Cantidad máxima a subir.
     */
    private synchronized void Trailer3_ClienteN(int arriba) {
        entregaRecta(v.trailer3, 300);
        entregaGiroIzquierda(v.trailer3, arriba);
        v.trailer3.rotar(0);
        entregaRecta(v.trailer3, limite);
    }

    /**
     * Establece el array de identificadores de tandas por transportar.
     *
     * @param tandasTransportar Array a establecer.
     */
    public void setTandasTransportadas(ArrayList<Integer> tandasTransportar){
        this.tandasTransportadas = tandasTransportadas;
    }

    /**
     * Establece el buffer de tandas a actualizar.
     *
     * @param tandasActualizar Buffer a establecer.
     */
    public void setTandasActualizar(BufferTandas tandasActualizar) {
        this.tandasActualizar = tandasActualizar;
    }
}
