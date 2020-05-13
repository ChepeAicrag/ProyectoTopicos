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
    
    private VistaProducir ventana1;
    public VistaProduccion ventana2;
    private VistaTraslado ventana3;
    public JTabbedPane principal;
    public Vista(){
        setSize(1080,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);
        colocar();
        revalidate();
    }
    
    public void colocar(){
        ImageIcon img = new ImageIcon(
                getClass().getResource("/TestVistas/maguey.png"));
        String[] txts = {"Tobalá","Cuishe","Coyote","Tepeztate",
                        "Papalote","Cenizo","Estoquillo","Mexicano"};
        ImageIcon[] imagenes = {img,img,img,img,img,img,img,img};
        
        principal = new JTabbedPane();
        ventana1 = new VistaProducir(imagenes, txts);
        //ventana1.setBackground(Color.yellow);
        principal.addTab("Seleccion", ventana1);
        ventana2 = new VistaProduccion();
        principal.addTab("Produccion", ventana2);
        ventana3 = new VistaTraslado();
        principal.addTab("Traslado", ventana3);
        add(principal);
    }
    
    public void conectarControlador(Controlador c){
       ventana1.conectarControlador(c);
    }
    
    public void llenarOpciones(ArrayList<String> porcentajes,ArrayList<String> tipos){
        ventana1.llenarOpciones(porcentajes, tipos);
    }
}
