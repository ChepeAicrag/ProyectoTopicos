package MVC_Principal;

import Controlador.Controlador;
import Modelo.ManejoDatos;
import Vista.Vista;

/**
 * Clase principal para ejecutar el proyecto.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */

public class MVC_main {

    /**
     * Método principal de la clase.
     * @param args Argumentos.
     */

    public static void main(String[] args) {
        Vista v = new Vista();
        ManejoDatos m = new ManejoDatos();
        Controlador c = new Controlador(v, m);
        v.conectarControlador(c);
    }
}
