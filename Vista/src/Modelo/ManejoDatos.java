/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Procesos.Tanda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author García García José Ángel
 */
public class ManejoDatos {

    private Connection conexion; // Acceso a conexion
    private Conexion crearConexion;// Crea conexion
    private String host = "localhost";
    private String usuario = "postgres";
    private final String clave = "Dexter1998";
    private int puerto = 5432;
    private String baseDatos = "Mezcalera";

    public ManejoDatos() {
        try {
            crearConexion = crearConexion.getConexion("jdbc:postgresql://" + host + ":" + puerto
                    + "/" + baseDatos, usuario, clave);
            conexion = crearConexion.getConeccion();
        } catch (Exception e) {
            System.out.println(e.getCause());

        }
        System.out.println("Conectado a " + baseDatos);
    }

    public Object selectValueDe(String sql){
        PreparedStatement ps;
        Object dato = null;
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                dato = rs.getObject(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener dato \n " + e);
        }
        return dato;
    }
    
    public Object[] selectMaguey(int id) {
        PreparedStatement ps;
        Object datos[] = new Object[3];
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("select * from mezcal.maguey where id_maguey=" + id);
            if(rs.next()){
                datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getInt(3);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las piñas del maguey " + id + " \n " + e);
        }
        return datos;
    }

