/*
 * Clase de una etiqueta con imagen redonda
 */

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
 * 
 * @author García García José Ángel
 */
public class EtiquetaRedonda extends JLabel{
    
    private Image imagen;
    private ImageIcon icono = new ImageIcon("Botella.jpg");
   
    public EtiquetaRedonda(String text){
        super(text);
        this.imagen = icono.getImage();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        int ancho = getWidth(),
            alto = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new Ellipse2D.Float(3, 3, ancho - 6, alto - 6));/** Limitamos la imagen a pintar, area de recorte */
        g2.drawImage(imagen  , 3, 3, ancho - 6, alto - 6, this); // Pinta la imagen en todo la etiqueta
        g2.dispose();
    }   
    
    @Override
    public void paintBorder(Graphics g){
        int ancho = getWidth(),
            alto = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2F));
        g2.setColor(Color.decode("#1057E8")); // Color del borde
        g2.drawOval(3, 3, ancho - 6, alto - 6); // Pintamos un circulo
        g2.dispose(); // Libera recursos en cada pintada
    }
    
    
    public Image getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        if (imagen == null) return;
        this.imagen = imagen.getImage();
    }
    
}
