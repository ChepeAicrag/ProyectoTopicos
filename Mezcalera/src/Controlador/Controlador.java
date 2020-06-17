package Controlador;

import Modelo.ColorearFilas;
import Modelo.ManejoDatos;
import Procesos.*;
import Vista.Vista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase controladora, aquí se encuentra la parte lógica del proyecto.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Controlador implements ActionListener{

    // Variable de instancia - Vista del proyecto.
    private Vista v;

    // Variable de instancia - Modelo del proyecto.
    private ManejoDatos m;

    // Variables de instancia - Buffers de Tandas
    private BufferTandas bft = new BufferTandas(),
                         bpc = new BufferTandas(),
                         bph = new BufferTandas(),
                         bpm = new BufferTandas(),
                         bpf = new BufferTandas(),
                         bmd = new BufferTandas(),
                         bb  = new BufferTandas(),
          TANDAS_TRANSPORTAR = new BufferTandas(),
          TANDAS_ACTUALIZAR  = new BufferTandas();

    // Variables de instancia - Equipos
    private ArrayList<Equipo> cortes                = new ArrayList<>(),
                              hornos                = new ArrayList<>(),
                              molinos               = new ArrayList<>(),
                              fermentadores         = new ArrayList<>(),
                              destiladores          = new ArrayList<>(),
                              enbotelladores        = new  ArrayList<>();
    private ArrayList<Transportista> transportistas = new ArrayList<>();

    // Variable de instancia - Ejecutador de los Hilos.
    private ExecutorService ejecutador      = Executors.newCachedThreadPool();

    // Variable de instancia - Array de los ID de Tandas en proceso.
    private ArrayList<Integer>   tandasProduciendo  = new ArrayList<Integer>(),
                       tandasTransportando  = new ArrayList<Integer>();

    // Variables para las acciones de los botones.
    private int filaPulsada = 0, id_tanda = 0, limite = 0,id_Maguey = 0, id_alcohol = 0, id_tipoMezcal = 0, cantPinias = 0;

    /**
     * Constructor para objetos de Controlador
     *
     * @param m Modelo para realizar consultas.
     * @param v Vista a mostrar.
     */
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
     * Consulta para rellenar las opciones de las diferentes vistas.
     */
    private void actualizarOpciones(){
        ArrayList<String> porcentajes = m.conexionConsultarNombre("select * from mezcal.gradoalcohol"),
                          tipos       = m.conexionConsultarNombre("select * from mezcal.tipomezcal"),
                          mezcales    = m.conexionConsultarNombre("select * from mezcal.maguey order by id_maguey"),
                          clientes    = m.conexionConsultarNombre("select * from mezcal.cliente");
        v.llenarOpciones(mezcales, porcentajes, tipos, clientes);
    }

    /**
     * Método que controla las acciones de los botones de cada vista.
     */
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
            /** Registra la tanda de acuerdo a los campos rellenados */
            case "registrar":
                System.out.println(id_Maguey + " <<<<->>> " + cantPinias);
                if(id_Maguey > 0 && cantPinias > 0){
                     v.principal.setSelectedIndex(1);
                     id_tipoMezcal = v.vProducir.tipo.getSelectedIndex() + 1;
                     id_alcohol = v.vProducir.alcohol.getSelectedIndex() + 1;
                     t = new Tanda(id_Maguey,id_alcohol,id_tipoMezcal, cantPinias);
                     System.out.println(t);
                     m.insertTanda(t);
                     v.principal.setSelectedIndex(1);
                     cargarDatosTandas();
                     v.vRegistro.tabla.updateUI();
                     v.vRegistro.tabla.revalidate();
               }else
                    JOptionPane.showMessageDialog(v,"No rellenó correctamente");
                break;
            /** Elimina el registro de la tabla de tandas disponibles para producir */
            case "eliminar":
                filaPulsada = v.vRegistro.tabla.getSelectedRow();
                if (filaPulsada >= 0) {
                    t = new Tanda();
                    id_tanda = Integer.parseInt((String) v.vRegistro.mtt.getValueAt(filaPulsada, 0));
                    t.setId(id_tanda);
                    int respuesta = JOptionPane.showConfirmDialog(v, "¿Está seguro de eliminar la tanda con id: " + t.getId());
                    if(!tandasProduciendo.contains(id_tanda) && respuesta == JOptionPane.YES_OPTION){
                        m.deleteTanda(t);
                        cargarDatosTandas();
                        v.vRegistro.tabla.updateUI();
                        JOptionPane.showMessageDialog(v,"Eliminó a tanda con id " + t.getId() );
                    }else if(respuesta == JOptionPane.YES_OPTION)
                        JOptionPane.showMessageDialog(v, "No lo puede eliminar, está en produccion");
                    else
                        JOptionPane.showMessageDialog(v,"Tanda no eliminada...");
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
                        bft.put(tandaProducir);
                        tandaProducir.setEstado("Produciendo");
                        m.updateEstadoTanda(tandaProducir);
                        cargarDatosTandas();
                    }else
                        JOptionPane.showMessageDialog(v, "Ya está en proceso esa tanda");
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
                ejecutador.shutdown();
                System.exit(0);
                break;
        } 
   }

    /**
     * Carga información a la tabla de TANDAS DISPONIBLES.
     */
    public void cargarDatosTandas(){
        ColorearFilas c = new ColorearFilas(5);
        String consultaTandas = "select * from mezcal.tanda where status != 'Entregada'";
        /**
         *  Se lo dejamos al Thread del despachador de eventos
         *  para que la actualización de la tabla sea instantanea.
         */
        SwingUtilities.invokeLater(() ->{
            v.vRegistro.mtt.setDatos(m.conexionConsultaTanda(consultaTandas));
            v.vRegistro.tabla.getColumnModel().getColumn(5).setCellRenderer(c);
            v.vRegistro.tabla.updateUI();
            v.vRegistro.updateUI();
        });
    }

    /**
     * Carga la informacion de las tandas a la ultima tabla.
     *
     */
    private void cargarInformeTandas() {
        v.vInforme.mti.setDatos(m.conexionConsultaInformeTanda());
        v.vInforme.tabla.updateUI();
    }
      
    /** 
     * Solicita la cantidad de piñas y las valida para el maguey dado.
     *
     * @param limite Cantidad maxima de piñas a usar.
     * @param nombreMaguey Nombre del maguey.
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

    /**
     * Valida si hay tandas pendientes en producción o traslado.
     *
     */
    private boolean hayProduccion(){
        return !tandasProduciendo.isEmpty() || !tandasTransportando.isEmpty();
    }

    /**
     * Inicia todos los equipos (Hilos)
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
        ejecutador.submit(new MiHilo(TANDAS_ACTUALIZAR));
        ejecutador.submit(new HiloVista());
    }

    /**
     * Método que permite preparar los equipos
     */
    private void prepararEquipos(){
        int id_Equipo = 0;
        for (int i = 0; i < 3; i++) {
            id_Equipo = i + 1;
            cortes.add(new Cortador(id_Equipo, bft, bpc));
            hornos.add(new Horno(id_Equipo, bpc, bph));
            molinos.add(new Molino(id_Equipo , bph, bpm));
            fermentadores.add(new Fermentador(id_Equipo, bpm, bpf));
            destiladores.add(new Destilador(id_Equipo, bpf, bmd));
            enbotelladores.add(new Enbotelladora(id_Equipo, bmd, bb));
            transportistas.add(new Transportista(id_Equipo, bb, v.vTraslado));
            cortes.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(0));
            hornos.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(1));
            molinos.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(2));
            fermentadores.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(3));
            destiladores.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(4));
            enbotelladores.get(i).setBarraIdentificador(v.vProduccion.getBarra(i).getPos(5));
            cortes.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            hornos.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            molinos.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            fermentadores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            destiladores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            enbotelladores.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            ((Enbotelladora)enbotelladores.get(i)).setTandasTransportar(tandasTransportando);
            transportistas.get(i).setTandasActualizar(TANDAS_ACTUALIZAR);
            transportistas.get(i).setTandasTransportadas(tandasTransportando);
        }
    }
    
    /**
     * Clase que te permite hacer la actualización de las tablas cada que cambien de estado la tanda dada.
     */
    class MiHilo extends Thread{

        // Variable de instancia - Tandas a actualizar.
        private BufferTandas tandas_actualizar;

        /**
         * Constructor para objetos de MiHilo.
         * @param tandas_actualizar Tanda a actualizar.
         */
        public MiHilo(BufferTandas tandas_actualizar){
            this.tandas_actualizar = tandas_actualizar;
        }
        
        public void run(){
            for(;;) {
                Tanda t = this.tandas_actualizar.remove();
                if(t != null){
                    m.updateEstadoTanda(t);
                    cargarDatosTandas();
                    if(t.getEstado().equals("Entregada")) {
                        tandasProduciendo.remove(Integer.valueOf(t.getId()));
                        tandasTransportando.remove(Integer.valueOf(t.getId()));
                        System.out.println(tandasProduciendo.toString());
                        cargarInformeTandas();
                        cargarDatosTandas();
                        }
                    }
             }
        }
    }

    /**
     * Clase que nos permitirá ajustar el tamaño de las ventanas al instante.
     */
    class HiloVista extends  Thread{

        // Variable de instancia - Determina si estás en la vista o no.
        private boolean aquiYa = false;

        public void run(){
            int op = 0, aux = 0;
            while (true) {
                op = v.principal.getSelectedIndex();
                if ((op == 1 || op == 2 || op == 3 || op == 4) && !aquiYa) {
                    v.setSize(1020, 725);
                    v.setLocationRelativeTo(null);
                }else if (op == 0 && !aquiYa) {
                    v.setSize(770, 700);
                    v.setLocationRelativeTo(null);
                }while(aux == op){
                    aquiYa = true;
                    aux = v.principal.getSelectedIndex();
                    System.out.print("");
                }
                aquiYa = false;
                aux = op;
            }
        }
    }
}