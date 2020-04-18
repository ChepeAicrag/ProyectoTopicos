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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author García García José Ángel
 */
public class Controlador implements ActionListener, KeyListener {

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
                if (u != null) {
                    JOptionPane.showMessageDialog(vP, "Entrando al sistema " + u.getUsuario());
                    vP.txtContraseña.setText("");
                    vP.txtUsuario.setText("");
                    vP.dispose();
                    accionDadoPermiso(u.getPermiso());
                } else {
                    JOptionPane.showMessageDialog(vP, "Acceso denegado");
                }
                break;
            case "nombre":
                v.validaNombre = false;
                if (!v.txtNombre.getText().isEmpty()) {
                    v.validaNombre = true;
                    v.txtCurp.requestFocus();
                } else {
                    v.txtNombre.requestFocus();
                }
                break;
            case "curp":
                v.validaCurp = false;
                if (!v.txtCurp.getText().isEmpty()) {
                    v.validaCurp = true;
                    v.txtEdad.requestFocus();
                } else {
                    v.txtCurp.requestFocus();
                }
                break;
            case "edad":
                v.validaEdad = false;
                int valEdad = 0;
                if (!v.txtEdad.getText().isEmpty()) {
                    try {
                        valEdad = Integer.parseInt(v.txtEdad.getText());
                    } catch (NumberFormatException e) {
                    }
                    if (valEdad > 0 && valEdad < 120) {
                        v.validaEdad = true;
                        v.mujer.requestFocus();
                    } else {
                        v.txtEdad.setText("");
                        v.txtEdad.requestFocus();
                    }
                }
                break;
            case "hombre":
                v.validaHombre = false;
                if (v.hombre.isSelected()) {
                    v.validaHombre = true;
                    v.mujer.setSelected(false);
                } else {
                    v.hombre.doClick();
                }
                break;
            case "mujer":
                v.validaMujer = false;
                if (v.mujer.isSelected()) {
                    v.validaMujer = true;
                    v.hombre.setSelected(false);
                } else {
                    v.mujer.doClick();
                }
                break;
            case "usuario":
                v.validaUsuario = false;
                if (!v.txtUsuario.getText().isEmpty()) {
                    v.validaUsuario = true;
                    v.txtCorreo.requestFocus();
                } else {
                    v.txtUsuario.requestFocus();
                }
                break;
            case "email":
                v.validaEmail = false;
                if (!v.txtCorreo.getText().isEmpty()) {
                    if (v.txtCorreo.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                        /*
                        ^ especifica el inicio de la entrada.
                        ([_a-z0-9-]) primer grupo. Se refiere a la aparición de uno o más caracteres compuestos por guión bajo, letras, números y guiones.
                        (\\.[_a-z0-9-]) segundo grupo. Puede ser opcional y repetible, se refiere a la aparición de un punto seguido de uno o más caracteres compuestos por guión bajo, letras, números y guiones.
                        *@ carácter arroba.
                        ([a-z0-9-]) tercer grupo. Especifica la aparición de uno o más caracteres compuestos por letras, números y guiones.
                        (\\.[a-z0-9-]) cuarto grupo. Especifica un punto seguido de uno o más caracteres compuestos por letras, números y guiones.
                        (\\.[a-z]{2,4}) quinto grupo. Especifica un punto seguido de entre 2 y 4 letras, con el fin de considerar dominios terminados, por ejemplo, en .co y .info.
                        $ especifica el fin de la entrada.
                         */
                        v.validaEmail = true;
                        v.txtContraseña.requestFocus();
                    } else {
                        v.txtCorreo.requestFocus();
                    }
                }
                break;
            case "contraseña":
                v.validaContraseña = false;
                if (!v.txtContraseña.getText().isEmpty()) {
                    v.validaContraseña = true;
                    v.productor.requestFocus();
                } else {
                    v.txtContraseña.requestFocus();
                }
                break;
            case "op_cliente":
                v.validaCliente = false;
                if (v.cliente.isSelected()) {
                    v.validaCliente = true;
                    v.validaProductor = false;
                    v.productor.setSelected(false);
                    v.txtTelefono.setVisible(true);
                    v.etqTelefono.setVisible(true);
                    v.txtRfc.setVisible(true);
                    v.etqRfc.setVisible(true);
                } else {
                    v.cliente.doClick();
                }
                break;
            case "op_productor":
                v.validaProductor = false;
                if (v.productor.isSelected()) {
                    v.validaProductor = true;
                    v.validaCliente = false;
                    v.cliente.setSelected(false);
                    v.txtTelefono.setVisible(true);
                    v.etqTelefono.setVisible(true);
                    v.txtRfc.setVisible(false);
                    v.etqRfc.setVisible(false);
                } else {
                    v.productor.doClick();
                }
                break;
            case "telefono":
                v.validaTelefono = false;
                if (v.txtTelefono.getText().length() < 11) {
                    v.validaTelefono = true;
                    if (v.validaCliente) {
                        v.txtRfc.requestFocus();
                    } else {
                        v.registrar.requestFocus();
                    }
                } else {
                    v.txtTelefono.setText("");
                    v.txtTelefono.requestFocus();
                }
                break;
            case "rfc":
                v.validadRfc = false;
                if (!v.txtRfc.getText().isEmpty()) {
                    v.validadRfc = true;
                    v.registrar.requestFocus();
                } else {
                    v.txtRfc.requestFocus();
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
                if (v.validaNombre && v.validaCurp && v.validaEdad && (v.validaHombre || v.validaMujer)
                        && v.validaUsuario && v.validaEmail && v.validaContraseña
                        && (v.validaProductor || v.validaCliente) && v.validaTelefono) {
                    String sexo = "M";
                    if (v.hombre.isSelected()) {
                        sexo = "H";
                    }
                    if ((v.validadRfc && v.validaCliente) || (!v.validaCliente && !v.validadRfc)) {
                        JOptionPane.showMessageDialog(v, "correcto");
                        /*
                        p = new Persona(v.txtCurp.getText(), v.txtNombre.getText(), sexo, Integer.parseInt(v.txtEdad.getText()));
                        m.insertPersona(p);
                        String per = "P";
                        if (v.cliente.isSelected()) {
                              per = "C";
                        }
                        String SqlUser = "insert into mezcal.usuario(usuario,password,correo,permiso,curp) values('"
                            + v.txtUsuario.getText() + "','" + v.txtContraseña.getText() + "','"
                            + v.txtCorreo.getText() + "','" + per + "','" + p.getCurp() + "')";
                        m.actualizarDato(SqlUser);
                        if(per.equals("C")){
                            String SqlCliente = "insert into mezcal.cliente (curp,rfc,telefono) values('" + 
                            p.getCurp() + "','" + v.txtRfc.getText() + "','" + v.txtTelefono.getText() + "')";
                            m.actualizarDato(SqlCliente);
                        }else{
                            String SqlProductor = "insert into mezcal.empleado (curp,telefono) values('" + 
                            p.getCurp() + "','" + v.txtTelefono.getText() + "')";
                            m.actualizarDato(SqlProductor);
                        }
                        JOptionPane.showMessageDialog(v, "Se ha registrado correctamente\n Se regresará al Loging");
                         */
                        v.dispose();
                        limpiarLoginYRegistrarse();
                    } else {
                        JOptionPane.showMessageDialog(v, "Introduce el Rfc");
                    }

                } else {
                    String msj = "Rellena los campos correctamente";
                    if (!v.validaNombre) {
                        msj += "\n*El campo nombre está vacio";
                    }
                    if (!v.validaCurp) {
                        msj += "\n*El campo curp no es correcto";
                    }
                    if (!v.validaEdad) {
                        msj += "\n*El campo edad no es correcto ";
                    }
                    if (!v.validaHombre && !v.validaMujer) {
                        msj += "\n*Elija sexo";
                    }
                    if (!v.validaUsuario) {
                        msj += "\n*El campo usuario está vacio";
                    }
                    if (!v.validaEmail) {
                        msj += "\n*El campo correo está vacio";
                    }
                    if (!v.validaContraseña) {
                        msj += "\n*El campo contraseña no está relleno";
                    }
                    if (!v.validaProductor && !v.validaCliente) {
                        msj += "\n*Elija rol";
                    }
                    if (!v.validaTelefono && (v.validaProductor || v.validaCliente)) {
                        msj += "\n*El campo telefono está vacio";
                    }
                    if (!v.validadRfc && v.validaCliente) {
                        msj += "\n*El campo rfc está vacio";
                    }

                    JOptionPane.showMessageDialog(v, msj);
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
                v.cliente.setSelected(false);
                v.txtTelefono.setVisible(false);
                v.etqTelefono.setVisible(false);
                v.txtRfc.setVisible(false);
                v.etqRfc.setVisible(false);
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

    public void accionDadoPermiso(String permiso) {
        if (permiso.equals("P")) {
            JOptionPane.showMessageDialog(vP, "Usted es un productor");
            vPro = new Vista_Productor() {
                public void dipose() {
                    vP.setVisible(true);
                }
            };
            cargarDatosMezcal();
            vPro.iniciar();
        } else {
            JOptionPane.showMessageDialog(vP, "Usted es un consumidor");
            vCon = new Vista_Consumidor();
            cargarDatosBotella();
            vCon.iniciar();
        }
    }

    public void cargarDatosBotella() {
        vCon.mtb.setDatos(m.conexionConsultaBotellas("select * from mezcal.botella"));
    }

    public void cargarDatosMezcal() {
        vPro.mtm.setDatos(m.conexionConsultaMezcales("select * from mezcal.mezcal"));
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char k = ke.getKeyChar();
        JTextField productor = (JTextField) ke.getSource();
        if (productor == v.txtNombre) {
            if (!Character.isAlphabetic(k) && k != KeyEvent.VK_SPACE) {
                ke.consume();
            }
        }
        if (productor == v.txtEdad || productor == v.txtTelefono) {
            if (!Character.isDigit(k)) {
                ke.consume();
            }
        }
        if (productor == v.txtRfc) {
            if (!Character.isDigit(k) && !Character.isAlphabetic(k)) {
                ke.consume();
            }
        }

        if (productor == v.txtCurp) {
            if (!Character.isUpperCase(k)) {
                ke.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
