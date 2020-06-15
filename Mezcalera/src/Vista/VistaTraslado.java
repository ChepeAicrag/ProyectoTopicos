package Vista;

import Controlador.Controlador;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Clase para vista de traslador de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class VistaTraslado extends JPanel {

    // Variable de instancia - Panel para el escenario.
    private JPanel escenario;

    // Variable de instancia - Botón para transportar.
    private JButton btnTransportar;

    // Variable de instancia - Transportadores.
    public Trailer trailer1, trailer2, trailer3;

    // Variable de instancia - Consumidores.
    private JLabel consumidor1, consumidor2, consumidor3, consumidor4, consumidor5;

    // Variables de instancia - Imagenes de transportadores, consumidores y fondo.
    private ImageIcon imgTrailer = new ImageIcon(getClass().getResource("/Imagenes/trailer.png")),
            imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_carretera.jpg")),
            imgConsumidor = new ImageIcon(getClass().getResource("/Imagenes/comprador.png"));
    private ImageIcon ICONO_TRAILER = new ImageIcon(imgTrailer.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)),
            ICONO_CONSUMIDOR = new ImageIcon(imgConsumidor.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
            ICONO_FONDO = new ImageIcon(imgFond.getImage().getScaledInstance(1020, 680, Image.SCALE_DEFAULT));

    /**
     * Constructor de objetos VistaTraslado.
     */
    public VistaTraslado() {
        setSize(1020, 680);
        setVisible(true);
        colocar();
        revalidate();
    }

    /**
     * Coloca los elementos en el panel.
     */
    private void colocar() {
        int y_Trailer = -18, consumer_posX = 850, y_Consumidor = 10;
        setLayout(null);
        trailer1 = new Trailer();
        trailer1.setLocation(10, y_Trailer);
        trailer1.setSize(200, 150);
        add(trailer1);
        trailer2 = new Trailer();
        trailer2.setLocation(10, y_Trailer + 200);
        trailer2.setSize(200, 200);
        add(trailer2);
        trailer3 = new Trailer();
        trailer3.setLocation(10, y_Trailer + 400);
        trailer3.setSize(200, 200);
        add(trailer3);
        consumidor1 = new JLabel("1", ICONO_CONSUMIDOR, SwingUtilities.CENTER);
        consumidor1.setLocation(consumer_posX, y_Consumidor);
        consumidor1.setSize(120, 125);
        add(consumidor1);
        consumidor2 = new JLabel("2", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor2.setLocation(consumer_posX, y_Consumidor + 110);
        consumidor2.setSize(120, 125);
        add(consumidor2);
        consumidor3 = new JLabel("3", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor3.setLocation(consumer_posX, y_Consumidor + 220);
        consumidor3.setSize(120, 125);
        add(consumidor3);
        consumidor4 = new JLabel("4", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor4.setLocation(consumer_posX, y_Consumidor + 330);
        consumidor4.setSize(120, 135);
        add(consumidor4);
        consumidor5 = new JLabel("5", ICONO_CONSUMIDOR, JLabel.CENTER);
        consumidor5.setLocation(consumer_posX, y_Consumidor + 445);
        consumidor5.setSize(120, 130);
        add(consumidor5);
        btnTransportar = new JButton("TRANSPORTAR");
        btnTransportar.setActionCommand("transportar");
        btnTransportar.setSize(140, 30);
        btnTransportar.setLocation(450, 590);
        add(btnTransportar);
        JLabel fondo = new JLabel(ICONO_FONDO);
        fondo.setSize(1020, 680);
        add(fondo);

    }

    /**
     * Establece el nombre de los clientes.
     *
     * @param nombreClientes Array con los nombres de los clientes.
     */
    public void llenarClientes(ArrayList<String> nombreClientes) {
        escribirVertical(consumidor1, nombreClientes.get(0));
        escribirVertical(consumidor2, nombreClientes.get(1));
        escribirVertical(consumidor3, nombreClientes.get(2));
        escribirVertical(consumidor4, nombreClientes.get(3));
        escribirVertical(consumidor5, nombreClientes.get(4));
    }

    /**
     * Escribe en vertical el texto en un JLabel.
     *
     * @param etq Etiqueta en la que se escribe.
     * @param texto Texto que se escribe.
     */
    private void escribirVertical(JLabel etq, String texto){
        String escribir = "<html>";
        for (char a : texto.toCharArray())
            escribir += String.format("<p> %c</p>",a);
        escribir += "</html>";
        etq.setText(escribir.toUpperCase());
        etq.setForeground(Color.WHITE);
    }

    /**
     * Rota la imágen de un trailer.
     *
     * @param t Trailera a rotar.
     * @param val Valor de la rotación en radianes.
     */
    public void rotar(Trailer t, double val){
        t.rotar(val);
    }

    /**
     * Conecta con el controlador.
     *
     * @param c Controlador a utilizar.
     */
    public void conectarControlador(Controlador c) {
        btnTransportar.addActionListener(c);
    }

    /**
     * Mueve el trailer de acuerdo a la indicación.
     *
     * @param trailer Trailer a mover.
     * @param op Opción de movimiento, true para mover en horizontal y false
     *           para mover en vertical.
     */
    public void mover(Trailer trailer, boolean op) {
        if (op) 
        trailer.setLocation(trailer.getLocation().x + 20, trailer.getLocation().y);
        else
        trailer.setLocation(trailer.getLocation().x, trailer.getLocation().y + 10);
        invalidate();
    }

    /**
     * Mueve el trailer de abajo hacia arriba.
     *
     * @param trailer Trailer a mover.
     */
    public void moverArriba(Trailer trailer){
        trailer.setLocation(trailer.getLocation().x, trailer.getLocation().y - 10);
        invalidate();
    }
}
