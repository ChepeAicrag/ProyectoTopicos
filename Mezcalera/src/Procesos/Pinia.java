package Procesos;

/**
 * Clase para representar las Piñas de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Pinia {

    /** 
     * Tipos de estado:
     * N: Normal
     * C: Cortada
     * H: Horneada
     * T: Triturada
     */
    
    // Variable de instancia - Estatus de la piña.
    private char estatus = 'N';

    // Variable de instancia - Id a asignar a la piña.
    private static int _id = 0;

    // Variables de instancia - Tipo e id de la piña.
    private int id, tipo;

    /**
     * Constructor para objetos de Pinia.
     * @param tipo Tipo de la piña.
     */
    public Pinia(int tipo) {
        this.tipo = tipo;
        _id++;
        id = _id;
    }

    /**
     * Retorna el tipo de piña.
     *
     * return Tipo de piña.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de piña.
     *
     * @param tipo Tipo de piña a establecer.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna el estado de la piña.
     *
     * @return Estado de la piña.
     */
    public char getEstatus() {
        return estatus;
    }

    /**
     * Establece el estado de la piña.
     *
     * @param estatus Estado a establecer.
     */
    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    /**
     * Retorna la representación de la piña.
     * @return Represantción de la piña.
     */
    @Override
    public String toString() {
        return "Pinia{" + "id=" + id + ", tipo=" + tipo + ", estatus=" + estatus + '}';
    }
}
