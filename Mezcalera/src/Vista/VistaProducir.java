package Vista;

import Componentes.BCE;
import Componentes.BarraEleccion;
import Controlador.Controlador;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author García García José Ángel
 */
public class VistaProducir extends JPanel{
    
    private BarraEleccion eleccion;
    private JButton btnRegistrar;
    public JComboBox<String> alcohol, tipo;
    private JLabel etqAlcohol, etqTipo;
    private ImageIcon[] imagenes;
    private String[] textos;
    private ImageIcon imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_0.jpg")),
                  icon = new ImageIcon(imgFond.getImage().getScaledInstance(1020, 680, Image.SCALE_DEFAULT));
    private JLabel fondo = new JLabel(icon);
    private SpringLayout s = new SpringLayout();

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
    
    public void conectarControlador(Controlador c){
        int i = 1;
        for(BCE b : eleccion.getBotones()){
            b.getBoton().setActionCommand("" + i++);
            b.getBoton().addActionListener(c);
        }
        btnRegistrar.addActionListener(c);
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

    @Override
    public void paint(Graphics g) {
        g.drawImage(imgFond.getImage(), 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }

}
