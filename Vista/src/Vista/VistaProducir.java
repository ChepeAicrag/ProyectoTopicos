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
        setSize(1020, 680);
        setVisible(true);
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
        eleccion.setLayout(new GridLayout(4,4));
        eleccion.setBackground(Color.yellow);
        eleccion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(2), "Selecciona tu mezcal", TitledBorder.CENTER, TitledBorder.TOP));
        add(eleccion);
        s.putConstraint(SpringLayout.NORTH, eleccion, 5, SpringLayout.NORTH,this);
        s.putConstraint(SpringLayout.WEST, eleccion, 50, SpringLayout.WEST,this);
        s.putConstraint(SpringLayout.EAST, eleccion, -50, SpringLayout.EAST,this);
        s.putConstraint(SpringLayout.SOUTH, eleccion, -200, SpringLayout.SOUTH,this);
        etqAlcohol = new JLabel("Selecciona el grado de alcohol");
        add(etqAlcohol);
        s.putConstraint(SpringLayout.NORTH,etqAlcohol, 10, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, etqAlcohol, 405, SpringLayout.WEST,this);
        alcohol = new JComboBox<>();
        add(alcohol);
        s.putConstraint(SpringLayout.NORTH,alcohol, 15, SpringLayout.SOUTH,etqAlcohol);
        s.putConstraint(SpringLayout.WEST, alcohol, 465, SpringLayout.WEST,this);
        etqTipo = new JLabel("Selecciona el tipo de mezcal");
        add(etqTipo);
        s.putConstraint(SpringLayout.NORTH,etqTipo, 12, SpringLayout.SOUTH,alcohol);
        s.putConstraint(SpringLayout.WEST, etqTipo, 405, SpringLayout.WEST,this);
        tipo = new JComboBox<>();
        add(tipo);
        s.putConstraint(SpringLayout.NORTH,tipo, 12, SpringLayout.SOUTH,etqTipo);
        s.putConstraint(SpringLayout.WEST, tipo, 460, SpringLayout.WEST,this);
        producir = new JButton("REGISTRAR");
        producir.setActionCommand("registrar");
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
     * Método para rellenar las opciones de % de alcohol y tipo de mezcal
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
}
