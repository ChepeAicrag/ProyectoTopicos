/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MVC_Principal;

import Controlador.Controlador;
import Modelo.Conexion;
import Vista.Vista;

/**
 * 
 * @author García García José Ángel
 */
public class MVC_main {
    
    public static void main(String[] args) {
        Vista v = new Vista();
        Conexion m = new Conexion("Mezcalera");
        Controlador c = new Controlador(v, m);
        v.conectarControlador(c);
    }
}
