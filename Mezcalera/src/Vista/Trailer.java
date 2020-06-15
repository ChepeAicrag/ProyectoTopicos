package Vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Clase para representar al trailer que se utiliza para transportar la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Trailer extends JLabel{
    
    // Variables de instancia - Imagen del trailer y del fondo de la vista.
    private ImageIcon imgTrailer = new ImageIcon(getClass().getResource("/Imagenes/trailer.png")),
            imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_carretera.jpg"));
    private ImageIcon ICONO_TRAILER = new ImageIcon(imgTrailer.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

    // Variable de instancia - Valor rotación en radianes.
    private double rotacion = 0.0;

    /**
     * Retorna una dimensión preferible de la imágen.
     * @return Dimensión correcta de la imágen.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ICONO_TRAILER.getIconWidth(), ICONO_TRAILER.getIconHeight());
    }

    /**
     * Constructor para objetos de Trailer.
     */
    public Trailer() {
        
    }

    /**
     * Dibujo de la foto rotandola.
     *
     * @param g Graphics para pintar.
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // AffineTransform realiza el giro, usando como eje de giro el centro
        // de la foto (width/2, height/2) y el angulo que indica el atributo
        // rotacion.
        AffineTransform tx = AffineTransform.getRotateInstance(rotacion, this.getWidth()/2, this.getHeight()/2);
        g2d.drawImage(ICONO_TRAILER.getImage(), tx, this);
    }

    /**
     * Devuelve la rotacion actual.
     *
     * @return Rotacion en radianes
     */
    public double getRotacion() {
        return rotacion;
    }

    /**
     * Establece la rotación deseada.
     *
     * @param rotacion La rotacion en radianes.
     */
    public void setRotacion(double rotacion) {
        this.rotacion = rotacion;
    }

    /**
     * Realiza la rotación de la imágen.
     *
     * @param val Valor de rotación.
     */
    public void rotar(double val){
        setRotacion(val);
        repaint();
    }
}