    //Método que regresa en una  lista, los clientes registrados en la bd
    public List<Object[]> conexionConsultaTanda(String sql) {
        PreparedStatement ps;
        ResultSet rs;
        List<Object[]> datos = new ArrayList<Object[]>();
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dat[] = new String[6];
                /**
                 * id_tanda, tipoMaguey, gradoAlcohol,
                 * tipoMezcal,cantidadPinias, status
                 */
                dat[0] = String.valueOf((Integer) rs.getInt(1));
                dat[1] = (String) selectValueDe("select nombre from mezcal.maguey where id_maguey = " + rs.getString(2));
                dat[2] = String.valueOf((Double)selectValueDe("select valor from mezcal.gradoalcohol where id_grado = " + rs.getInt(3)));
                dat[3] = (String) selectValueDe("select nombre from mezcal.tipomezcal where id_tipo = " + rs.getInt(4));
                dat[4] = String.valueOf((Integer) rs.getInt(5));
                dat[5] = rs.getString(6);
                datos.add(dat);
            }
        } catch (SQLException e) {
            // System.err.println("Error al CARGAR DATOS " + e);
        }
        return datos;
    }
     
     public List<Object[]> conexionConsultaInformeTanda(String sql) {
        PreparedStatement ps;
        ResultSet rs;
        List<Object[]> datos = new ArrayList<Object[]>();
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dat[] = new String[15];
                dat[0] = String.valueOf((Integer) rs.getInt(1));
                dat[1] = (String) selectValueDe("select nombre from mezcal.maguey where id_maguey = " + rs.getString(2));
                dat[2] = String.valueOf((Double)selectValueDe("select valor from mezcal.gradoalcohol where id_grado = " + rs.getInt(3)));
                dat[3] = (String) selectValueDe("select nombre from mezcal.tipomezcal where id_tipo = " + rs.getInt(4));
                dat[4] = String.valueOf((Integer) rs.getInt(5));
                dat[5] = rs.getString(6);
                dat[6] = (String) selectValueDe("select nombre from mezcal.cortador where id_cortador = " + rs.getInt(7));
                dat[7] = (String) selectValueDe("select nombre from mezcal.horno where id_horno = " + rs.getInt(8));
                dat[8] = (String) selectValueDe("select nombre from mezcal.molino where id_molino = " + rs.getInt(9));
                dat[9] = (String) selectValueDe("select nombre from mezcal.fermentador where id_fermentador = " + rs.getInt(10));
                dat[10] = (String) selectValueDe("select nombre from mezcal.destilador where id_destilador = " + rs.getInt(11));
                dat[11] = (String) selectValueDe("select nombre from mezcal.enbotelladora where id_enbotelladora = " + rs.getInt(12));
                dat[12] = "Pendiente"; // Pendiente el cliente
                SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                dat[13] = (rs.getDate(14) == null) ? "Null" : dt1.format(rs.getDate(14));
                dat[14] = (rs.getDate(15) == null) ? "Null" : dt1.format(rs.getDate(15));
                datos.add(dat);
            }
        } catch (SQLException e) {
            // System.err.println("Error al CARGAR DATOS " + e);
        }
        return datos;
    }
    
    

    public boolean insertTanda(Tanda t) {
        PreparedStatement ps;
        String sqlInsertTanda = "insert into mezcal.tanda (tipomaguey,gradoalcohol,tipoMezcal,cantidadPinias"
                + ",status,fecha_inicio) values (?,?,?,?,?,?);";
        try {
            ps = conexion.prepareStatement(sqlInsertTanda);
            //ps.setInt(1, t.getId());
            ps.setInt(1, t.getTipoMaguey());
            ps.setInt(2, t.getPorcentajeAlcohol());
            ps.setInt(3, t.getTipoMezcal());
            ps.setInt(4, t.getCantidadPinias());
            ps.setString(5, t.getEstado());
            ps.setDate(6, (Date) t.getFechaInicio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error en la INSERCIÓN " + e);
            return false;
        }
    }

    public ArrayList<String> conexionConsultarPorcentajeOTipo(String sql) {
        ArrayList<String> datos = new ArrayList<>();
        try {
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                datos.add(rs.getString(2)); // La segunda columna en ambos
            }
        } catch (SQLException e) {
        }
        return datos;
    }

    public boolean deleteTanda(Tanda t) {
        PreparedStatement ps;
        String sqlDeleteCliente = "delete from mezcal.tanda where id_tanda = ?;";
        try {
            ps = conexion.prepareStatement(sqlDeleteCliente);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error en el BORRADO " + e);
            return false;
        }
    }
    
    public Tanda selectTanda(Tanda t){
        PreparedStatement ps;
        ResultSet rs;
        Tanda tandaEncontrada = null;
        String sqlConsulta = "select * from mezcal.tanda where id_tanda = ?;";
        try{
            ps  = conexion.prepareStatement(sqlConsulta);
            ps.setInt(1,t.getId());
            rs  = ps.executeQuery();
            if(rs.next()){
                tandaEncontrada = new Tanda(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
                tandaEncontrada.setId(rs.getInt(1));
                tandaEncontrada.setEstado(rs.getString(6));
                tandaEncontrada.setId_Cortador(rs.getInt(7));
                tandaEncontrada.setId_Horneador(rs.getInt(8));
                tandaEncontrada.setId_Triturador(rs.getInt(9));
                tandaEncontrada.setId_Fermentador(rs.getInt(10));
                tandaEncontrada.setId_Destilador(rs.getInt(11));
                tandaEncontrada.setId_Enbotelladora(rs.getInt(12));
                tandaEncontrada.setId_Cliente(rs.getInt(13));
                tandaEncontrada.setFechaInicio(rs.getDate(14));
                tandaEncontrada.setFechaFinal(rs.getDate(15));
            }
        }catch (SQLException e) {
            System.err.println("Error al CARGAR TANDA " + e);
        }
        return tandaEncontrada;
    }
    
    public boolean updatePinias(Tanda t){
        PreparedStatement ps;
        String sqlUpdateMaguey = "update mezcal.maguey set \"cantidadPinia\" = ? where id_maguey = " + t.getTipoMaguey() + ";";
        try{
            ps  = conexion.prepareStatement(sqlUpdateMaguey);
            int valor = (int) selectValueDe("select \"cantidadPinia\" from mezcal.maguey where id_maguey = " + t.getTipoMaguey())
                    - t.getCantidadPinias();
            ps.setInt(1,valor);
            ps.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.err.println("Error en la MODIFICACION de piñas \n" + e);
            return false;
        }
    }
    
    // Actualiza el id de la tanda 
    public boolean updateEstadoTanda(Tanda t){
        PreparedStatement ps;
        String sqlUpdateTanda = "";
        boolean completa = false;
        if (t.getEstado().equals("Enbarrilada")) {
            
        
        //if(t.getId_Cortador() != 0 && t.getId_Horneador() != 0 && t.getId_Triturador() != 0 && t.getId_Fermentador() != 0 
        //            && t.getId_Destilador() != 0 && t.getId_Enbotelladora() != 0 && t.getId_Cliente() != 0){
            sqlUpdateTanda = "update mezcal.tanda set "
                + "status = ?, id_Cortador = ?, id_Horno = ?, id_Molino = ?,"
                + "id_Fermentador = ?, id_Destilador = ?, id_Enbotelladora = ?"
                + ", fecha_inicio = ?, fecha_final = ? where id_tanda = ?;";
            completa = true;
        }
        else
            sqlUpdateTanda = "update mezcal.tanda set status = ? where id_tanda = ?;";
        try{
            ps  = conexion.prepareStatement(sqlUpdateTanda);
            System.out.println(t);
            ps.setString(1,t.getEstado());
            if(completa){
                ps.setObject(2, t.getId_Cortador());
                ps.setObject(3, t.getId_Horneador());
                ps.setInt(4, t.getId_Triturador());
                ps.setInt(5, t.getId_Fermentador());
                ps.setInt(6, t.getId_Destilador());
                ps.setInt(7, t.getId_Enbotelladora());
                ps.setDate(8, new Date(t.getFechaInicio().getTime()));
                ps.setDate(9, new Date(t.getFechaFinal().getTime()));
                ps.setInt(10,t.getId());
            }else
                ps.setInt(2, t.getId());
            ps.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.err.println("Error en la MODIFICACION de Estado de tanda \n" + e);
            return false;
        }
    }
    
}
