/*
 *  Interfaz para los productores
 */
package Procesos;

/**
 *
 * @author García García José Ángel
 */
public interface Productor {

    //public void producir() throws InterruptedException;
    public void producir(Tanda tanda) throws InterruptedException;

}
