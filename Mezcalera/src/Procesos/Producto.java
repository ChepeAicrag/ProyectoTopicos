package Procesos;

/**
 * Clase para el equipo Destilador.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public abstract class Producto {

    // Variable de instancia - Estado del producto.
    private char estado = 'D';

    // Variable de clase - Id a asignar al producto.
    private static int _id = 0;

    // Variables de instancia - Tipo e Id de barril.
    private int id, tipo;

    /**
     * Constructor para objetos de Producto.
     *
     * @param tipo Tipo de Producto.
     */
    public Producto(int tipo) {
        this.tipo = tipo;
        _id++;
        id = _id;
    }

    /**
     * Retorna el tipo de producto.
     *
     * @return Tipo de producto.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de producto.
     *
     * @param tipo Tipo a establecer.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna el estado actual del producto.
     *
     * @return El estado del producto.
     */
    public char getEstado() {
        return estado;
    }

    /**
     * Establece el estado del producto.
     *
     * @param estado Estado a establecer.
     */
    protected void setEstado(char estado) {
        this.estado = estado;
    }

    /**
     * Retorna una representación del producto.
     *
     * @return Datos del producto.
     */
    @Override
    public String toString() {
        return "producto{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + '}';
    }

    /**
     * Cambiar el estado del producto.
     * @param estado Estado a asignar.
     */
    public abstract void cambiarEstado(char estado);
}
