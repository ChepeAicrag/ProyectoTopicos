/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MVC_Principal;

import Procesos.BufferTandas;
import Procesos.Corte;
import Procesos.Productor;
import Procesos.Tanda;

/**
 * 
 * @author García García José Ángel
 */
public class Main {
    public static void main(String[] args) {
        BufferTandas bufferTandas = new BufferTandas();
        
        bufferTandas.put(new Tanda('T',0.55,"Añejo", 4));
        
        Corte corte = new Corte(1, bufferTandas);
        
        corte.start();
        
        Productor p = new Corte(0, bufferTandas);
    }
}
