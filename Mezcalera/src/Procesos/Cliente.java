/*
 * Comprador o cliente
 */

package Procesos;

/**
 * 
 * @author García García José Ángel
 */
public class Cliente {

    private int id;
    private String nombre;
    
    public Cliente(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
