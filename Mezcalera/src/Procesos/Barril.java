package Procesos;

/**
 * Clase controladora, aquí se encuentra la parte lógica del proyecto.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Barril extends Producto{

    // Variable de instancia - Conocer la capicidad de nuestro barril.
    private String capacidadBarril;

    /**
     * Constructor para objetos de Barril.
     *
     * @param tipo Tipo de Barril.
     */
    public Barril(int tipo) {
        super(tipo);
    }

    /**
     * Cambiar el estado del producto.
     *
     * @param estado Estado a asignar.
     */
    @Override
    public void cambiarEstado(char estado) {
        setEstado('L');
    }

    /**
     * Retorna la capacidad del barril.
     *
      * @return Capacidad del barrl.
     */
    public String getCapacidadBarril() {
        return capacidadBarril;
    }

    /**
     * Establece la capacidad del barril.
     *
     * @param capacidadBarril Capacidad a establecer.
     */
    public void setCapacidadBarril(String capacidadBarril) {
        this.capacidadBarril = capacidadBarril;
    }
}
