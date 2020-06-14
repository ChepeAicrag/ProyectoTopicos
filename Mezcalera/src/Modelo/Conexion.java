package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * @author Garcia Garcia Jose Angel
 */
public class Conexion {

    private static Connection coneccion = null; // Contenida en el pquete SQL
    private static Conexion conexion = null; // instancia a utilizar
    private static  int numConexiones = 0; // controla el numero de veces que sucedio
    
    private Conexion(String url, String usuario, String password){
        try {
            // Clase usada para una conexion con Derby
            Class.forName("org.postgresql.Driver");
            // Para MySql : "com.mysql.jdbc.Driver"
            // Para Postgres : "org.postgresql.Driver"
            coneccion = DriverManager.getConnection(url,usuario, password);
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,e);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Conexion getConexion(String url, String Usuario, String password){
        numConexiones++;
        if(conexion == null)
            return conexion = new Conexion(url, Usuario, password);
        return conexion;
     }
     
     public static Connection getConeccion(){
         return coneccion;
     }
     
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
