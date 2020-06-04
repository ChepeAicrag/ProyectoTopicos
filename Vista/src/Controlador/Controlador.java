/*
 * Clase Controlador
 */

package Controlador;

import Modelo.ManejoDatos;
import Procesos.BufferTandas;
import Procesos.Corte;
import Procesos.Destilador;
import Procesos.Enbotelladora;
import Procesos.Fermentado;
import Procesos.Horno;
import Procesos.Molino;
import Procesos.Tanda;
import Procesos.Transportista;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 * 
 * @author García García José Ángel
 */
public class Controlador implements ActionListener{
 
    private Vista v;
    
    public ManejoDatos m;
    BufferTandas bft = new BufferTandas(),
                bpc = new BufferTandas(),
                bph = new BufferTandas(),
                bpm = new BufferTandas(),
                bpf = new BufferTandas(),
                bmd = new BufferTandas(),
                bb  = new BufferTandas(),
    TANDAS_TRANSPORTAR = new BufferTandas(),
    TANDAS_ACTUALIZAR = new BufferTandas();
    ArrayList<Corte> cortes = new ArrayList<>();
    ArrayList<Horno> hornos = new ArrayList<>();
    ArrayList<Molino> molinos = new ArrayList<>();
    ArrayList<Fermentado> fermentadores = new ArrayList<>();
    ArrayList<Destilador> destiladores = new ArrayList<>();
    ArrayList<Enbotelladora> enbotelladores = new  ArrayList<>();
    ArrayList<Transportista> transportistas = new ArrayList<>();
    private ExecutorService ejecutador = Executors.newCachedThreadPool();
    
    public Controlador(Vista v, ManejoDatos m){
        this.v = v;
        this.m = m;
        prepararEquipos();
        IniciarEquipos();
        actualizarOpciones();
        cargarDatosTandas();
        cargarInformeTandas();
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
                System.out.println("Holaaaaaaaaaaaaaaa");
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
            case "transportar":
                for (Transportista transportista : transportistas) {
                    transportista.start();
                }
                break;
            case "salir":
                m.cerrarConexion();
                System.exit(0);
                break;
        } 
   }
       
    public void cargarDatosTandas(){
        String consultaTandas = "select * from mezcal.tanda where status = 'Registrada'";
        //String consultaTandas = "select * from mezcal.tanda";
        v.vRegistro.mtt.setDatos(m.conexionConsultaTanda(consultaTandas));
        v.vRegistro.tabla.updateUI();
        v.vRegistro.btnEliminar.repaint();
    }
    
    private void cargarInformeTandas() {
        String consultaTandas = "select * from mezcal.tanda";
        v.vInforme.mti.setDatos(m.conexionConsultaInformeTanda(consultaTandas));
        v.vInforme.tabla.updateUI();
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
        for (int i = 0; i < 3; i++) {
            ejecutador.submit(cortes.get(i));
            ejecutador.submit(hornos.get(i));
            ejecutador.submit(molinos.get(i));
            ejecutador.submit(fermentadores.get(i));
            ejecutador.submit(destiladores.get(i));
            ejecutador.submit(enbotelladores.get(i));
        }
        new MiHilo(TANDAS_ACTUALIZAR).start();
    }
    
    private void prepararEquipos(){
        int id_Equipo = 0;
        for (int i = 0; i < 3; i++) {
            id_Equipo = i + 1;
            cortes.add(new Corte(id_Equipo, bft, bpc));
            hornos.add(new Horno(id_Equipo, bpc, bph));
            molinos.add(new Molino(id_Equipo , bph, bpm));
            fermentadores.add(new Fermentado(id_Equipo, bpm, bpf));
            destiladores.add(new Destilador(id_Equipo, bpf, bmd));
            enbotelladores.add(new Enbotelladora(id_Equipo, bmd, bb));
            transportistas.add(new Transportista(id_Equipo, bb, v.vTraslado));
            cortes.get(i).setIdentificador(v.vProduccion.getBarra(i).getPos(0));
            hornos.get(i).setIdentificador(v.vProduccion.getBarra(i).getPos(1));
            molinos.get(i).setIdentificador(v.vProduccion.getBarra(i).getPos(2));
            fermentadores.get(i).setIdentificador(v.vProduccion.getBarra(i).getPos(3));
            destiladores.get(i).setIdentificador(v.vProduccion.getBarra(i).getPos(4));
            enbotelladores.get(i).setIdentificador(v.vProduccion.getBarra(i).getPos(5));
            cortes.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            hornos.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            molinos.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            fermentadores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            destiladores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            enbotelladores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            transportistas.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
        }
    }
    
    /** Clase Hilo que permite hacer la actualización en tiempo real de la tabla */
    class MiHilo extends Thread{
    
        private BufferTandas tandas_actualziar;
        
        public MiHilo(BufferTandas tandas_actualizar){
            this.tandas_actualziar = tandas_actualizar;
        }
        
        public void run(){
            for(;;) {                
                Tanda t = this.tandas_actualziar.remove();
                if(t != null){
                    m.updateEstadoTanda(t);
                    cargarDatosTandas();
                    if(t.getEstado().equals("Enbarrilada")){
                        cargarInformeTandas();
                        //m.deleteTanda(t);
                        cargarDatosTandas();
                    }
                }
            }
        }
    }
}