/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MVC_Principal;

import Procesos.BufferBarriles;
import Procesos.BufferMezcalDestilado;
import Procesos.BufferPiniasCortadas;
import Procesos.BufferPiniasFermentadas;
import Procesos.BufferPiniasHorneadas;
import Procesos.BufferPiniasMolidas;
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
public class Main2 {
    
    public static void main(String[] args) throws InterruptedException {
        new Prueba().start();
        
        
        /*
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.submit(c1);
        ex.submit(c2);
        ex.submit(c3);
        ex.submit(h1);
        ex.submit(m1);
        ex.submit(m2);
        ex.submit(m3);
        ex.awaitTermination(1, TimeUnit.MINUTES);
        if(!ex.isTerminated()){
            System.out.println(bft.isEmpty());
            System.out.println(bpc.isEmpty());
            System.out.println(bph.isEmpty());
            System.out.println(bph.isEmpty());
            System.out.println(t1.getPinias().toString());
            System.out.println(t2.getPinias().toString());
            
        }*/
}}
    
    class Prueba extends Thread{
        public void run(){
        BufferTandas bft = new BufferTandas();
        BufferPiniasCortadas bpc = new BufferPiniasCortadas();
        BufferPiniasHorneadas bph = new BufferPiniasHorneadas();
        BufferPiniasMolidas bpm = new BufferPiniasMolidas();
        BufferPiniasFermentadas bpf = new BufferPiniasFermentadas();
        BufferMezcalDestilado bmd = new BufferMezcalDestilado();
        BufferBarriles bb = new BufferBarriles();
        
        Tanda t1 = new Tanda('T',0.55,"Añejo", 4);
        Tanda t2 = new Tanda('S',0.45,"Blanco", 2);
        bft.put(t1);
        bft.put(t2);
        /**
         * Los cortes tienen el buffer de tamdas del que consumen
         * y tambien tienen el buffer de pinias cortadas de las que producen
         */
        Corte c1 = new Corte(1, bft,bpc);
        Corte c2 = new Corte(2, bft,bpc);
        Corte c3 = new Corte(3, bft,bpc);
        
        Horno h1 = new Horno(1, bpc,bph);
        Horno h2 = new Horno(2, bpc,bph);
        Horno h3 = new Horno(3, bpc,bph);
        
        Molino m = new Molino(1, bph, bpm);
        Molino m2 = new Molino(2, bph, bpm);
        
        Fermentado f = new Fermentado(1, bpm, bpf);
        
        Destilador d = new Destilador(1, bpf, bmd);
        
        Enbotelladora e = new Enbotelladora(1, bmd, bb);
        
        c1.start(); c2.start(); c3.start();
        h1.start(); h2.start(); h3.start();
        m.start();  m2.start();
        f.start();
        d.start();
        e.start();
        }
    }

