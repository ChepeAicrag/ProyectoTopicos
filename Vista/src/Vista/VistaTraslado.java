package Vista;

import Componentes.BarraProceso;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author García García José Ángel
 */
public class VistaTraslado extends JPanel{
    
    
    public VistaTraslado(){
        setSize(1020, 680);
        setVisible(true);
        colocar();
        revalidate();
        
    }
    /**
     * Ponerle color a cada tanda para identificarlo
     * Son 12 estanqueles que tiene barriles (Bodegas)
     * 3 Tiendas compradoras
     */
    private void colocar(){
        add(new JLabel("EN BREVE...."),BorderLayout.CENTER);
        
    }
}
