package Vista;

import Controlador.Controlador;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 * Clase para la vista principal del proyecto.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Vista extends JFrame{

    // Variables de instancia - Vistas internas.
    public VistaProducir vProducir;
    public VistaProduccion vProduccion;
    public VistaTraslado vTraslado;
    public VistaRegistro vRegistro;
    public VistaRegistroFinal vInforme;

    // Variable de instancia - Contenedor de vistas.
    public JTabbedPane principal;

    /**
     * Constructor de objetos de Vista.
     */
    public Vista(){
        super("MEZCALERA CHAHUE");
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiInternalFrameUI");
        } catch (Exception e) {}
        setSize(780,670);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        colocar();
        revalidate();
    }

    /**
     * Coloca los componentes al panel principal.
     */
    public void colocar(){
        ImageIcon img = new ImageIcon(
                getClass().getResource("/Imagenes/maguey.png")),
        mAzul = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Azul.png")),
        mCenizo = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Cenizo.png")),
        mCoyote = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Coyote.png")),
        mCuishe = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Cuishe.png")),
        mEspadín = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Espadín.png")),
        mMexicano = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Mexicano.png")),
        mPapalote = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Papalote.png")),
        mTobala = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Tobalá.png")),
        mArroquenio = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Arroqueño.png")),
        mJabali = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Jabalí.png")),
        mMadreCuish = new ImageIcon(getClass().getResource("/Imagenes/Maguey_MadreCuish.png")),
        mTepextate = new ImageIcon(getClass().getResource("/Imagenes/Maguey_Tepextate.png"));
                
        String[] txts = {"Tobalá","Cuishe","Coyote","Tepeztate","Papalote","Cenizo",
                        "Estoquillo","Mexicano","Tobalá","Cuishe","Coyote","Tepeztate"};
        ImageIcon[] imagenes = {mAzul, mCenizo, mCoyote, mCuishe, mEspadín, mMexicano, mPapalote, mTobala,
                                mArroquenio, mJabali, mMadreCuish, mTepextate};
        
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

    /**
     * Conecta la vista con el controlador.
     *
     * @param c Controlador a utilizar.
     */
    public void conectarControlador(Controlador c){
       vProducir.conectarControlador(c);
       vRegistro.conectarControlador(c);
       vInforme.conectarControlador(c);
       vTraslado.conectarControlador(c);
    }

    /**
     * Rellena las opciones de las respectivas vistas.
     *
     * @param opcion Arrays con información a colocar.
     */
    public void llenarOpciones(ArrayList<String>... opcion){//mezcales, ArrayList<String> porcentajes,ArrayList<String> tipos){
        vProducir.llenarOpciones(opcion[0], opcion[1],opcion[2]);
        vTraslado.llenarClientes(opcion[3]);
    }
}
