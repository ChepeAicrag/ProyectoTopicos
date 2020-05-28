package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author García García José Ángel
 */
public class VistaTraslado extends JPanel{
    
    /**
     * Tenemos 12 tipos de barriles 
     * 
     */
    
    private JProgressBar barrilesAnejo55, barrilesAnejo48, barrilesAnejo37,
                         barrilesJoven55, barrilesJoven48, barrilesJoven37,
                         barrilesReposado55, barrilesReposado48, barrilesReposado37,
                         barrilesMaduro55, barrilesMaduro48, barrilesMaduro37;
    private JPanel panelBarras;
    
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
        setLayout(new BorderLayout());
        add(new JLabel("EN BREVE...."),BorderLayout.NORTH);
        
    }
}
