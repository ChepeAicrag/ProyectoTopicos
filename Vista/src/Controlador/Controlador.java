/*
 * Clase Controlador
 */

package Controlador;

import Modelo.Conexion;
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
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    BufferTandas bft = new BufferTandas();
    BufferPiniasCortadas bpc = new BufferPiniasCortadas();
    BufferPiniasHorneadas bph = new BufferPiniasHorneadas();
    BufferPiniasMolidas bpm = new BufferPiniasMolidas();
    BufferPiniasFermentadas bpf = new BufferPiniasFermentadas();
    BufferMezcalDestilado bmd = new BufferMezcalDestilado();
    BufferBarriles bb = new BufferBarriles();
    Corte c1         = new Corte(1, bft, bpc);
    Corte c2         = new Corte(2, bft, bpc);
    Corte c3         = new Corte(3, bft, bpc);
    Horno h1         = new Horno(1, bpc, bph);
    Horno h2         = new Horno(2, bpc, bph);
    Horno h3         = new Horno(3, bpc, bph);
    Molino m1        = new Molino(1, bph, bpm);
    Molino m2        = new Molino(2, bph, bpm);
    Molino m3        = new Molino(3, bph, bpm);
    Fermentado f1    = new Fermentado(1, bpm, bpf);
    Fermentado f2    = new Fermentado(2, bpm, bpf);
    Fermentado f3    = new Fermentado(3, bpm, bpf);
    Destilador d1    = new Destilador(1, bpf, bmd);
    Destilador d2    = new Destilador(2, bpf, bmd);
    Destilador d3    = new Destilador(3, bpf, bmd);
    Enbotelladora e1 = new Enbotelladora(1, bmd, bb);
    Enbotelladora e2 = new Enbotelladora(2, bmd, bb);
    Enbotelladora e3 = new Enbotelladora(3, bmd, bb);
    private int cantPinas = 0, // Cantidad de piñas a usar
                id_Maguey = 0, // id maguey seleccionado
                limite = 0;
    private char tipoMaguey;
    private String tipoMezcal; // Tipo de alcohol
    private double porcentajeAlcohol;
    private String bandera;
    
    
    public Controlador(Vista v, Conexion m){
        this.v = v;
        this.m = m;
        //actualizarOpciones();
        IniciarEquipos();
    }
    
    private void actualizarOpciones(){
        ArrayList<String> porcentajes = m.conexionConsultarPorcentajeOTipo("select * from mezcalera.GradoAlcohol");
        ArrayList<String> tipos = m.conexionConsultarPorcentajeOTipo("select * from mezcalera.TipoMezcal");
        v.llenarOpciones(porcentajes, tipos);
    }
    
    private void datosTanda(){
        // Hacer la consulta para el tipo de maguey desde el id
        // Por el momento estará así
        if (bandera.equals("1")) {
            tipoMaguey = 'T'; // T detobala
        }else if(bandera.equals("2")){
            tipoMaguey = 'S'; // S spadin
        }
        String valorPorcentaje = (String) v.ventana1.alcohol.getSelectedItem();
        porcentajeAlcohol = Double.parseDouble(valorPorcentaje.substring(1, valorPorcentaje.length()));
        tipoMezcal = (String) v.ventana1.tipo.getSelectedItem();
        
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String o = ae.getActionCommand();
        if(o.equals("1") || o.equals("2") || o.equals("3") || o.equals("4") || 
           o.equals("5") || o.equals("6") || o.equals("7") || o.equals("8")){
            System.out.println("Selecciono el btn " + o);
            /**
             * La BD ayudará a obtener la información de Mageuy
             * Nombre 
             */
            bandera = o;
            id_Maguey = Integer.parseInt(o);
            //limite = m.consultarPinas(id_Maguey);
              cantPinas = cantidadPinas(1000);
        }
        switch(o){
            case "producir": //Instrucción del boton producir
                System.out.println("Produciendo");
                
                if(id_Maguey > 0 && cantPinas > 0){
                    String sql = "update mezcalera.maguey set cantidadPinas=" 
                        + (limite - cantPinas) + " where id_Maguey=" + id_Maguey;
                    JOptionPane.showMessageDialog(v, sql);
                     //m.actualizarDato(sql);
                     datosTanda(); // Actauliza las variables para tanda
                     Tanda t = new Tanda(tipoMaguey,porcentajeAlcohol, tipoMezcal, cantPinas);
                     System.out.println(t);
                     bft.put(t);
                     v.principal.setSelectedIndex(1);
                     
               }else{
                    JOptionPane.showMessageDialog(v,"No rellenó correctamente");
                }
                break;
                
        }
    }
   
    /**
     * Prepara todos los equipos 
     * Deben estar listos desde el inicio
     */
    
    private ExecutorService ejecutador = Executors.newCachedThreadPool();
    
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
        
        /** Asignar barras a cada proceso */
        eb3 = v.ventana2.barra3.getPos(5).getBarra();
        c1.setBarra(cb1); c2.setBarra(cb2); c3.setBarra(cb3);
        h1.setBarra(hb1); h2.setBarra(hb2); h3.setBarra(hb3);
        m1.setBarra(mb1); m2.setBarra(mb2); m3.setBarra(mb3);
        f1.setBarra(fb1); f2.setBarra(fb2); f3.setBarra(fb3);
        d1.setBarra(db1); d2.setBarra(db2); d3.setBarra(db3);
        e1.setBarra(eb1); e2.setBarra(eb2); e3.setBarra(eb3);
        
        /** Iniciar hilos    
        
        c1.start(); c2.start(); c3.start();
        h1.start(); h2.start(); h3.start();
        m1.start(); m2.start(); m3.start();
        f1.start(); f2.start(); f3.start();
        d1.start(); d2.start(); d3.start();
        e1.start(); e2.start(); e3.start();
        * */
        ejecutador.submit(c1); ejecutador.submit(c2); ejecutador.submit(c3);
        ejecutador.submit(h1); ejecutador.submit(h2); ejecutador.submit(h3);
        ejecutador.submit(m1); ejecutador.submit(m2); ejecutador.submit(m3);
        ejecutador.submit(f1); ejecutador.submit(f2); ejecutador.submit(f3);
        ejecutador.submit(d1); ejecutador.submit(d2); ejecutador.submit(d3);
        ejecutador.submit(e1); ejecutador.submit(e2); ejecutador.submit(e3);
        
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
                if(msj != null){
                
                if (msj.isEmpty())
                    msj = "0";
                cantidad = Integer.parseInt(msj);
                if(cantidad > limite)
                    new Exception("Limite pasado");
                else 
                    op = true;
                }
                op = true;
            }catch(NumberFormatException e){
                System.out.println(e.toString());
            }
        }
        return cantidad;
    }
}
