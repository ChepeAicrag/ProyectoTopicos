package Procesos;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase para mantener las tandas antes de pasarla a otro proceso.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class BufferTandas {

    // Variable de instancia - Almacenamiento de tandas.
    private Queue<Tanda> bufferTandas;

    /**
     * Constructor para objetos de BufferTandas.
     */
    public BufferTandas(){
        bufferTandas = new LinkedList();
    }

    /**
     * Almacena una tanda a la cola.
     *
     * @param tanda Tanda a almacenar.
     */
    public synchronized void put(Tanda tanda){
        bufferTandas.add(tanda);
        notifyAll();
    }

    /**
     * Remueve la primera tanda de la cola.
     *
     * @return Tanda de la cola.
     */
    public synchronized Tanda remove(){
        while (isEmpty()) {    
            System.out.println("Espera tanda inicial ::: " + Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error esperando tanda inicial ::: " + ex.getCause());
            }
        }
        notifyAll();
       return bufferTandas.remove();
    }

    /**
     * Verifica si el buffer está vacio.
     *
     * @return tre si está vacio y false de lo contario.
     */
    public boolean isEmpty(){
        return bufferTandas.isEmpty();
    }
}
