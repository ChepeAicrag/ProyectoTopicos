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
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        colocar();
        revalidate();
        
    }
    
    private void colocar(){
        add(new JLabel("EN BREVE...."),BorderLayout.CENTER);
        
    }
}
