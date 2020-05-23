package Vista;

import Componentes.BCE2;
import Componentes.BarraEleccion;
import Controlador.Controlador;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author García García José Ángel
 */
public class VistaProducir extends JPanel{
    
    private BarraEleccion eleccion;
    private JButton producir;
    public JComboBox<String> alcohol, tipo;
    private JLabel etqAlcohol, etqTipo;
    private ImageIcon[] imagenes;
    private String[] textos;
    
    public VistaProducir(ImageIcon[] imagenes,String[] textos){
        //setTitle("Vista Producir");
        setSize(1020, 680);
        //setLocationRelativeTo(null);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        this.imagenes = imagenes;
        this.textos = textos;
        colocar();
        revalidate();
    }
    /* Error al posicion, si se crea bien */
    private void colocar(){
        SpringLayout s = new SpringLayout();
        setLayout(s);
        eleccion = new BarraEleccion(imagenes,textos);
        eleccion.setLayout(new GridLayout(2,4));
        eleccion.setBackground(Color.yellow);
        eleccion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(2), "Selecciona tu mezcal", TitledBorder.CENTER, TitledBorder.TOP));
        add(eleccion);
        s.putConstraint(SpringLayout.NORTH, eleccion, 5, SpringLayout.NORTH,this);
        s.putConstraint(SpringLayout.WEST, eleccion, 20, SpringLayout.WEST,this);
        s.putConstraint(SpringLayout.EAST, eleccion, -20, SpringLayout.EAST,this);
        s.putConstraint(SpringLayout.SOUTH, eleccion, -200, SpringLayout.SOUTH,this);
        etqAlcohol = new JLabel("Selecciona el grado de alcohol");
        add(etqAlcohol);
        s.putConstraint(SpringLayout.NORTH,etqAlcohol, 10, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, etqAlcohol, 405, SpringLayout.WEST,this);
        // Consultar en la base de datos
        String[] datAlcohol = {"%55","%48","%37"};
        alcohol = new JComboBox<>(datAlcohol);
        add(alcohol);
        s.putConstraint(SpringLayout.NORTH,alcohol, 15, SpringLayout.SOUTH,etqAlcohol);
        s.putConstraint(SpringLayout.WEST, alcohol, 465, SpringLayout.WEST,this);
        etqTipo = new JLabel("Selecciona el tipo de mezcal");
        add(etqTipo);
        s.putConstraint(SpringLayout.NORTH,etqTipo, 12, SpringLayout.SOUTH,alcohol);
        s.putConstraint(SpringLayout.WEST, etqTipo, 405, SpringLayout.WEST,this);
        // Tipo de mezcal, guardar en la base de datos (TipoMezcal)
        String[] datTipo = {"Añejo","Reposado","Madurado","Blanco"};
        tipo = new JComboBox<>(datTipo);
        add(tipo);
        s.putConstraint(SpringLayout.NORTH,tipo, 12, SpringLayout.SOUTH,etqTipo);
        s.putConstraint(SpringLayout.WEST, tipo, 460, SpringLayout.WEST,this);
        producir = new JButton("PRODUCIR");
        producir.setActionCommand("producir");
        add(producir);
        s.putConstraint(SpringLayout.NORTH,producir, 12, SpringLayout.SOUTH,tipo);
        s.putConstraint(SpringLayout.WEST, producir, 447, SpringLayout.WEST,this);
        setBackground(Color.RED);
    }
    
    public void conectarControlador(Controlador c){
        int i = 1;
        for(BCE2 b : eleccion.getBotones()){
            b.getBoton().setActionCommand("" + i++);
            b.getBoton().addActionListener(c);
        }
        producir.addActionListener(c);
    }
    
    /**
     * Método para rellenar las opciones de % de alcohol
     */
    public void llenarOpciones(ArrayList<String> porcentajes,ArrayList<String> tipos){
        for (String p : porcentajes) {
            alcohol.addItem(p);
        }
        for (String t : tipos) {
            tipo.addItem(t);
        }
        revalidate();
    }
}
