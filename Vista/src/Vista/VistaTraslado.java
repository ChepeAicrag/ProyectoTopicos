package Vista;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

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
    
    private JPanel escenario;
    private JButton btnTransportar;
    private JLabel trailer1, trailer2, trailer3, consumidor1, consumidor2, consumidor3, consumidor4, consumidor5;
    private ImageIcon imgTrailer = new ImageIcon(getClass().getResource("/Imagenes/trailer.png")),
                      imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_carretera.jpg")),
                      imgConsumidor = new ImageIcon(getClass().getResource("/Imagenes/comprador.png"));
    private ImageIcon ICONO_TRAILER = new ImageIcon(imgTrailer.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)),
                      ICONO_CONSUMIDOR = new ImageIcon(imgConsumidor.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));;
    
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
        SpringLayout sp = new SpringLayout(), s = new SpringLayout();
        setLayout(sp);
        escenario = new JPanel(s);
        escenario.setBackground(Color.yellow);
        trailer1 = new JLabel("1",ICONO_TRAILER,JLabel.CENTER);
        trailer2 = new JLabel("2",ICONO_TRAILER,SwingUtilities.CENTER);
        trailer3 = new JLabel("3",ICONO_TRAILER,SwingUtilities.CENTER);
        escenario.add(trailer1);
        s.putConstraint(SpringLayout.NORTH, trailer1, 5, SpringLayout.NORTH, escenario);
        s.putConstraint(SpringLayout.WEST, trailer1, 20, SpringLayout.WEST, escenario);
        //s.putConstraint(SpringLayout.EAST, trailer1, -500, SpringLayout.EAST, escenario);
        escenario.add(trailer2);
        s.putConstraint(SpringLayout.NORTH, trailer2, 5, SpringLayout.SOUTH, trailer1);
        s.putConstraint(SpringLayout.WEST, trailer2, 20, SpringLayout.WEST, escenario);
        escenario.add(trailer3);
        s.putConstraint(SpringLayout.NORTH, trailer3, 5, SpringLayout.SOUTH, trailer2);
        s.putConstraint(SpringLayout.WEST, trailer3, 20, SpringLayout.WEST, escenario);
        consumidor1 = new JLabel("1", ICONO_CONSUMIDOR,JLabel.CENTER);
        consumidor2 = new JLabel("2",ICONO_CONSUMIDOR,JLabel.CENTER);
        consumidor3 = new JLabel("3",ICONO_CONSUMIDOR,JLabel.CENTER);
        consumidor4 = new JLabel("4",ICONO_CONSUMIDOR,JLabel.CENTER);
        consumidor5 = new JLabel("5",ICONO_CONSUMIDOR,JLabel.CENTER);
        add(escenario);
        sp.putConstraint(SpringLayout.NORTH, escenario, 12, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, escenario, 12, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.EAST, escenario, -150, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.SOUTH, escenario, -12, SpringLayout.SOUTH, this);
        add(consumidor1);
        sp.putConstraint(SpringLayout.NORTH, consumidor1, 15, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, consumidor1, 0, SpringLayout.EAST, escenario);
        sp.putConstraint(SpringLayout.EAST, consumidor1, -2, SpringLayout.EAST, this);
        add(consumidor2);
        sp.putConstraint(SpringLayout.NORTH, consumidor2, 25, SpringLayout.SOUTH, consumidor1);
        sp.putConstraint(SpringLayout.WEST, consumidor2, 0, SpringLayout.EAST, escenario);
        sp.putConstraint(SpringLayout.EAST, consumidor2, -2, SpringLayout.EAST, this);
        add(consumidor3);
        sp.putConstraint(SpringLayout.NORTH, consumidor3, 25, SpringLayout.SOUTH, consumidor2);
        sp.putConstraint(SpringLayout.WEST, consumidor3, 0, SpringLayout.EAST, escenario);
        sp.putConstraint(SpringLayout.EAST, consumidor3, -2, SpringLayout.EAST, this);
        add(consumidor4);
        sp.putConstraint(SpringLayout.NORTH, consumidor4, 25, SpringLayout.SOUTH, consumidor3);
        sp.putConstraint(SpringLayout.WEST, consumidor4, 0, SpringLayout.EAST, escenario);
        sp.putConstraint(SpringLayout.EAST, consumidor4, -2, SpringLayout.EAST, this);
        add(consumidor5);
        sp.putConstraint(SpringLayout.NORTH, consumidor5, 25, SpringLayout.SOUTH, consumidor4);
        sp.putConstraint(SpringLayout.WEST, consumidor5, 0, SpringLayout.EAST, escenario);
        sp.putConstraint(SpringLayout.EAST, consumidor5, -2, SpringLayout.EAST, this);
        trailer1.setLocation(trailer1.getLocation().x + 100, trailer1.getLocation().y);
        trailer1.repaint();
        escenario.repaint();
        repaint();
        
    }
    
    
}
