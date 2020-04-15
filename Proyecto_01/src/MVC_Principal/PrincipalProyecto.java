package MVC_Principal;

import Controlador.Controlador;
import Modelo.Conexion;
import Vista.Vista_Registro;

/**
 * 
 * @author García García José Ángel
 */
public class PrincipalProyecto {
    public static void main(String[] args) {
        Conexion conexion = new Conexion("Mezcalera");
        Vista_Registro v = new Vista_Registro();
        Controlador c = new Controlador(conexion, v);
        v.conectarControlador(c);
    }
}
