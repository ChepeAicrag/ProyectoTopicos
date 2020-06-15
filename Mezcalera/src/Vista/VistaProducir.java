package Vista;

import Componentes.BCE;
import Componentes.BarraEleccion;
import Controlador.Controlador;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Clase para la vista de producción de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class VistaProducir extends JPanel{

    // Variable de instancia - Barra de elección.
    private BarraEleccion eleccion;

    // Variable de instancia - Bóton para registrar.
    private JButton btnRegistrar;

    // Variables de instancia - Mostrar tipos de alcohol y tipos de mezcal.
    public JComboBox<String> alcohol, tipo;

    // Variables de instancia - Etiquetas de indicación.
    private JLabel etqAlcohol, etqTipo;

    // Variable de instancia - Arreglo de imagenes de los magueyes.
    private ImageIcon[] imagenes;

    // Variable de instancia - Arreglo de nombre de los magueyes.
    private String[] textos;

    // Variables de instancia - Imagenes.
    private ImageIcon imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_0.jpg")),
                  icon = new ImageIcon(imgFond.getImage().getScaledInstance(1020, 680, Image.SCALE_DEFAULT));

    // Variable de instancia - Etiqueta para la imágen de fondo.
    private JLabel fondo = new JLabel(icon);

    // Variable de instancia - Tipo de layout manager.
    private SpringLayout s = new SpringLayout();

    /**
     * Constructor de objetos de VistaProducir.
     *
     * @param imagenes Arreglo de imagenes a colocar a los botones.
     * @param textos Arreglo de textos a colocar a los botones.
     */
    public VistaProducir(ImageIcon[] imagenes,String[] textos){
        setSize(250, 700);
        setVisible(true);
        this.imagenes = imagenes;
        this.textos = textos;
        colocar();
        revalidate();
    }

    /**
     * Coloca los elementos al panel.
     */
    private void colocar(){
        setLayout(s);
        eleccion = new BarraEleccion(imagenes,textos);
        eleccion.setLayout(new GridLayout(4,4));
        //eleccion.setBackground(Color.yellow);
        eleccion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(5), "SELECCIONA EL MAGUEY", TitledBorder.CENTER, TitledBorder.TOP));
        eleccion.setFondoImagen(imgFond);
        etqAlcohol = new JLabel("SELECCIONA EL GRADO DE ALCOHOL");
        etqAlcohol.setFont(new Font("Helvetica", Font.BOLD, 14));
        etqAlcohol.setForeground(Color.WHITE);
        alcohol = new JComboBox<>();
        etqTipo = new JLabel("SELECCIONA EL TIPO DE MEZCAL");
        etqTipo.setFont(new Font("Helvetica", Font.BOLD, 14));
        etqTipo.setForeground(Color.WHITE);
        tipo = new JComboBox<>();
        btnRegistrar = new JButton();
        //producir.setBackground(Color.decode("#eca0b6"));
            //btn.setForeground(Color.WHITE);
            //btn.setFont(new Font("Sylfaen", Font.BOLD, 13));
            //btn.setBorder(BorderFactory.createEtchedBorder(Color.decode("#ffc8bd"), Color.decode("#f3d1f4")));
        btnRegistrar.setFont(new Font("Sylfaen", Font.BOLD, 17));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
        btnRegistrar.setActionCommand("registrar");
        //producir.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/btnRegistrar.png"))
        //        .getImage().getScaledInstance(110,60, Image.SCALE_DEFAULT)));
        //producir.setSize(100, 30);
        //producir.setPreferredSize(new Dimension(100, 30));
        add(eleccion);
        add(etqAlcohol);
        add(alcohol);
        add(etqTipo);
        add(tipo);
        add(btnRegistrar);
        iniciarVistas();
    }

    /**
     * Conecta la vista con el controlador.
     *
     * @param c Controlador a utilizar.
     */
    public void conectarControlador(Controlador c){
        int i = 1;
        for(BCE b : eleccion.getBotones()){
            b.getBoton().setActionCommand("" + i++);
            b.getBoton().addActionListener(c);
        }
        btnRegistrar.addActionListener(c);
    }
    
    /**
     * Rellena las opciones de magueyes, % de alcohol y tipo de mezcal.
     *
     * @param mezcales Array de nombre de magueyes.
     * @param porcentajes Array de porcentajes de alcohol.
     * @param tipos Array de los nombres de tipos de mezcal.
     */
    public void llenarOpciones(ArrayList<String> mezcales,ArrayList<String> porcentajes,ArrayList<String> tipos){
        for (String p : porcentajes) {
            alcohol.addItem(p + "%");
        }
        for (String t : tipos) {
            tipo.addItem(t);
        }
        String nombreMezcales[] = new String[mezcales.size()-1];
        for (int i = 0; i < mezcales.size() - 1; i++) {
            nombreMezcales[i] = mezcales.get(i);
        }
        eleccion.setTextos(nombreMezcales);
        revalidate();
    }

    /**
     * Coloca los elementos en una vista inicial.
     */
    public void iniciarVistas(){
        remove(eleccion);
        add(eleccion);
        s.putConstraint(SpringLayout.NORTH, eleccion, 5, SpringLayout.NORTH,this);
        s.putConstraint(SpringLayout.WEST, eleccion, 80, SpringLayout.WEST,this);
        s.putConstraint(SpringLayout.EAST, eleccion, -80, SpringLayout.EAST,this);
        s.putConstraint(SpringLayout.SOUTH, eleccion, -80, SpringLayout.SOUTH,this);
        remove(etqAlcohol);
        add(etqAlcohol);
        s.putConstraint(SpringLayout.NORTH,etqAlcohol, 10, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, etqAlcohol, 100, SpringLayout.WEST,this);
        remove(alcohol);
        add(alcohol);
        s.putConstraint(SpringLayout.NORTH,alcohol, 15, SpringLayout.SOUTH,etqAlcohol);
        s.putConstraint(SpringLayout.WEST, alcohol, 195, SpringLayout.WEST,this);
        remove(etqTipo);
        add(etqTipo);
        s.putConstraint(SpringLayout.NORTH,etqTipo, 10, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, etqTipo, 60, SpringLayout.EAST,etqAlcohol);
        remove(tipo);
        add(tipo);
        s.putConstraint(SpringLayout.NORTH,tipo, 15, SpringLayout.SOUTH,etqTipo);
        s.putConstraint(SpringLayout.WEST, tipo, 150, SpringLayout.EAST,etqAlcohol);
        remove(btnRegistrar);
        add(btnRegistrar);
        s.putConstraint(SpringLayout.NORTH, btnRegistrar, 40, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, btnRegistrar, 330, SpringLayout.WEST,this);
    }

    /**
     * Pinta el fondo de la vista.
     * @param g Graphocs para pintar.
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(imgFond.getImage(), 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }

}
