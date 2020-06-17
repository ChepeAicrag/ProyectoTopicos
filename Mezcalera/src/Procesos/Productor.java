package Procesos;

/**
 * Clase para indicar que es un Consumidor.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public interface Productor {

    /**
     * Produce una tanda a un buffer de tandas.
     *
     * @throws InterruptedException Error al colocar la tanda.
     */
    public void producir(Tanda tanda) throws InterruptedException;

}
