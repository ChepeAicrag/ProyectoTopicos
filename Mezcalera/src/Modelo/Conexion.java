package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Clase Conexion para conectar con la BD utilizando el patrón de diseño Singleton.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Conexion {

    // Variable de clase - Contenida en el pquete SQL.
    private static Connection coneccion = null; //

    // Variable de calse - Instancia a utilizar.
    private static Conexion conexion = null;

    // Variable de clase - Número de conexiones.
    private static  int numConexiones = 0;

    /**
     * Constructor para un único objeto de la clase
     * @param password Contraseña de la BD
     * @param url Dirreción de la BD
     * @param usuario Usuario de la BD
     */
    private Conexion(String url, String usuario, String password){
        try {
            Class.forName("org.postgresql.Driver");
            coneccion = DriverManager.getConnection(url,usuario, password);
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,e);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método de la clase para retornar una instancia de la misma.
     * @param password Contraseña de la BD
     * @param url Dirreción de la BD
     * @param usuario Usuario de la BD
     *
     * @return instancia de la clase.
     */
    public static Conexion getConexion(String url, String usuario, String password){
        numConexiones++;
        if(conexion == null)
            return conexion = new Conexion(url, usuario, password);
        return conexion;
     }

     /**
      * Retorna un objeto de Coneccction.
      *
      * @return instancia de Conecction.
      */
     public static Connection getConeccion(){
         return coneccion;
     }
     
     /**
      * Método para cerrar la conexión con la BD
      *
      * return true si fue cerrada correctamente y
      *        false de lo contrario.
     */
     public boolean CerrarConexion(){
        try {
            if (coneccion != null) 
              if(numConexiones == 1){
                 coneccion.close();
                  System.out.println("Conexion cerrada.");
                 return true;
            }else
                numConexiones--;
            return false;
        } catch (SQLException e) {
            System.err.println(" Error al tratar de cerrar la conexion " + e);
       }
        return false;
    }
}
