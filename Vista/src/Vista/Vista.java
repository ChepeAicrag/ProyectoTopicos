/*
 * Vista de todo el proyecto, usando ventanas
 *
 */

package Vista;

import Controlador.Controlador;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * 
 * @author García García José Ángel
 */
public class Vista extends JFrame{
    
    public VistaProducir vProducir;
    public VistaProduccion vProduccion;
    public VistaTraslado vTraslado;
    public VistaRegistro vRegistro;
    public VistaRegistroFinal vInforme;
    public JTabbedPane principal;
    
    public Vista(){
        setSize(1200,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        colocar();
        revalidate();
    }
    
    public void colocar(){
        ImageIcon img = new ImageIcon(
                getClass().getResource("/TestVistas/maguey.png"));
        String[] txts = {"Tobalá","Cuishe","Coyote","Tepeztate","Papalote","Cenizo",
                        "Estoquillo","Mexicano","Tobalá","Cuishe","Coyote","Tepeztate"};
        ImageIcon[] imagenes = {img,img,img,img,img,img,img,img,img,img,img,img};
        
        principal = new JTabbedPane();
        vProducir = new VistaProducir(imagenes, txts);
        principal.addTab("Seleccion", vProducir);
        vRegistro = new VistaRegistro();
        principal.add("Registro_Tandas",vRegistro);
        vProduccion = new VistaProduccion();
        principal.addTab("Produccion", vProduccion);
        vTraslado = new VistaTraslado();
        principal.addTab("Traslado", vTraslado);
        vInforme = new VistaRegistroFinal();
        principal.add("Informe",vInforme);
        add(principal);
    }
    
    public void conectarControlador(Controlador c){
       vProducir.conectarControlador(c);
       vRegistro.conectarControlador(c);
       vInforme.conectarControlador(c);
    }
    
    public void llenarOpciones(ArrayList<String> mezcales, ArrayList<String> porcentajes,ArrayList<String> tipos){
        vProducir.llenarOpciones(mezcales, porcentajes, tipos);
    }
}
