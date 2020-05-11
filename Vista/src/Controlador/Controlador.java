/*
 * Clase Controlador
 */

package Controlador;

import Modelo.Conexion;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * 
 * @author García García José Ángel
 */
public class Controlador implements ActionListener{
 
    private Vista v;
    private Conexion m;
    
    public Controlador(Vista v, Conexion m){
        this.v = v;
        this.m = m;
        actualizarOpciones();
    }
    
    private void actualizarOpciones(){
        ArrayList<String> porcentajes = m.conexionConsultarPorcentajeOTipo("select * from mezcalera.GradoAlcohol");
        ArrayList<String> tipos = m.conexionConsultarPorcentajeOTipo("select * from mezcalera.TipoMezcal");
        v.llenarOpciones(porcentajes, tipos);
    }
    private int cantPinas = 0, // Cantidad de piñas a usar
            id_Maguey = 0, // id maguey seleccionado
            limite = 0;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String o = ae.getActionCommand();
        if(o.equals("1") || o.equals("2") || o.equals("3") || o.equals("4") || 
           o.equals("5") || o.equals("6") || o.equals("7") || o.equals("8")){
            System.out.println("Selecciono el btn " + o);
            id_Maguey = Integer.parseInt(o);
            //limite = m.consultarPinas(id_Maguey);
            //cantPinas = cantidadPinas(limite);
        }
        switch(o){
            
            case "producir": //Instrucción del boton producir
                System.out.println("Produciendo");
                if(id_Maguey > 0 && cantPinas > 0){
                    String sql = "update mezcalera.maguey set cantidadPinas=" 
                        + (limite - cantPinas) + " where id_Maguey=" + id_Maguey;
                    JOptionPane.showMessageDialog(v, sql);
                     //m.actualizarDato(sql);
                    v.principal.setSelectedIndex(1);
               }else{
                    JOptionPane.showMessageDialog(v,"No rellenó correctamente");
                }
                break;
                
        
        }
    }
    
    /** 
     * Solicita la cantidad de piñas y las valida
     * @param limite Cantidad maxima de piñas a usar
     */
    private int cantidadPinas(int limite){
        int cantidad = 0;
                
        boolean op = false;
        while (!op) {            
            try{
                String msj = JOptionPane.showInputDialog(v, "Introduce la cantidad de piñas a usar");
                if (msj.isEmpty())
                    msj = "0";
                cantidad = Integer.parseInt(msj);
            if(cantidad > limite)
                new Exception("Limite pasado");
            else 
                op = true;
            }catch(NumberFormatException e){
                System.out.println(e.toString());
            }
        }
        return cantidad;
    }
}
