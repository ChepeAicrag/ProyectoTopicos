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
import javax.swing.UIManager;

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
        super("MEZCALERA CHAHUE");
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiInternalFrameUI");
        } catch (Exception e) {}
        //setSize(1020, 725);
        setSize(780,670);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        colocar();
        revalidate();
    }
    
    public void colocar(){
        ImageIcon img = new ImageIcon(
                getClass().getResource("/TestVistas/maguey.png")),
        mAzul = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Azul.png")),
        mCenizo = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Cenizo.png")),
        mCoyote = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Coyote.png")),
        mCuishe = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Cuishe.png")),
        mEspadín = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Espadín.png")),
        mMexicano = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Mexicano.png")),
        mPapalote = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Papalote.png")),
        mTobala = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Tobalá.png"));
                
        String[] txts = {"Tobalá","Cuishe","Coyote","Tepeztate","Papalote","Cenizo",
                        "Estoquillo","Mexicano","Tobalá","Cuishe","Coyote","Tepeztate"};
        ImageIcon[] imagenes = {mCoyote,img,mPapalote,mCenizo,
                                img,mMexicano,mTobala,mCuishe,
                                mAzul,img,img,img};
        
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
        principal.setEnabledAt(1, false);
        principal.setEnabledAt(2, false);
        principal.setEnabledAt(3, false);
        add(principal);
    }
    
    public void conectarControlador(Controlador c){
       vProducir.conectarControlador(c);
       vRegistro.conectarControlador(c);
       vInforme.conectarControlador(c);
       vTraslado.conectarControlador(c);
    }
    
    public void llenarOpciones(ArrayList<String> mezcales, ArrayList<String> porcentajes,ArrayList<String> tipos){
        vProducir.llenarOpciones(mezcales, porcentajes, tipos);
    }

    /** Reajusta la vista de cada panel */
    public void reajustarVistas(){
        setSize(1020, 725);
        setLocationRelativeTo(null);
        principal.setEnabledAt(0,false);
        principal.setSelectedIndex(1);
        principal.setEnabledAt(1, true);
        principal.setEnabledAt(2, true);
        principal.setEnabledAt(3, true);
        vInforme.reajustarVista();
    }

    public void iniciarVistas(){
        setSize(780,670);
        setLocationRelativeTo(null);
        vInforme.iniciarVistas();
        vProducir.iniciarVistas();
        principal.setEnabledAt(0,true);
        principal.setSelectedIndex(0);
        principal.setEnabledAt(1, false);
        principal.setEnabledAt(2, false);
        principal.setEnabledAt(3, false);
    }
}
