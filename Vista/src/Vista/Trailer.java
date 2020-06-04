/*
 * 
 */

package Vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * @author García García José Ángel
 */
public class Trailer extends JLabel{
    
    /** La Imagen */
    private ImageIcon imgTrailer = new ImageIcon(getClass().getResource("/Imagenes/trailer.png")),
            imgFond = new ImageIcon(getClass().getResource("/Imagenes/fondo_carretera.jpg"));
    private ImageIcon ICONO_TRAILER = new ImageIcon(imgTrailer.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    

    /**
     * Devuelve como tamaño preferido el de la foto.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ICONO_TRAILER.getIconWidth(), ICONO_TRAILER.getIconHeight());
    }

    
    public Trailer() {
        
    }

    /**
     * Cuanto queremos que se rote la foto, en radianes.
     */
    private double rotacion = 0.0;

    /**
     * Dibujo de la foto rotandola.
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // AffineTransform realiza el giro, usando como eje de giro el centro
        // de la foto (width/2, height/2) y el angulo que indica el atributo
        // rotacion.
        AffineTransform tx = AffineTransform.getRotateInstance(rotacion, 
                this.getWidth()/2, this.getHeight()/2);
        
        // dibujado con la AffineTransform de rotacion
        g2d.drawImage(ICONO_TRAILER.getImage(), tx, this);
    }

    /**
     * Devuelve la rotacion actual.
     * @return rotacion en radianes
     */
    public double getRotacion() {
        return rotacion;
    }

    /**
     * Se le pasa la rotación deseada.
     * @param rotacion La rotacion en radianes.
     */
    public void setRotacion(double rotacion) {
        this.rotacion = rotacion;
    }
    
    public void rotar(double val){
        setRotacion(val);
        repaint();
    }
}
