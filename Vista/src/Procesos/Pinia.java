/*
 * Piña que contiene cada tanda
 */

package Procesos;

/**
 * 
 * @author García García José Ángel
 */
public class Pinia {
    /** 
     * Tipos :
     * N: Normal
     * C: Cortada
     * H: Horneada
     * T: Triturada
     */
    private char estatus = 'N';
    private static int _id = 0;
    private int id, tipo;

    public Pinia(int tipo) {
        this.tipo = tipo;
        _id++;
        id = _id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Pinia{" + "id=" + id + ", tipo=" + tipo + ", estatus=" + estatus + '}';
    }
}
