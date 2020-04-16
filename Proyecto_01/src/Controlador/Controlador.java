/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Persona;
import Modelo.Usuario;
import Vista.ModeloTablaBotellas;
import Vista.Vista_Consumidor;
import Vista.Vista_Login;
import Vista.Vista_Productor;
import Vista.Vista_Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author García García José Ángel
 */
public class Controlador implements ActionListener {

    private Vista_Login vP;
    private Vista_Registro v;
    private Vista_Productor vPro;
    private Vista_Consumidor vCon;
    private ModeloTablaBotellas mtb;
    private Conexion m;

    public Controlador(Conexion m, Vista_Login vistaLogin) {
        this.vP = vistaLogin;
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Persona p;
        String accion = ae.getActionCommand();
        switch (accion) {
            case "registrar_Login":
                v = new Vista_Registro() {
                    public void dispose() {
                        vP.setVisible(true);
                        v.setVisible(false);
                    }
                };
                v.conectarControlador(this);
                v.setVisible(true);
                vP.dispose();
                break;
            case "ingresar":
                Usuario u = m.login(vP.txtUsuario.getText(), vP.txtContraseña.getText());
                if(u != null){
                    JOptionPane.showMessageDialog(vP, "Entrando al sistema " + u.getUsuario());
                    vP.txtContraseña.setText("");
                    vP.txtUsuario.setText("");
                    vP.dispose();
                    accionDadoPermiso(u.getPermiso());
                }else{
                    JOptionPane.showMessageDialog(vP, "Acceso denegado");
                }
                break;

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
                if (!v.txtContraseña.getText().isEmpty() || !v.txtCorreo.getText().isEmpty()
                        || !v.txtCurp.getText().isEmpty() || !v.txtEdad.getText().isEmpty()
                        || !v.txtNombre.getText().isEmpty() || !v.txtUsuario.getText().isEmpty()) {
                    String sexo = "M";
                    if (v.hombre.isSelected()) {
                        sexo = "H";
                    }
                    p = new Persona(v.txtCurp.getText(), v.txtNombre.getText(), sexo, Integer.parseInt(v.txtEdad.getText()));
                    ///String SqlPersona = "INSERT INTO MEZCAL.PERSONA (curp,sexo,edad,nombre) values(" + p.getCurp() + "),(" 
                    m.insertPersona(p);
                    String per = "P";
                    if (v.empleado.isSelected()) {
                        per = "C";
                    }
                    String SqlUser = "insert into mezcal.usuario(usuario,password,correo,permiso,curp) values('"
                            + v.txtUsuario.getText() + "','" + v.txtContraseña.getText() + "','"
                            + v.txtCorreo.getText() + "','" + per + "','" + p.getCurp() + "')";
                    m.actualizarDato(SqlUser);
                    JOptionPane.showMessageDialog(v, "Se ha registrado correctamente\n Se regresará al Loging");
                    v.dispose();
                    limpiarLoginYRegistrarse();
                   
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
                int op = JOptionPane.showConfirmDialog(v, "Se cancelará el proceso");
                if (op == JOptionPane.YES_OPTION) {
                    v.dispose();
                    limpiarLoginYRegistrarse();
                }
                break;

        }
    }

    public void limpiarLoginYRegistrarse() {
        v.limpiar.doClick(); // Hago que se limpien los datos de la vista registrar
        // Limpiar los campos del login
        vP.txtContraseña.setText("");
        vP.txtUsuario.setText("");
    }

    public void accionDadoPermiso(String permiso){
        if (permiso.equals("P")) {
            JOptionPane.showMessageDialog(vP, "Usted es un productor");
            vPro = new Vista_Productor(){
                public void dipose(){
                    vP.setVisible(true);
                 }
            };
            cargarDatosMezcal();
            vPro.iniciar();
        }else{
            JOptionPane.showMessageDialog(vP, "Usted es un consumidor");
            vCon = new Vista_Consumidor();
            cargarDatosBotella();
            vCon.iniciar();
        }
    }
    
    public void cargarDatosBotella(){
        vCon.mtb.setDatos(m.conexionConsultaBotellas("select * from mezcal.botella"));
    }
    
    public void cargarDatosMezcal(){
        vPro.mtm.setDatos(m.conexionConsultaMezcales("select * from mezcal.mezcal"));
    }
}
