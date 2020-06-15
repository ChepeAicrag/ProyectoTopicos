package Vista;

import Componentes.BarraProceso;


import java.awt.*;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Clase para la vista de producción de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class VistaProduccion extends JPanel{

    // Variable de instancia - Array de barras de proceso.
    public ArrayList<BarraProceso> barras;

    // Variable de instancia - Imagen de fondo.
    private Image imagenFondo = new ImageIcon(getClass().getResource("/Imagenes/Mezcal_desenfocado.png")).getImage();

    /**
     * Constructor de objetos de VistaProduccion.
     */
    public VistaProduccion(){
        barras = new ArrayList<>();
        setSize(1020, 680);
        setVisible(true);
        colocar();
        revalidate();
    }

    /**
     * Coloca los elementos al panel.
     */
    public void colocar(){
        BarraProceso barra1, barra2, barra3;
        ImageIcon imgCorte = new ImageIcon(getClass().getResource("/Imagenes/Cosecha.png")),
                  imgHorno = new ImageIcon(getClass().getResource("/Imagenes/Horneada.jpg")),
                  imgMolino = new ImageIcon(getClass().getResource("/Imagenes/Molienda.png")),
                  imgFermentador = new ImageIcon(getClass().getResource("/Imagenes/Fermentacion.png")),
                  imgDestilador = new ImageIcon(getClass().getResource("/Imagenes/Destilacion.png")),
                  imgEnbotellado = new ImageIcon(getClass().getResource("/Imagenes/EnvasadoEtiquetado.png"));
        int[] porcentajes = {0,0,0,0,0,0};
        
        ImageIcon[] imagenes = {imgCorte,imgHorno,imgMolino,imgFermentador,imgDestilador,imgEnbotellado};
        SpringLayout s = new SpringLayout();
        setLayout(s);
        barra1 = new BarraProceso(imagenes, porcentajes);
        Border border = BorderFactory.createEtchedBorder(5, Color.BLACK, Color.BLACK);
        barra1.setBorder(BorderFactory.createTitledBorder(
                border,"PALENQUE 1",TitledBorder.CENTER,TitledBorder.TOP,
                new Font("Helvetica",Font.BOLD,15),Color.WHITE));
        barra2 = new BarraProceso(imagenes, porcentajes);
        barra2.setBorder(BorderFactory.createTitledBorder(
                border,"PALENQUE 2",TitledBorder.CENTER,TitledBorder.TOP,
                new Font("Helvetica",Font.BOLD,15),Color.WHITE));
        barra3 = new BarraProceso(imagenes, porcentajes);
        barra3.setBorder(BorderFactory.createTitledBorder(
                border,"PALENQUE 3",TitledBorder.CENTER,TitledBorder.TOP,
                new Font("Helvetica",Font.BOLD,15),Color.WHITE));
        add(barra1);
        s.putConstraint(SpringLayout.NORTH, barra1,10, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, barra1,12, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, barra1,-12, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, barra1,-450, SpringLayout.SOUTH, this);
        add(barra2);
        s.putConstraint(SpringLayout.NORTH, barra2,220, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, barra2,12, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, barra2,-12, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, barra2,-230, SpringLayout.SOUTH, this);
        add(barra3);
        s.putConstraint(SpringLayout.NORTH, barra3,440, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, barra3,12, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, barra3,-12, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, barra3,-10, SpringLayout.SOUTH, this);
        barras.add(barra1);
        barras.add(barra2);
        barras.add(barra3);
        System.out.println("x:" + getHeight() + "y:" + getWidth());
    }

    /**
     * Retorna la barra de la posición dada.
     * @param i Posición de la barra, deber ser de 0 a 2
     * @return La barra de proceso en dicha posición.
     */
    public BarraProceso getBarra(int i){
        return barras.get(i);
    }

    /**
     * Pinta el fondo de la vista.
     * @param g Graphocs para pintar.
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
