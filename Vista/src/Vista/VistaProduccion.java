/*
 * Vista de la parte de producción
 *
 */

package Vista;

import Componentes.BarraProceso;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author García García José Ángel
 */
public class VistaProduccion extends JPanel{
    
    public BarraProceso barra1,barra2,barra3;
    
    public VistaProduccion(){
        setSize(1020, 680);
        setVisible(true);
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        colocar();
        revalidate();
    }
    
    public void colocar(){
            /** Creaer las imagenes de cada procesos */
        ImageIcon img = new ImageIcon(
                getClass().getResource("/TestVistas/maguey.png"));
        int[] porcentajes = {0,0,0,0,0,0};
        
        ImageIcon[] imagenes = {img,img,img,img,img,img};
        SpringLayout s = new SpringLayout();
        setLayout(s);
        barra1 = new BarraProceso(imagenes, porcentajes);
        Border border = BorderFactory.createEtchedBorder(5, Color.BLACK, Color.BLACK);
        barra1.setBorder(BorderFactory.createTitledBorder(
                border,"Palenque 1",TitledBorder.CENTER,TitledBorder.TOP));
        barra2 = new BarraProceso(imagenes, porcentajes);
        barra2.setBorder(BorderFactory.createTitledBorder(
                border,"Palenque 2",TitledBorder.CENTER,TitledBorder.TOP));
        barra3 = new BarraProceso(imagenes, porcentajes);
        barra3.setBorder(BorderFactory.createTitledBorder(
                border,"Palenque 3",TitledBorder.CENTER,TitledBorder.TOP));
        add(barra1);
        s.putConstraint(SpringLayout.NORTH, barra1,5, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, barra1,12, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, barra1,-12, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, barra1,-445, SpringLayout.SOUTH, this);
        add(barra2);
        s.putConstraint(SpringLayout.NORTH, barra2,15, SpringLayout.SOUTH, barra1);
        s.putConstraint(SpringLayout.WEST, barra2,12, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, barra2,-12, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, barra2,-240, SpringLayout.SOUTH, this);
        add(barra3);
        s.putConstraint(SpringLayout.NORTH, barra3,15, SpringLayout.SOUTH, barra2);
        s.putConstraint(SpringLayout.WEST, barra3,12, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, barra3,-12, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, barra3,-30, SpringLayout.SOUTH, this);
    }
}
