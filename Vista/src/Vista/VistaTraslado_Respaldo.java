/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.Controlador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

/**
 * 
 * @author García García José Ángel
 */
public class VistaTraslado_Respaldo extends JPanel{
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
    
    public VistaTraslado_Respaldo(){
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
        trailer1.setLocation(100, 0);
        escenario.add(trailer1);
        s.putConstraint(SpringLayout.NORTH, trailer1, 5, SpringLayout.NORTH, escenario);
        s.putConstraint(SpringLayout.WEST, trailer1, 20, SpringLayout.WEST, escenario);
        s.putConstraint(SpringLayout.EAST, trailer1, -500, SpringLayout.EAST, escenario);
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
        sp.putConstraint(SpringLayout.NORTH, consumidor1, 55, SpringLayout.NORTH, this);
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
        btnTransportar = new JButton("TRANSPORTAR");
        btnTransportar.setActionCommand("transportar");
        add(btnTransportar);
        sp.putConstraint(SpringLayout.NORTH, btnTransportar, 35, SpringLayout.SOUTH, consumidor4);
        sp.putConstraint(SpringLayout.WEST, btnTransportar, 15, SpringLayout.EAST, escenario);
        //sp.putConstraint(SpringLayout.EAST, consumidor4, -2, SpringLayout.EAST, this);
        //con esto te aseguras que se ejecute en el EDT
        SwingUtilities.invokeLater(() -> {
            System.out.println(trailer1.getLocation().x);    
        //trailer1.setLocation(trailer1.getLocation().x + 200, trailer1.getLocation().y);
        trailer1.repaint();
            System.out.println(trailer1.getLocation().x);    
        
        escenario.repaint();
        escenario.revalidate();
        
        });
        repaint();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
    }
    public void conectarControlador(Controlador c){
        btnTransportar.addActionListener(c);
    }
    
}
