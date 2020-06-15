package Componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Clase de un componente que tiene una etiqueta circular y una barra de proceso.
 * @author Garcíaa García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class EtiquetaRedonda extends JLabel{

    // Variable de instancia - Imagen de la etiqueta.
    private Image imagen = new ImageIcon("Botella.jpg").getImage();

    /**
     * Constructor para objetos de EtiquetaRedonda.
     *
     * @param text Texto que tendrá la etiqueta.
     */
    public EtiquetaRedonda(String text){
        super(text);
        this.repaint();
    }

    /**
     * Método que te permite pintar el componente en forma de circulo.
     *
     * @param g Graphics para pintar.
     */
    @Override
    public void paintComponent(Graphics g){
        int ancho = getWidth(), alto = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new Ellipse2D.Float(3, 3, ancho - 6, alto - 6));
        g2.drawImage(imagen  , 3, 3, ancho - 6, alto - 6, this);
        g2.dispose();
    }

    /**
     * Método que te permite pintar el borde del componente.
     *
     * @param g Graphics para pintar.
     */
    @Override
    public void paintBorder(Graphics g){
        int ancho = getWidth(), alto = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2F));
        g2.setColor(Color.decode("#1057E8")); // Color del borde
        g2.drawOval(3, 3, ancho - 6, alto - 6); // Pintamos un circulo
        g2.dispose(); // Libera recursos en cada pintada
    }
    
    /**
     * Retorna la imágen actual de la etiqueta.
     *
     * @return imágen que tiene la etiqueta.
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Establece la imágen que tendrá la etiqueta.
     *
     * @param imagen Imágen a establecer.
     */
    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen.getImage();
    }
    
}
