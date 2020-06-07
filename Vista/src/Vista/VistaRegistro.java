/**
 * Mostrará las tandas existentes
 * Una tabla con todos los registros almacenados en la BD
 * Simulará una bitacora de produciones a realizar
 */

package Vista;

import Controlador.Controlador;
import Modelo.ModeloTablaTandas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * 
 * @author García García José Ángel
 */
public class VistaRegistro extends JPanel{
    
    public ModeloTablaTandas mtt;
    public JTable tabla;
    private JScrollPane scroll;
    private JLabel titulo;
    public JButton btnEliminar, btnProducir, btnAtras;
    private DefaultTableCellRenderer dtcr;
    private JTableHeader hTab ;
    private ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/prod.jpg"));
    private Image imagenFondo = img.getImage();
    
    public VistaRegistro(){
        setSize(1020, 680);
        setVisible(true);
        colocar();
        revalidate();
    }
    
    public void colocar(){
        SpringLayout s = new SpringLayout();
        setLayout(s);
        titulo = new JLabel("REGISTRO DE TANDAS DISPONIBLES");
        titulo.setAlignmentX(SwingUtilities.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.blue);
        add(titulo);
        s.putConstraint(SpringLayout.NORTH, titulo, 32, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, titulo,300, SpringLayout.WEST, this);
        //s.putConstraint(SpringLayout.EAST, titulo, -300, SpringLayout.EAST, this);
        mtt = new ModeloTablaTandas();
        tabla = new JTable(mtt);
        dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.setDefaultRenderer(String.class, dtcr);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAlignmentX(SwingConstants.CENTER);
        //tabla.setBorder(BorderFactory.createEmptyBorder());
        hTab = tabla.getTableHeader();
        hTab.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
        hTab.setResizingAllowed(false);
        hTab.setReorderingAllowed(false);
        hTab.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        hTab.setBackground(Color.BLACK);
        hTab.setForeground(Color.WHITE);
        add(hTab);
        s.putConstraint(SpringLayout.NORTH, hTab, 22, SpringLayout.SOUTH, titulo);
        s.putConstraint(SpringLayout.WEST, hTab, 100, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, hTab, -110, SpringLayout.EAST, this);
        add(tabla);
        s.putConstraint(SpringLayout.NORTH, tabla, 0, SpringLayout.SOUTH, hTab);
        s.putConstraint(SpringLayout.WEST, tabla, 100, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, tabla, -110, SpringLayout.EAST, this);
        scroll = new JScrollPane();
        scroll.setViewportView(tabla);
        scroll.setBackground(Color.BLACK);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        btnAtras = new JButton("ATRAS");
        btnAtras.setActionCommand("atrasProducir");
        add(btnAtras);
        s.putConstraint(SpringLayout.NORTH, btnAtras, 600, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, btnAtras, 550, SpringLayout.WEST, this);
        btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setActionCommand("eliminar");
        btnEliminar.revalidate();
        add(btnEliminar);
        s.putConstraint(SpringLayout.NORTH, btnEliminar, 600, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, btnEliminar, 20, SpringLayout.EAST, btnAtras);
        //s.putConstraint(SpringLayout.EAST, btnEliminar, -100, SpringLayout.EAST, this);
        btnProducir = new JButton("PRODUCIR");
        btnProducir.setActionCommand("producir");
        add(btnProducir);
        s.putConstraint(SpringLayout.NORTH, btnProducir, 600, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, btnProducir, 20, SpringLayout.EAST, btnEliminar);
        //s.putConstraint(SpringLayout.EAST, btnProducir, -200, SpringLayout.EAST, this);
        add(scroll);
        s.putConstraint(SpringLayout.NORTH, scroll, 32, SpringLayout.SOUTH, titulo);
        s.putConstraint(SpringLayout.WEST, scroll, 150, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, scroll, -150, SpringLayout.EAST, this);
        
        
    }
    
    public void conectarControlador(Controlador c){
        btnAtras.addActionListener(c);
        btnEliminar.addActionListener(c);
        btnProducir.addActionListener(c);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
