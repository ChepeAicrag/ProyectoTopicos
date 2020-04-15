package Modelo;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
    
    public boolean insertPersona(Persona p){
        PreparedStatement ps;
        String sqlInsertCliente = "insert into mezcal.persona values (?,?,?,?);";
        try{
            ps  = getConexion().prepareStatement(sqlInsertCliente);
            ps.setString(1, p.getCurp());
            ps.setString(2, p.getSexo());
            ps.setInt(3, p.getEdad());
            ps.setString(4, p.getNombre());
            ps.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.err.println("Error en la INSERCIÓN de Persona" + e );
            return false;
        }
    }
    
    public boolean actulizarDato(String sql){  
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
    
    public List <Object[]> conexionConsultaClientes(String sql){
        List <Object[]> datos = new ArrayList<Object[]>();
        try {
           Statement ps = conexion.createStatement();
           ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                // Estructura del registro activiad
                String [] dat = new String[4];
                dat[0] = rs.getString(1);
                dat[1] = rs.getString(2);
                dat[2] = String.valueOf(rs.getInt(3));
                dat[3] = rs.getString(4);
                datos.add(dat);
            }
        } catch (Exception e) {
            System.err.println("Error al consultar clientes " + e);
        }
        return datos;
    }
    
    public boolean deleteCliente(Persona p){
        PreparedStatement ps;
        String sqlDeleteCliente = "delete from mezcal.persona where curp = ?;";
        try{
            ps  = getConexion().prepareStatement(sqlDeleteCliente);
            ps.setString(1,p.getCurp());
            ps.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.err.println("Error en el BORRADO "+ e);
            return false;
        }
    }

}
