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
import Procesos.Horno;
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
    //private BufferTandas monitor = new BufferTandas();
    
    BufferTandas bft = new BufferTandas();
    BufferPiniasCortadas bpc = new BufferPiniasCortadas();
    BufferPiniasHorneadas bph = new BufferPiniasHorneadas();
    BufferPiniasMolidas bpm = new BufferPiniasMolidas();
    BufferPiniasFermentadas bpf = new BufferPiniasFermentadas();
    BufferMezcalDestilado bmd = new BufferMezcalDestilado();
    BufferBarriles bb = new BufferBarriles();
    Corte c1 = new Corte(1, bft, bpc);
    Corte c2 = new Corte(2, bft, bpc);
    Corte c3 = new Corte(3, bft, bpc);
    Horno h1 = new Horno(1, bpc, bph);
    Horno h2 = new Horno(2, bpc, bph);
    Horno h3 = new Horno(3, bpc, bph);
    
    
    
    
    
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
    
    private int cantPinas = 0, // Cantidad de piñas a usar
            id_Maguey = 0, // id maguey seleccionado
            limite = 0;
    private char tipoMaguey;
    private String tipoMezcal; // Tipo de alcohol
    private double porcentajeAlcohol;
    private String bandera;
    
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
                //int a = Integer.parseInt(JOptionPane.showInputDialog(v, "Introduce el proceso"));
                //producir((int)Math.random() * 5);
                 //   iniciarTanda(3000); // Los 3 primeros deben estar disponible
                 //   JOptionPane.showMessageDialog(v, "No hay espacio para empezar a producir");
                break;
                
        }
    }
    
    
    public void iniciarTanda(){
        
       /**
        * Calculando el tiempo de la tanda.
        *   %Alcohol 
        *   - 55 ::: 3 s
        *   - 45 ::: 6 s
        *   - 38 ::: 9 s
        *  Tipo Alcohol
        *   Blanco – Mezcal sin color, almacenado por menos de dos meses, verificando su almacenado.
        *   Madurado en vidrio- Almacenado en recipientes de vidrio cuano menos 1 año y bajo condiciones reguladas de temperatura, iluminación y humedad.
        *   Reposado – Almacenado entre dos meses y un año.
        *   Añejo – Almacenado por lo menos un año, en barriles de no más de 200 litros.
        */
        
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
        
        c1.start();
        c2.start();
        c3.start();
        h1.start();
        h2.start();
        h3.start();
        
    }
    
    public void producir(int pos){
        // Siempre son 3 días, por lo que durará 3 * n segundos
        
        /*JProgressBar pb1 = v.ventana2.barra1.getPos(pos).getBarra(),
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
        */
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
