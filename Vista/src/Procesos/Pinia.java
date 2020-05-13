/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Procesos;

/**
 * 
 * @author García García José Ángel
 */
public class Pinia {
    private char tipo, estatus = 'N';
    private static int _id = 0;
    private int id;

    public Pinia(char tipo) {
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
        return "Pinia{" + "id=" + id + ", tipo=" + tipo + ", estatus=" + estatus + '}';
    }

    
    
    
}
