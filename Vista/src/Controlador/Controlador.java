/*
 * Clase Controlador
 */

package Controlador;

import Modelo.ColorearFilas;
import Modelo.Conexion;
import Modelo.ManejoDatos;
import Procesos.*;
import Vista.Vista;

import java.awt.event.*;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

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
    ArrayList<Corte2> cortes = new ArrayList<>();
    ArrayList<Horno2> hornos = new ArrayList<>();
    ArrayList<Molino2> molinos = new ArrayList<>();
    ArrayList<Fermentador> fermentadores = new ArrayList<>();
    ArrayList<Destilador2> destiladores = new ArrayList<>();
    ArrayList<Enbotelladora> enbotelladores = new  ArrayList<>();
    ArrayList<Transportista> transportistas = new ArrayList<>();
    private ExecutorService ejecutador = Executors.newCachedThreadPool();
    int filaPulsada = 0, id_tanda = 0, limite = 0,id_Maguey = 0, id_alcohol = 0, id_tipoMezcal = 0, cantPinias = 0;
    ArrayList<Integer> tandasProduciendo = new ArrayList<Integer>(),
                       tandasTransportando = new ArrayList<Integer>(); // Almacena los id de las tandas


    public Controlador(Vista v, ManejoDatos m){
        this.v = v;
        this.m = m;
        prepararEquipos();
        IniciarEquipos();
        actualizarOpciones();
        cargarDatosTandas();
        cargarInformeTandas();

        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(hayProduccion()){
                    JOptionPane.showMessageDialog(v,"NO PUEDE ABANDONAR, HAY MEZCALES PRODUCIENDOSE");
                    v.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    return;
                }else{
                    m.cerrarConexion();
                    System.exit(0);
                }
            }});
    }

    /**
     * Consulta para rellenar los checkbox
     *
     * */
    private void actualizarOpciones(){
        ArrayList<String> porcentajes = m.conexionConsultarNombre("select * from mezcal.gradoalcohol"),
                          tipos = m.conexionConsultarNombre("select * from mezcal.tipomezcal"),
                          mezcales = m.conexionConsultarNombre("select * from mezcal.maguey"),
                          clientes = m.conexionConsultarNombre("select * from mezcal.cliente");
        v.llenarOpciones(mezcales, porcentajes, tipos, clientes);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Tanda t;
        Object datos[] = null;
        String o = ae.getActionCommand();
        if(o.matches("[0-9]*")){
            datos = m.selectMaguey(Integer.parseInt(o));
            System.out.println("Seleccionó el maguey " + (String) datos[1]);
            id_Maguey = (int) datos[0];
            limite = (int) datos[2];
            System.out.println(limite);
            cantPinias = cantidadPinas(limite, (String) datos[1]);
        }
        switch(o){
            case "registrar":
                if(id_Maguey > 0 && cantPinias > 0){
                     v.principal.setSelectedIndex(1);
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
            case "atrasProducir":
                if(5 < 10)
                    return;
                v.iniciarVistas();
                if (!tandasProduciendo.isEmpty()){
                    v.principal.setEnabledAt(2,true);
                }if(!tandasTransportando.isEmpty()){
                    v.principal.setEnabledAt(3,true);
                }

                // Habilitar produccion y transporte si tenemos a una tanda en produccion
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
                    v.principal.setEnabledAt(2,true);
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
                if(bb.isEmpty()){
                    JOptionPane.showMessageDialog(v,"No hay barriles para transportar");
                    return;
                }
                transportistas.stream()
                        .filter(transportista -> !transportista.isAlive())
                        .forEach(Thread::start);
                break;
            case "salir":
                if(hayProduccion()){
                    JOptionPane.showMessageDialog(v,"NO PUEDE ABANDONAR, HAY MEZCALES PRODUCIENDOSE");
                    return;
                }
                m.cerrarConexion();
                System.exit(0);
                break;
        } 
   }

    /**
     * Carga información a la primera tabla sobre las tandas que están disponibles para producir
     * */
    public void cargarDatosTandas(){
        ColorearFilas c = new ColorearFilas(5);
        String consultaTandas = "select * from mezcal.tanda where status != 'Entregada'";
        /** Se lo dejamos al Thread del despachador de eventos
         *  De lo contrario tendriamos un error
         * */
        SwingUtilities.invokeLater(() ->{
            v.vRegistro.mtt.setDatos(m.conexionConsultaTanda(consultaTandas));
            v.vRegistro.tabla.getColumnModel().getColumn(5).setCellRenderer(c);
            v.vRegistro.tabla.updateUI();
            v.vRegistro.updateUI();
        });
    }

    /**
     * Carga la informacion de las tandas a la ultima tabla
     * */
    private void cargarInformeTandas() {
        String consultaTandas = "select * from mezcal.tanda";
        v.vInforme.mti.setDatos(m.conexionConsultaInformeTanda(consultaTandas));
        v.vInforme.tabla.updateUI();
    }
      
    /** 
     * Solicita la cantidad de piñas y las valida para el maguey dado
     * @param limite Cantidad maxima de piñas a usar
     * @param nombreMaguey Nombre del maguey
     */
    private int cantidadPinas(int limite, String nombreMaguey){
        int cantidad = 0;
        boolean op = false;
        while (!op) {            
            try{
                String msj = JOptionPane.showInputDialog(v, "Introduce la cantidad de piñas a usar del maguey " +nombreMaguey);
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

    /***/
    private boolean hayProduccion(){
        return !tandasProduciendo.isEmpty() || !tandasTransportando.isEmpty();
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
        ejecutador.submit(new HiloVista());
    }

    /**
     * Método que permite preparar los equipos
     * Falta hacer que sean introducidos a la base de datos
     * */
    private void prepararEquipos(){
        int id_Equipo = 0;
        for (int i = 0; i < 3; i++) {
            id_Equipo = i + 1;
            cortes.add(new Corte2(id_Equipo, bft, bpc));
            hornos.add(new Horno2(id_Equipo, bpc, bph));
            molinos.add(new Molino2(id_Equipo , bph, bpm));
            fermentadores.add(new Fermentador(id_Equipo, bpm, bpf));
            destiladores.add(new Destilador2(id_Equipo, bpf, bmd));
            enbotelladores.add(new Enbotelladora(id_Equipo, bmd, bb));
            transportistas.add(new Transportista(id_Equipo, bb, v.vTraslado));
            cortes.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(0));
            hornos.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(1));
            molinos.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(2));
            fermentadores.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(3));
            destiladores.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(4));
            enbotelladores.get(i).setIdentificador(v.vProduccion.getBarra(i).getPos(5));
            cortes.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            hornos.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            molinos.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            fermentadores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            destiladores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            enbotelladores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            enbotelladores.get(i).setTandasTransportar(tandasTransportando); // Para saber si hay tandas por transportar
            transportistas.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            transportistas.get(i).setTandasTransportadas(tandasTransportando);
        }
    }
    
    /** Clase Hilo que permite hacer la actualización en tiempo real de la tabla */
    class MiHilo extends Thread{
    
        private BufferTandas tandas_actualizar;
        
        public MiHilo(BufferTandas tandas_actualizar){
            this.tandas_actualizar = tandas_actualizar;
        }
        
        public void run(){
            for(;;) {
                Tanda t = this.tandas_actualizar.remove();
                if(t != null){
                    m.updateEstadoTanda(t);
                    cargarDatosTandas();
                    //if (t.getEstado().equals("Entregada")){

                //    }
                    if(t.getEstado().equals("Entregada")) {
                        tandasProduciendo.remove(new Integer(t.getId()));
                        tandasTransportando.remove(new Integer(t.getId()));
                        System.out.println(tandasProduciendo.toString());
                        cargarInformeTandas();
                        //m.deleteTanda(t);
                        cargarDatosTandas();
                        }
                    }
             }
        }
    }

    /** Hilo que nos permitira ajustar el tamaño de las ventanas
     * Ya funciona correctamente o en lo esprado.
     */
    class HiloVista extends  Thread{

        private boolean aquiYa = false;

        public void run(){
            int op = 0, aux = 0;
            while (true) {
                op = v.principal.getSelectedIndex();
                if ((op == 1 || op == 2 || op == 3 || op == 4) && !aquiYa) {
                    v.setSize(1020, 725);
                    v.setLocationRelativeTo(null);
                    if (op == 4)
                        v.vInforme.reajustarVista();
                    System.out.println(aquiYa);
                } else if (op == 0 && !aquiYa) {
                    v.setSize(780, 670);
                    v.setLocationRelativeTo(null);
                    System.out.println(aquiYa);
                }
                /** Una forma de hacer un wait */
                while(aux == op){
                    aquiYa = true;
                    aux = v.principal.getSelectedIndex();
                    System.out.print("");
                }
                aquiYa = false;
                /** Para que al llegar aquí sea unicamente cuando se cambia de ventana */
                System.out.println(aquiYa);
                aux = op;
            }
        }
    }
}