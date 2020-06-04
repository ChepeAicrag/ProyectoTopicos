/*
 * Para la informacion final de las tandas
 * Quitar las FK de las tandas, ya que se repiten 
 */

package Vista;

import Controlador.Controlador;
import Modelo.ModeloTablaInforme;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * 
 * @author García García José Ángel
 */
public class VistaRegistroFinal extends JPanel{
    
    public ModeloTablaInforme mti;
    public JTable tabla;
    private JScrollPane scroll;
    private JLabel titulo;
    public JButton btnSalir; // Solo para cerrar la BD
    private DefaultTableCellRenderer dtcr;
    private JTableHeader hTab ;
    
    public VistaRegistroFinal(){
        setSize(1020, 680);
        setVisible(true);
        colocar();
        revalidate();
    }
    
    private void colocar(){
        SpringLayout s = new SpringLayout();
        setLayout(s);
        titulo = new JLabel("REGISTRO DE LAS TANDAS PRODUCIDAS");
        titulo.setAlignmentX(SwingUtilities.CENTER);
        add(titulo);
        s.putConstraint(SpringLayout.NORTH, titulo, 12, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, titulo,465, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, titulo, -470, SpringLayout.EAST, this);
        mti = new ModeloTablaInforme();
        tabla = new JTable(mti);
        dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.setDefaultRenderer(String.class, dtcr);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(60);
        
        tabla.getColumnModel().getColumn(7).setPreferredWidth(65);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(150);
        
        hTab = tabla.getTableHeader();
        hTab.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
        
        hTab.setResizingAllowed(false);
        hTab.setReorderingAllowed(false);
        hTab.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        hTab.setBackground(Color.BLACK);
        hTab.setForeground(Color.WHITE);
        add(hTab);
        //s.putConstraint(SpringLayout.NORTH, hTab, 12, SpringLayout.SOUTH, titulo);
        //s.putConstraint(SpringLayout.WEST, hTab, 300, SpringLayout.WEST, this);
        //s.putConstraint(SpringLayout.EAST, hTab, -310, SpringLayout.EAST, this);
        add(tabla);
        scroll = new JScrollPane();
        scroll.setViewportView(tabla);
        scroll.setBackground(Color.BLACK);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
         btnSalir = new JButton("SALIR");
        btnSalir.setActionCommand("salir");
        add(btnSalir);
        s.putConstraint(SpringLayout.NORTH, btnSalir, 120, SpringLayout.SOUTH, scroll);
        s.putConstraint(SpringLayout.WEST, btnSalir, 870, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, btnSalir, -30, SpringLayout.EAST, this);
        add(scroll);
        s.putConstraint(SpringLayout.NORTH, scroll, 12, SpringLayout.SOUTH, hTab);
        s.putConstraint(SpringLayout.WEST, scroll, 2, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, scroll, -2, SpringLayout.EAST, this);           
    }
    
    public void conectarControlador(Controlador c){
        btnSalir.addActionListener(c);
    }
}
