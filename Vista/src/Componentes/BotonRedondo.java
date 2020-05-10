package Componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * @author García García José Ángel
 */
public class BotonRedondo extends JButton{
    private Image image = null;
    private ImageIcon icono
            = new ImageIcon("Botella.jpg");
    private String txt;
    private Color txtColor = Color.black;
    private int espacio = 30;
    /**
     * Constructor de clase
     */
    public BotonRedondo() {
        super();
        this.setContentAreaFilled(false); // Evitar pintar el fondo original
        this.setBorderPainted(false); // Sin bordes pintados
        this.image = ((ImageIcon) icono).getImage();
        this.repaint();
        txt = "";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int ancho = getWidth()
           ,alto = getHeight();
        if(getModel().isArmed())
             g.setColor(Color.decode("#191919")); 
         else
             g.setColor(Color.decode("#81d8d0"));
        // Sombreamos un poco para ver el clic
        g.fillOval( 1,1,ancho-espacio+4,alto-espacio+4);
        Graphics2D g2 = (Graphics2D) g.create();
        /** Algoritmos de presentación */
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING
                            ,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.decode("#2A5A82"));// Color del fondo
        g2.fill(new Ellipse2D.Double(3, 3, ancho - espacio, alto - espacio)); // Forma del boton
        /*
        Color y dibujo del contorno, no necesario si tenemos el paintBorder()
        g2.setColor(Color.decode("#8312FF"));
        g2.drawOval(0, 0, ancho - 6, alto - 6);
        */
        drawString(g2);
        g2.setClip(new Ellipse2D.Float(3, 3, ancho - espacio, alto - espacio));/** Limitamos la imagen a pintar, area de recorte */
        g2.drawImage(image, 3, 3, ancho - espacio, alto - espacio, this); // Pinta la imagen en todo el boton
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        int ancho = getWidth()
            ,alto = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING
                , RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f)); // Grueso del contorno
        g2.setColor(Color.decode("#1162ac")); // Color del borde
        g2.drawOval(3, 3, ancho - espacio, alto - espacio); // Pintamos un circulo
        g2.dispose();
    }
    
    protected void drawString(Graphics2D g){
       float quit = this.txt.length() * 4;
       g.setColor(txtColor);
       g.drawString(this.txt,getWidth() / 2 - quit,getHeight()-2);
    }
    
    /**
     * Método para colocar imagen al bóton, no requiere escalada, se adapta por 
     * completo al bóton
     * @param icono Imagen a colocar
     */
    public void setImagen(ImageIcon icono) {
        if(icono == null) return;
        this.image = ((ImageIcon) icono).getImage();
    }
    /**
     * Método que te retorna el icono que tiene el bóton
     * @return Imagen que tiene elboton
     */
    public Image getImagen() {
        return this.image;
    }
    
    @Override
    public void setText(String texto){
        this.txt = (texto != null ) ? texto : "null";
    }
    
    public void setColorText(Color color){
        this.txtColor = (color != null) ? color : Color.decode("#2a6478");
    }
}
