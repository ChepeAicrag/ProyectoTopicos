/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Procesos.BufferTandas;
import Procesos.Tanda;
import java.util.ArrayList;

/**
 * 
 * @author García García José Ángel
 */
public class Controlador_Hilos<T> extends Thread{

    private ArrayList<T> cortadores, horneados, molinos, fermentadores,
                              destiladores, enbotelladores;
    private BufferTandas bft;
    
    public Controlador_Hilos(ArrayList<T> ... s){
        cortadores = s[0];
        horneados = s[1];
        molinos = s[2];
        fermentadores = s[3];
        destiladores = s[4];
        enbotelladores = s[5];
        this.bft = bft;
    }
    
    public void run(){
        for(;;){
            actualizar();
            }
    }
    
    public void setBuffer(BufferTandas bft){
        this.bft = bft;
    }
    
    public void actualizar(){
        Tanda t = bft.remove();
        
    }
}
