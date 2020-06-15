package Procesos;

/**
 * Interfaz para indicar que es un consumidor.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */

public interface Consumidor{

   /**
    * Consume una tanda de un buffer de tandas.
    *
    * @throws InterruptedException Error al remover la tanda.
    */
   public void consumir () throws InterruptedException;
}    
  
