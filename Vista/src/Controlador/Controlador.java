/*
 * Clase Controlador
 */

package Controlador;

import Modelo.Conexion;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String o = ae.getActionCommand();
        switch(o){
            case "1":
                System.out.println("Entramos al boton1");
                
        
        }
    }
    
    
}
