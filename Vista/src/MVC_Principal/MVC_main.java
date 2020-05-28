/*
 * Clase para ejecutar todo el proyecto
 */

package MVC_Principal;

import Controlador.Controlador;
import Modelo.Conexion;
import Modelo.ManejoDatos;
import Vista.Vista;

/**
 * 
 * @author García García José Ángel
 */
public class MVC_main {
    
    public static void main(String[] args) {
        Vista v = new Vista();
        ManejoDatos m = new ManejoDatos();
        Controlador c = new Controlador(v, m);
        v.conectarControlador(c);
    }
}
