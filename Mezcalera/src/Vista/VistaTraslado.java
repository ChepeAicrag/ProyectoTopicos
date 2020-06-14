package Vista;

import Controlador.Controlador;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author García García José Ángel
 */
public class VistaTraslado extends JPanel {

    private JProgressBar barrilesAnejo55, barrilesAnejo48, barrilesAnejo37,
            barrilesJoven55, barrilesJoven48, barrilesJoven37,
            barrilesReposado55, barrilesReposado48, barrilesReposado37,
            barrilesMaduro55, barrilesMaduro48, barrilesMaduro37;

    private JPanel escenario;
    private JButton btnTransportar;
    public Trailer trailer1, trailer2, trailer3;
    private JLabel consumidor1, consumidor2, consumidor3, consumidor4, consumidor5;
    private ImageIcon imgTrailer = new ImageIcon(getClass().getResource("/Imagenes/trailer.png")),
            imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_carretera.jpg")),
            imgConsumidor = new ImageIcon(getClass().getResource("/Imagenes/comprador.png"));
    private ImageIcon ICONO_TRAILER = new ImageIcon(imgTrailer.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)),
            ICONO_CONSUMIDOR = new ImageIcon(imgConsumidor.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
            ICONO_FONDO = new ImageIcon(imgFond.getImage().getScaledInstance(1020, 680, Image.SCALE_DEFAULT));
            
    private int i = 0;

    public VistaTraslado() {
        setSize(1020, 680);
        setVisible(true);
        colocar();
        revalidate();
    }

    private void colocar() {
        //SpringLayout sp = new SpringLayout(), s = new SpringLayout();
        //escenario = new JPanel(s);
        int y_Trailer = -18, consumer_posX = 850, y_Consumidor = 10;
        setLayout(null);
        trailer1 = new Trailer();
        trailer1.setLocation(10, y_Trailer);
        trailer1.setSize(200, 150);
        trailer1.setOpaque(true);
        add(trailer1);
        trailer2 = new Trailer();
        trailer2.setLocation(10, y_Trailer + 200);
        trailer2.setSize(200, 200);
        trailer2.setOpaque(true);
        add(trailer2);
        trailer3 = new Trailer();
        trailer3.setLocation(10, y_Trailer + 400);
        trailer3.setSize(200, 200);
        trailer3.setOpaque(true);
        add(trailer3);
        consumidor1 = new JLabel("1", ICONO_CONSUMIDOR, SwingUtilities.CENTER);
        consumidor1.setLocation(consumer_posX, y_Consumidor);
        consumidor1.setSize(120, 125);
        consumidor1.setOpaque(true);
        add(consumidor1);
        consumidor2 = new JLabel("2", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor2.setLocation(consumer_posX, y_Consumidor + 110);
        consumidor2.setSize(120, 125);
        consumidor2.setOpaque(true);
        add(consumidor2);
        consumidor3 = new JLabel("3", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor3.setLocation(consumer_posX, y_Consumidor + 220);
        consumidor3.setSize(120, 125);
        consumidor3.setOpaque(true);
        add(consumidor3);
        consumidor4 = new JLabel("4", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor4.setLocation(consumer_posX, y_Consumidor + 330);
        consumidor4.setSize(120, 135);
        consumidor4.setOpaque(true);
        add(consumidor4);
        consumidor5 = new JLabel("5", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor5.setLocation(consumer_posX, y_Consumidor + 445);
        consumidor5.setSize(120, 130);
        consumidor5.setOpaque(true);
        add(consumidor5);
        btnTransportar = new JButton("TRANSPORTAR");
        btnTransportar.setActionCommand("transportar");
        btnTransportar.setSize(140, 30);
        btnTransportar.setLocation(420, 590);
        add(btnTransportar);
        JLabel fondo = new JLabel(ICONO_FONDO);
        fondo.setSize(1020, 680);
        add(fondo);

    }

    /** Rellenar el texto de los clientes con los de la base de datos*/
    public void llenarClientes(ArrayList<String> nombreClientes) {
        escribirVertical(consumidor1, nombreClientes.get(0));
        escribirVertical(consumidor2, nombreClientes.get(1));
        escribirVertical(consumidor3, nombreClientes.get(2));
        escribirVertical(consumidor4, nombreClientes.get(3));
        escribirVertical(consumidor5, nombreClientes.get(4));
    }

    private void escribirVertical(JLabel etq, String texto){
        String escribir = "<html>";
        for (char a : texto.toCharArray())
            escribir += String.format("<p>%c</p>",a);
        etq.setText(escribir += "</html>");
    }

    public void rotar(Trailer t, double val){
        t.rotar(val);
    }
    public void conectarControlador(Controlador c) {
        btnTransportar.addActionListener(c);
    }

    public void mover(Trailer trailer, boolean op) {
        if (op) 
        trailer.setLocation(trailer.getLocation().x + 20, trailer.getLocation().y);
        else
        trailer.setLocation(trailer.getLocation().x, trailer.getLocation().y + 10);
        invalidate();
    }
    
    public void moverArriba(Trailer trailer){
        trailer.setLocation(trailer.getLocation().x, trailer.getLocation().y - 10);
        invalidate();
    }
    
}