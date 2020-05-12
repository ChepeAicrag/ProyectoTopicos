/*
 * Clase Controlador
 */

package Controlador;

import Modelo.Conexion;
import Procesos.Consumidor;
import Procesos.Corte;
import Procesos.DespachadorPalenque;
import Procesos.Monitor;
import Procesos.Productor;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 * 
 * @author García García José Ángel
 */
public class Controlador implements ActionListener{
 
    private Vista v;
    private Conexion m;
    private Monitor monitor = new Monitor();
    private Productor corte1, corte2, corte3, horno1,
              horno2, horno3, molin1, molin2,
              molin3, ferme1, ferme2, ferme3,
              desti1, desti2,desti3, enbot1,
              enbot2, enbot3;
    
    public Controlador(Vista v, Conexion m){
        this.v = v;
        this.m = m;
        actualizarOpciones();
        IniciarEquipos();
    }
    
    private void actualizarOpciones(){
        ArrayList<String> porcentajes = m.conexionConsultarPorcentajeOTipo("select * from mezcalera.GradoAlcohol");
        ArrayList<String> tipos = m.conexionConsultarPorcentajeOTipo("select * from mezcalera.TipoMezcal");
        v.llenarOpciones(porcentajes, tipos);
    }
    private int cantPinas = 0, // Cantidad de piñas a usar
            id_Maguey = 0, // id maguey seleccionado
            limite = 0;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String o = ae.getActionCommand();
        if(o.equals("1") || o.equals("2") || o.equals("3") || o.equals("4") || 
           o.equals("5") || o.equals("6") || o.equals("7") || o.equals("8")){
            System.out.println("Selecciono el btn " + o);
            id_Maguey = Integer.parseInt(o);
            //limite = m.consultarPinas(id_Maguey);
            //cantPinas = cantidadPinas(limite);
        }
        switch(o){
            
            case "producir": //Instrucción del boton producir
                System.out.println("Produciendo");
                if(id_Maguey > 0 && cantPinas > 0){
                    String sql = "update mezcalera.maguey set cantidadPinas=" 
                        + (limite - cantPinas) + " where id_Maguey=" + id_Maguey;
                    JOptionPane.showMessageDialog(v, sql);
                     //m.actualizarDato(sql);
                    v.principal.setSelectedIndex(1);
               }else{
                    JOptionPane.showMessageDialog(v,"No rellenó correctamente");
                }
                //int a = Integer.parseInt(JOptionPane.showInputDialog(v, "Introduce el proceso"));
                //producir((int)Math.random() * 5);
                if(!corte1.getEstado() || !corte2.getEstado() || !corte3.getEstado())
                    iniciarTanda(3000); // Los 3 primeros deben estar disponible
                else
                    JOptionPane.showMessageDialog(v, "No hay espacio para empezar a producir");
                break;
                
        }
    }
    private ArrayList<JProgressBar> Arraybarras;
    private DespachadorPalenque despa;
    private int contTandas;
    
    public void iniciarTanda(int limite){
       
        Consumidor c = new Consumidor(limite,despa);
        c.start(); // Empieza a pelear por el recurso
        
    }
    
    
    /**
     * Prepara todos los equipos 
     * Deben estar listos desde el inicio
     */
    
        
    public void IniciarEquipos(){
        /** Aquí creamos el proceso necesario*/
        JProgressBar 
            cb1 = v.ventana2.barra1.getPos(0).getBarra(),
            cb2 = v.ventana2.barra2.getPos(0).getBarra(),
            cb3 = v.ventana2.barra3.getPos(0).getBarra(),
                hb1 = v.ventana2.barra1.getPos(1).getBarra(),
                hb2 = v.ventana2.barra2.getPos(1).getBarra(),
                hb3 = v.ventana2.barra3.getPos(1).getBarra(),
                     mb1 = v.ventana2.barra1.getPos(2).getBarra(),
                     mb2 = v.ventana2.barra2.getPos(2).getBarra(),
                     mb3 = v.ventana2.barra3.getPos(2).getBarra(),
                          fb1 = v.ventana2.barra1.getPos(3).getBarra(),
                          fb2 = v.ventana2.barra2.getPos(3).getBarra(),
                          fb3 = v.ventana2.barra3.getPos(3).getBarra(),
                               db1 = v.ventana2.barra1.getPos(4).getBarra(),
                               db2 = v.ventana2.barra2.getPos(4).getBarra(),
                               db3 = v.ventana2.barra3.getPos(4).getBarra(),
                                    eb1 = v.ventana2.barra1.getPos(5).getBarra(),
                                    eb2 = v.ventana2.barra2.getPos(5).getBarra(),
                                    eb3 = v.ventana2.barra3.getPos(5).getBarra();
        Arraybarras = new ArrayList<>();
        Arraybarras.add(cb1);
        Arraybarras.add(cb2);
        Arraybarras.add(cb3);
        
        despa = new DespachadorPalenque(Arraybarras);
        
        
        
        
        
        
        /** Hilos productores, siempre estarán activos desde iniciar */
        corte1 = new Productor(cb1); // El cortador 1 administra su barra
        corte2 = new Productor(cb2);
        corte3 = new Productor(cb3);
        /*
        horno1 = new Productor(hb1,monitor);
        horno2 = new Productor(hb2,monitor);
        horno3 = new Productor(hb3,monitor);
        molin1 = new Productor(mb1,monitor);
        molin2 = new Productor(mb2,monitor);
        molin3 = new Productor(mb3,monitor);
        ferme1 = new Productor(fb1,monitor);
        ferme2 = new Productor(fb2,monitor);
        ferme3 = new Productor(fb3,monitor);
        desti1 = new Productor(db1,monitor);
        desti2 = new Productor(db2,monitor);
        desti3 = new Productor(db3,monitor);
        enbot1 = new Productor(eb1,monitor);
        enbot2 = new Productor(eb2,monitor);
        enbot3 = new Productor(eb3,monitor);
        */
        //Consumidor c = new Consumidor(3000,m)
        //Consumidor c2 = new Consumidor(3000,m);
        //Consumidor c3 = new Consumidor(3000,m);
        //Consumidor c4 = new Consumidor(3000,m);
        //Consumidor c5 = new Consumidor(3000,m);
        ExecutorService e = Executors.newCachedThreadPool();
        e.submit(corte1);// e.submit(corte2); e.submit(corte3);
        /*
        e.submit(horno1); e.submit(horno2); e.submit(horno3);
        e.submit(molin1); e.submit(molin2); e.submit(molin3);
        e.submit(ferme1); e.submit(ferme2); e.submit(ferme3);
        e.submit(desti1); e.submit(desti2); e.submit(desti3);
        e.submit(enbot1); e.submit(enbot2); e.submit(enbot3);
        */
        //e.submit(c);
        //e.submit(c2);
        //e.submit(c3);
        //e.submit(c4);
        

    }
    
    public void producir(int pos){
        // Siempre son 3 días, por lo que durará 3 * n segundos
        
        JProgressBar pb1 = v.ventana2.barra1.getPos(pos).getBarra(),
                     pb2 = v.ventana2.barra2.getPos(pos).getBarra(),
                     pb3 = v.ventana2.barra3.getPos(pos).getBarra();
        Corte c1 = new Corte(1),c2 = new Corte(2), c3 = new Corte(3);
        c1.setTiempo(3 * 1000);
        c1.setBarra(pb1);
        c2.setTiempo(3 * 1000);
        c2.setBarra(pb2);
        c3.setTiempo(3 * 1000);
        c3.setBarra(pb3);
        c3.start();
        c2.start();
        c1.start();
        
    }
    
    
    /** 
     * Solicita la cantidad de piñas y las valida
     * @param limite Cantidad maxima de piñas a usar
     */
    private int cantidadPinas(int limite){
        int cantidad = 0;
                
        boolean op = false;
        while (!op) {            
            try{
                String msj = JOptionPane.showInputDialog(v, "Introduce la cantidad de piñas a usar");
                if (msj.isEmpty())
                    msj = "0";
                cantidad = Integer.parseInt(msj);
            if(cantidad > limite)
                new Exception("Limite pasado");
            else 
                op = true;
            }catch(NumberFormatException e){
                System.out.println(e.toString());
            }
        }
        return cantidad;
    }
}
