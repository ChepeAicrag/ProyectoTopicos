/*
 * Clase Controlador
 */

package Controlador;

import Modelo.ManejoDatos;
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
    
    private int contadorTandas;// (Mejor usar serial)
    public ManejoDatos m;
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
    private ExecutorService ejecutador = Executors.newCachedThreadPool();
    
    public Controlador(Vista v, ManejoDatos m){
        this.v = v;
        this.m = m;
        IniciarEquipos();
        actualizarOpciones();
        new MiHilo().start();
        cargarDatosTandas();
        contadorTandas = v.vRegistro.mtt.getRowCount();
    }
    
    private void actualizarOpciones(){
        ArrayList<String> porcentajes = m.conexionConsultarPorcentajeOTipo("select * from mezcal.gradoalcohol");
        ArrayList<String> tipos = m.conexionConsultarPorcentajeOTipo("select * from mezcal.tipomezcal");
        ArrayList<String> mezcales = m.conexionConsultarPorcentajeOTipo("select * from mezcal.maguey");
        v.llenarOpciones(mezcales, porcentajes, tipos);
    }
            
    
    int filaPulsada = 0, id_tanda = 0, limite = 0,id_Maguey = 0, id_alcohol = 0, id_tipoMezcal = 0, cantPinias = 0;
    ArrayList<Integer> tandasProduciendo = new ArrayList();
    @Override
    public void actionPerformed(ActionEvent ae) {
        Tanda t;
        Object datos[] = null;
        String o = ae.getActionCommand();
        if(o.length() == 1){
            datos = m.selectMaguey(Integer.parseInt(o));
            System.out.println("Seleccionó el maguey " + (String) datos[1]);
            id_Maguey = (int) datos[0];
            limite = (int) datos[2];
            System.out.println(limite);
            cantPinias = cantidadPinas(limite);
        }
        switch(o){
            case "registrar":
                if(id_Maguey > 0 && cantPinias > 0){
                     id_tipoMezcal = v.vProducir.tipo.getSelectedIndex() + 1;
                     id_alcohol = v.vProducir.alcohol.getSelectedIndex() + 1;
                     t = new Tanda(id_Maguey,id_alcohol,id_tipoMezcal, cantPinias);
                     t.setId(++contadorTandas);
                     System.out.println(t);
                     m.insertTanda(t); // Insertamos la tanda al registro de la tabla (OJO)
                     v.principal.setSelectedIndex(1);
                     cargarDatosTandas();
                     v.vRegistro.tabla.updateUI();
                     v.vRegistro.tabla.revalidate();
               }else{
                    JOptionPane.showMessageDialog(v,"No rellenó correctamente");
                }
                break;
            
            /** Elimina el registro de la tabla de tandas*/    
            case "eliminar":
                filaPulsada = v.vRegistro.tabla.getSelectedRow();
                if (filaPulsada >= 0) {
                    t = new Tanda(); // Solo para eliminar
                    id_tanda = Integer.parseInt((String) v.vRegistro.mtt.getValueAt(filaPulsada, 0));
                    t.setId(id_tanda);
                    if(!tandasProduciendo.contains(id_tanda)){
                        m.deleteTanda(t);
                        cargarDatosTandas();
                        v.vRegistro.tabla.updateUI();
                    }else{
                        JOptionPane.showMessageDialog(v, "No lo puede eliminar, está en produccion");
                    }
                }
                break;
            case "producir":
                filaPulsada = v.vRegistro.tabla.getSelectedRow();      
                if (filaPulsada >= 0) {
                    id_tanda = Integer.parseInt((String) v.vRegistro.mtt.getValueAt(filaPulsada, 0));
                    t = new Tanda();
                    t.setId(id_tanda);
                    Tanda tandaProducir = m.selectTanda(t);
                    if(!tandasProduciendo.contains(t.getId())){
                        tandasProduciendo.add(t.getId());
                        m.updatePinias(tandaProducir);
                        bft.put(tandaProducir); // Lo colocamos en la tanda
                        tandaProducir.setEstado("Produciendo");
                        m.updateEstadoTanda(tandaProducir);
                        cargarDatosTandas();
                        //v.principal.setSelectedIndex(2);
                    }else{
                        JOptionPane.showMessageDialog(v, "Ya está en proceso esa tanda");
                    }
                }
                break;
        }
        
    }
       
    public void cargarDatosTandas(){
        String consultaTandas = "select * from mezcal.tanda";
        v.vRegistro.mtt.setDatos(m.conexionConsultaTanda(consultaTandas));
        v.vRegistro.tabla.updateUI();
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
    
    /**
     * Prepara todos los equipos 
     * Deben estar listos desde el inicio
     */
    public void IniciarEquipos(){
        /** Aquí creamos el proceso necesario*/
        JProgressBar 
            cb1 = v.vProduccion.barra1.getPos(0).getBarra(),
            cb2 = v.vProduccion.barra2.getPos(0).getBarra(),
            cb3 = v.vProduccion.barra3.getPos(0).getBarra(),
                hb1 = v.vProduccion.barra1.getPos(1).getBarra(),
                hb2 = v.vProduccion.barra2.getPos(1).getBarra(),
                hb3 = v.vProduccion.barra3.getPos(1).getBarra(),
                     mb1 = v.vProduccion.barra1.getPos(2).getBarra(),
                     mb2 = v.vProduccion.barra2.getPos(2).getBarra(),
                     mb3 = v.vProduccion.barra3.getPos(2).getBarra(),
                          fb1 = v.vProduccion.barra1.getPos(3).getBarra(),
                          fb2 = v.vProduccion.barra2.getPos(3).getBarra(),
                          fb3 = v.vProduccion.barra3.getPos(3).getBarra(),
                               db1 = v.vProduccion.barra1.getPos(4).getBarra(),
                               db2 = v.vProduccion.barra2.getPos(4).getBarra(),
                               db3 = v.vProduccion.barra3.getPos(4).getBarra(),
                                    eb1 = v.vProduccion.barra1.getPos(5).getBarra(),
                                    eb2 = v.vProduccion.barra2.getPos(5).getBarra(),
                                    eb3 = v.vProduccion.barra3.getPos(5).getBarra();
        /** Asignar barras a cada proceso */
        /*
        c1.conectarControlador(this); c2.conectarControlador(this); c3.conectarControlador(this);
        h1.conectarControlador(this); h2.conectarControlador(this); h3.conectarControlador(this);
        m1.conectarControlador(this); m2.conectarControlador(this); m3.conectarControlador(this);
        f1.conectarControlador(this); f2.conectarControlador(this); f3.conectarControlador(this);
        d1.conectarControlador(this); d2.conectarControlador(this); d3.conectarControlador(this);
        e1.conectarControlador(this); e2.conectarControlador(this); e3.conectarControlador(this);
        */
        c1.setBarra(cb1); c2.setBarra(cb2); c3.setBarra(cb3);
        c1.setIdentificador(v.vProduccion.barra1.getPos(0));
        c2.setIdentificador(v.vProduccion.barra1.getPos(1));
        c3.setIdentificador(v.vProduccion.barra1.getPos(2));
        
        h1.setBarra(hb1); h2.setBarra(hb2); h3.setBarra(hb3);
        m1.setBarra(mb1); m2.setBarra(mb2); m3.setBarra(mb3);
        f1.setBarra(fb1); f2.setBarra(fb2); f3.setBarra(fb3);
        d1.setBarra(db1); d2.setBarra(db2); d3.setBarra(db3);
        e1.setBarra(eb1); e2.setBarra(eb2); e3.setBarra(eb3);
        ejecutador.submit(c1); ejecutador.submit(c2); ejecutador.submit(c3);
        ejecutador.submit(h1); ejecutador.submit(h2); ejecutador.submit(h3);
        ejecutador.submit(m1); ejecutador.submit(m2); ejecutador.submit(m3);
        ejecutador.submit(f1); ejecutador.submit(f2); ejecutador.submit(f3);
        ejecutador.submit(d1); ejecutador.submit(d2); ejecutador.submit(d3);
        ejecutador.submit(e1); ejecutador.submit(e2); ejecutador.submit(e3);
    }
    
    private void prepararEquipos(){
        
    }
    
    /** Clase Hilo que permite hacer la actualización en tiempo real de la tabla */
    class MiHilo extends Thread{
    
        public MiHilo(){
        
        }
        
        @Override
        public void run(){
            while (true) {                
                Corte c = corteActualizar();
                Horno h = hornoActualizar();
                if(c != null){
                    m.updateEstadoTanda(c.getTanda());
                    System.out.println("actulizar tabla");
                    }
                if(h != null){
                    m.updateEstadoTanda(h.getTanda());
                    System.out.println("Tabla de horno");
                }
                if(c != null || h != null){
                    cargarDatosTandas();
                    v.vRegistro.tabla.updateUI();
                }
            }
        }
        
        public Corte corteActualizar(){
            Corte c = null;
            if (c1.update()) {
                c = c1;
            }else if (c2.update()) {
                c = c2;
            }else if (c3.update()) {
                c = c3;
            }
            return c;
        }
        
        public Horno hornoActualizar(){
            Horno h = null;
            if (h1.update()) {
                h = h1;
            }else if (h2.update()) {
                h = h2;
            }else if (h3.update()) {
                h = h3;
            }
            return h;
        }
    }
}