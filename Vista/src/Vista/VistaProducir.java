package Vista;

import Componentes.BCE2;
import Componentes.BarraEleccion;
import Controlador.Controlador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
    private ImageIcon imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_0.jpg")),
                  icon = new ImageIcon(imgFond.getImage().getScaledInstance(1020, 680, Image.SCALE_DEFAULT));
    private JLabel fondo = new JLabel(icon);
        
    public VistaProducir(ImageIcon[] imagenes,String[] textos){
        setSize(250, 700);
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
        //eleccion.setBackground(Color.yellow);
        eleccion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(5), "SELECCIONA EL MAGUEY", TitledBorder.CENTER, TitledBorder.TOP));
        eleccion.setFondoImagen(imgFond);
        add(eleccion);
        s.putConstraint(SpringLayout.NORTH, eleccion, 5, SpringLayout.NORTH,this);
        s.putConstraint(SpringLayout.WEST, eleccion, 80, SpringLayout.WEST,this);
        s.putConstraint(SpringLayout.EAST, eleccion, -80, SpringLayout.EAST,this);
        s.putConstraint(SpringLayout.SOUTH, eleccion, -80, SpringLayout.SOUTH,this);
        etqAlcohol = new JLabel("SELECCIONA EL GRADO DE ALCOHOL");
        etqAlcohol.setFont(new Font("Helvetica", Font.BOLD, 14));
        etqAlcohol.setForeground(Color.WHITE);
        add(etqAlcohol);
        s.putConstraint(SpringLayout.NORTH,etqAlcohol, 10, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, etqAlcohol, 140, SpringLayout.WEST,this);
        alcohol = new JComboBox<>();
        add(alcohol);
        s.putConstraint(SpringLayout.NORTH,alcohol, 15, SpringLayout.SOUTH,etqAlcohol);
        s.putConstraint(SpringLayout.WEST, alcohol, 195, SpringLayout.WEST,this);
        etqTipo = new JLabel("SELECCIONA EL TIPO DE MEZCAL");
        etqTipo.setFont(new Font("Helvetica", Font.BOLD, 14));
        etqTipo.setForeground(Color.WHITE);
        add(etqTipo);
        s.putConstraint(SpringLayout.NORTH,etqTipo, 10, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, etqTipo, 60, SpringLayout.EAST,etqAlcohol);
        tipo = new JComboBox<>();
        add(tipo);
        s.putConstraint(SpringLayout.NORTH,tipo, 15, SpringLayout.SOUTH,etqTipo);
        s.putConstraint(SpringLayout.WEST, tipo, 150, SpringLayout.EAST,etqAlcohol);
        producir = new JButton();
        //producir.setBackground(Color.decode("#eca0b6"));
            //btn.setForeground(Color.WHITE);
            //btn.setFont(new Font("Sylfaen", Font.BOLD, 13));
            //btn.setBorder(BorderFactory.createEtchedBorder(Color.decode("#ffc8bd"), Color.decode("#f3d1f4")));
        producir.setFont(new Font("Sylfaen", Font.BOLD, 16));
        producir.setBorderPainted(true);
        producir.setActionCommand("registrar");
        producir.setIcon(new ImageIcon(new ImageIcon(
                getClass().getResource("/Imagenes/btnRegistrar.png"))
                .getImage().getScaledInstance(120,60, Image.SCALE_DEFAULT)));
        //producir.setSize(100, 30);
        producir.setPreferredSize(new Dimension(100, 30));
        add(producir);
        s.putConstraint(SpringLayout.NORTH,producir, 40, SpringLayout.SOUTH,eleccion);
        s.putConstraint(SpringLayout.WEST, producir, 370, SpringLayout.WEST,this);
        add(fondo);
        
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
