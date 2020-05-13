/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MVC_Principal;

import Procesos.BufferBarriles;
import Procesos.BufferMezcalDestilado;
import Procesos.BufferPiniasFermentadas;
import Procesos.BufferPiniasMolidas;
import Procesos.BufferPiniasHorneadas;
import Procesos.BufferTandas;
import Procesos.Corte;
import Procesos.Destilador;
import Procesos.Enbotelladora;
import Procesos.Fermentado;
import Procesos.Horno;
import Procesos.Molino;
import Procesos.Tanda;

/**
 * 
 * @author García García José Ángel
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BufferTandas bufferTandas = new BufferTandas();
        
        Tanda tanda = new Tanda('T',0.55,"Añejo", 4);
        
        bufferTandas.put(tanda);
        
        Corte corte = new Corte(1, bufferTandas);
        
        corte.start();
        corte.join();
        
        System.out.println(tanda.getPinias().toString());
        
        BufferPiniasMolidas bufferPiniasCortadas = new BufferPiniasMolidas();
        
        bufferPiniasCortadas.put(tanda);
        
        BufferPiniasHorneadas bufferPiniasHorneadas = new BufferPiniasHorneadas();
        
        Horno h1 = new Horno(1, bufferPiniasCortadas, bufferPiniasHorneadas);
        
        h1.start();
        h1.join();
        
        System.out.println(tanda.getPinias().toString());
        
        BufferPiniasMolidas bufferPiniasMolidas = new BufferPiniasMolidas();
        
        Molino m1 = new Molino(1, bufferPiniasHorneadas, bufferPiniasMolidas);
        
        m1.start();
        m1.join();
        
        System.out.println(tanda.getPinias().toString());
        
        BufferPiniasFermentadas bufferPiniasFermentadas = new BufferPiniasFermentadas();
        
        Fermentado f1 = new Fermentado(1, bufferPiniasMolidas, bufferPiniasFermentadas);
        
        f1.start();
        f1.join();
        
        System.out.println(tanda.getPinias().toString());
    
        BufferMezcalDestilado bufferMezcalDestilado = new BufferMezcalDestilado();
        
        Destilador d1 = new Destilador(1, bufferPiniasFermentadas, bufferMezcalDestilado);
       
        d1.start();
        d1.join();
        
        System.out.println(tanda.getPinias().toString());
        
        BufferBarriles bufferBarriles = new BufferBarriles();
        
        Enbotelladora en1 = new Enbotelladora(1, bufferMezcalDestilado, bufferBarriles);
       
        en1.start();
        en1.join();
        
        System.out.println(tanda.getPinias().toString());
    }
}
