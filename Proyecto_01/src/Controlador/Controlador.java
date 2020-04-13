/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Conexion;
import Vista.Vista_Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        String accion = ae.getActionCommand();
        switch(accion){
            case "siguiente":
                
                break;
            case "atras":
                break;
                case "registrar":
                    break;
                case "limpiar":
                    break;
                case "cancelar":
                    break;
                    
        }
    }

}
