package Procesos;

/**
 * Clase controladora, aquí se encuentra la parte lógica del proyecto.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Barril{

    // Variable de instancia - Estatus del barril.
    private char estatus = 'N';

    // Variable de clase - Id a asignar al barril.
    private static int _id = 0;

    // Variables de instancia - Tipo e Id de barril.
    private int id, tipo;

    /**
     * Constructor para objetos de Barril.
     *
     * @param tipo Tipo de Barril.
     */
    public Barril(int tipo) {
        this.tipo = tipo;
        _id++;
        id = _id;
    }

    /**
     * Retorna el tipo de barril.
     *
     * return Tipo de barril.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de barril.
     *
     * @param tipo Tipo de barril a establecer.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna el estado del barril.
     *
     * @return Estado del barril.
     */
    public char getEstatus() {
        return estatus;
    }

    /**
     * Establece el estado del barril.
     *
     * @param estatus Estado a establecer.
     */
    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    /**
     * Retorna la representación del barril.
     * @return Represantción del barril.
     */
    @Override
    public String toString() {
        return "Barril{" + "id=" + id + ", tipo=" + tipo + ", estatus=" + estatus + '}';
    }
}
