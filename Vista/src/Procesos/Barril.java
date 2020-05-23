/*
 * Barril para vender
 */

package Procesos;

/**
 * 
 * @author García García José Ángel
 */
public class Barril{
    
    private char tipo, estatus = 'N';
    private static int _id = 0;
    private int id;

    public Barril(char tipo) {
        this.tipo = tipo;
        _id++;
        id = _id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
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
        return "Barril{" + "id=" + id + ", tipo=" + tipo + ", estatus=" + estatus + '}';
    }
}
