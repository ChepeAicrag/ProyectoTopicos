/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Conexion;
import Modelo.Persona;
import Vista.Vista_Login;
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
public class Controlador implements ActionListener{

    private Vista_Login vP;
    private Vista_Registro v;
    private Conexion m;
    
    public Controlador(Conexion m, Vista_Login vistaLogin){
        this.vP = vistaLogin;
        this.m = m;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Persona p;
        String accion = ae.getActionCommand();
        switch(accion){
            case "registrar_Login":
                v = new Vista_Registro(){
                    //Con esto cuando llamemos a dispose de nuevaVentana abrimos la principal
                    @Override
                    public void dispose() {
                     
                            //Hacemos visible la principal
                            vP.setVisible(true);
                            //Cerramos nuevaVentana
                            vP.dispose();
                       
                    }
                };
                v.conectarControlador(this);
                //Hacemos visible a nuevaVentana
                v.setVisible(true);
                //Cerramos la principal
                vP.dispose();
                /*try{
                    System.out.println(vP.getHilo());
                    Thread.currentThread().wait();
                }catch(InterruptedException e){}
                */
                break;
            case "ingresar":
                JOptionPane.showMessageDialog(vP, "Entrando al sistema");
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
                        vP.setVisible(true);
                       // v.notifyAll();
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
                    if(op == JOptionPane.YES_OPTION){
            try {
                // Aqui debemos mejorar esto
                v.dispose();
                v.setVisible(false);
                vP = new Vista_Login();
                Thread.sleep(1000);
                vP.conectarControlador(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
                    break;
                    
        }
    }

}
