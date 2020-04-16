package MVC_Principal;

import Controlador.Controlador;
import Modelo.Conexion;
import Vista.Vista_Login;
import Vista.Vista_Registro;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author García García José Ángel
 */
public class PrincipalProyecto {
    public static void main(String[] args) {
        try {
            Conexion conexion = new Conexion("Mezcalera");
            Vista_Login v = new Vista.Vista_Login();
            Controlador c = new Controlador(conexion, v);
            Thread.sleep(1000); // Tiempo para que corra el hilo de la vista
            v.conectarControlador(c);
        } catch (InterruptedException ex) {
            Logger.getLogger(PrincipalProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
