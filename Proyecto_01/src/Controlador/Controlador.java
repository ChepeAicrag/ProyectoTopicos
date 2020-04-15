/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Conexion;
import Modelo.Persona;
import Vista.Vista_Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * 
 * @author García García José Ángel
 */
public class Controlador implements ActionListener{

    private Vista_Registro v;
    private Conexion m;
    
    public Controlador(Conexion m, Vista_Registro v){
        this.v = v;
        this.m = m;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Persona p;
        String accion = ae.getActionCommand();
        switch(accion){
            case "siguiente":
                v.pPer.setVisible(false);
                v.pUser.setVisible(true);
                v.addPanelDataUser();
                v.contenedor.validate();
                v.deshabilitarBotones();
                break;
            case "atras":
                v.pUser.setVisible(false);
                v.pPer.setVisible(true);
                v.addPanelDataPer();
                v.contenedor.validate();
                v.deshabilitarBotones();
                break;
            case "registrar":
                    if(!v.txtContraseña.getText().isEmpty() || !v.txtCorreo.getText().isEmpty() ||
                       !v.txtCurp.getText().isEmpty() ||!v.txtEdad.getText().isEmpty() ||
                        !v.txtNombre.getText().isEmpty() || !v.txtUsuario.getText().isEmpty()
                     ){
                    String sexo = "M";
                    if(v.hombre.isSelected())
                        sexo = "H";
                    p = new Persona(v.txtCurp.getText(), v.txtNombre.getText(),sexo,Integer.parseInt(v.txtEdad.getText()));
                    ///String SqlPersona = "INSERT INTO MEZCAL.PERSONA (curp,sexo,edad,nombre) values(" + p.getCurp() + "),(" 
                    m.insertPersona(p);
                    String per = "P";
                    if(v.empleado.isSelected())
                        per = "C"; 
                    String SqlUser = "insert into mezcal.usuario(usuario,password,correo,permiso,curp) values('" 
                            + v.txtUsuario.getText() + "','" + v.txtContraseña.getText() + "','" 
                            + v.txtCorreo.getText() + "','" + per + "','" + p.getCurp() + "')";
                    m.actulizarDato(SqlUser);
                    int op = JOptionPane.showConfirmDialog(v,"Se ha registrado correctamente\n Se regresará al Loging");
                    if(op == JOptionPane.YES_OPTION)
                        System.exit(0);
                    }
                    break;
                case "limpiar":
                    v.txtNombre.setText("");
                    v.txtCurp.setText("");
                    v.txtEdad.setText("");
                    v.hombre.setSelected(false);
                    v.mujer.setSelected(false);
                    v.txtUsuario.setText("");
                    v.txtCorreo.setText("");
                    v.txtContraseña.setText("");
                    v.productor.setSelected(false);
                    v.empleado.setSelected(false);
                    break;
                case "cancelar":
                    int op = JOptionPane.showConfirmDialog(v, "Se ha cancelado el proceso");
                    if(op == JOptionPane.YES_OPTION){
                            v.terminar();
                    }
                    break;
                    
        }
    }

}
