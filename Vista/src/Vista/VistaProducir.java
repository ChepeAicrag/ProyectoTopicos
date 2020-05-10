package Vista;

import Componentes.BarraEleccion;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author García García José Ángel
 */
public class VistaProducir extends JFrame{
    
    private BarraEleccion eleccion;
    private JButton producir;
    private JComboBox<String> alcohol, tipo;
    private JLabel etqAlcohol, etqTipo;
    private ImageIcon[] imagenes;
    private String[] textos;
    
    public VistaProducir(ImageIcon[] imagenes,String[] textos){
        setTitle("Vista Producir");
        setSize(1020, 680);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.imagenes = imagenes;
        this.textos = textos;
        colocar();
        revalidate();
    }
    /* Error al posicion, si se crea bien */
    public void colocar(){
        SpringLayout s = new SpringLayout();
        JPanel p = new JPanel(s);
        eleccion = new BarraEleccion(imagenes,textos);
        eleccion.setLayout(new GridLayout(2,4));
        eleccion.setBackground(Color.yellow);
        eleccion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(2), "Selecciona tu mezcal", TitledBorder.CENTER, TitledBorder.TOP));
        p.add(eleccion);
        s.putConstraint(SpringLayout.NORTH, eleccion, 5, SpringLayout.NORTH,p);
        s.putConstraint(SpringLayout.WEST, eleccion, 12, SpringLayout.WEST,p);
        s.putConstraint(SpringLayout.EAST, eleccion, -12, SpringLayout.EAST,p);
        s.putConstraint(SpringLayout.SOUTH, eleccion, -180, SpringLayout.SOUTH,p);
        etqAlcohol = new JLabel("Selecciona el grado de alcohol");
        p.add(etqAlcohol);
        s.putConstraint(SpringLayout.NORTH,etqAlcohol, 10, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, etqAlcohol, 405, SpringLayout.WEST,p);
        alcohol = new JComboBox<>(new String[] {" 55% "," 45% "," 38% "});
        p.add(alcohol);
        s.putConstraint(SpringLayout.NORTH,alcohol, 15, SpringLayout.SOUTH,etqAlcohol);
        s.putConstraint(SpringLayout.WEST, alcohol, 465, SpringLayout.WEST,p);
        etqTipo = new JLabel("Selecciona el tipo de mezcal");
        p.add(etqTipo);
        s.putConstraint(SpringLayout.NORTH,etqTipo, 12, SpringLayout.SOUTH,alcohol);
        s.putConstraint(SpringLayout.WEST, etqTipo, 405, SpringLayout.WEST,p);
        tipo = new JComboBox<>(new String[] {"Añejo","Blanco","Madurado","Reposado"});
        p.add(tipo);
        s.putConstraint(SpringLayout.NORTH,tipo, 12, SpringLayout.SOUTH,etqTipo);
        s.putConstraint(SpringLayout.WEST, tipo, 460, SpringLayout.WEST,p);
        producir = new JButton("PRODUCIR");
        p.add(producir);
        s.putConstraint(SpringLayout.NORTH,producir, 12, SpringLayout.SOUTH,tipo);
        s.putConstraint(SpringLayout.WEST, producir, 447, SpringLayout.WEST,p);
        add(p);
    }
}
