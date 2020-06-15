package Procesos;

/**
 * Clase para representar el Mezcal de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Mezcal {

    // Variable de instancia - Estatus del mezcal.
    private char estatus = 'D';

    // Variable de clase - Id a asignar al mezcal.
    private static int _id = 0;

    // Variables de instancia - Tipo e Id de barril.
    private int id, tipo;

    /**
     * Constructor para objetos de Mezcal.
     *
     * @param tipo Tipo de Mezcal.
     */
    public Mezcal(int tipo) {
        this.tipo = tipo;
        _id++;
        id = _id;
    }

    /**
     * Retorna el tipo de mezcal.
     *
     * @return Tipo de mezcal.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de mezcal.
     *
     * @param tipo Tipo a establecer.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna el estado actual del mezcal.
     *
     * @return El estado del mezcal.
     */
    public char getEstatus() {
        return estatus;
    }

    /**
     * Establece el estado del mezcal.
     *
     * @param estatus Estado a establecer.
     */
    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    /**
     * Retorna una representación del mezcal.
     *
     * @return Datos del mezcal.
     */
    @Override
    public String toString() {
        return "Mezcal{" + "id=" + id + ", tipo=" + tipo + ", estatus=" + estatus + '}';
    }
}
