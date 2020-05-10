package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Garcia Garcia Jose Angel
 */
public class Conexion {

    private String host = "localhost";
    private String usuario = "postgres";
    private final String clave = "Dexter1998";
    private int puerto = 5432;
    private String servidor = "";
    private String baseDatos;
    private static Connection conexion = null;

    public Conexion(String baseDatos) {
        this.baseDatos = baseDatos;
        ConexionBd();
    }

    private void ConexionBd() {
        this.servidor = "jdbc:postgresql://" + host + ":" + puerto + "/" + baseDatos;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR AL REGISTRAR EL DRIVER " + e);
            System.exit(0);
        }
        try {
            conexion = DriverManager.getConnection(this.servidor,
                    this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
            System.exit(0); //parar la ejecución
        }
        System.out.println("Conectado a " + baseDatos);
    }

    private Connection getConexion() {
        return conexion;
    }

    public void closeConexion() {
        if (getConexion() != null) {
            try {
                getConexion().close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la bd " + e);
            }
        }
    }
    
    public boolean actualizarDato(String sql){  
        PreparedStatement ps;
        try{
            java.sql.Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            return true;
        }catch (SQLException e) {
            System.err.println("Error en la INSERCIÓN " + e );
            JOptionPane.showMessageDialog(null,"No se completó correctamente el proceso");
            return false;
        }
    }
    
    public List<Object[]> conexionConsultaBotellas(String sql){
    List <Object[]> datos = new ArrayList<Object[]>();
        try {
           Statement ps = conexion.createStatement();
           ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                String [] dat = new String[5];
                dat[0] = rs.getString(1);
                dat[1] = rs.getString(2);
                dat[2] = rs.getString(3);
                dat[3] = rs.getString(4);
                dat[4] = rs.getString(5);
                datos.add(dat);
            }
        }catch(SQLException e){
            System.err.println("Error al consultar botellas " + e);
        }
        return datos;
    }
    
    public List<Object[]> conexionConsultaMezcales(String sql){
    List <Object[]> datos = new ArrayList<Object[]>();
        try {
           Statement ps = conexion.createStatement();
           ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                String [] dat = new String[3];
                dat[0] = rs.getString(1);
                dat[1] = rs.getString(2);
                dat[2] = rs.getString(3);
                datos.add(dat);
            }
        }catch(SQLException e){
            System.err.println("Error al consultar mezcales " + e);
        }
        return datos;
    }
    


}
