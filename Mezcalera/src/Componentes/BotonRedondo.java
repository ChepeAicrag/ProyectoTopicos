package Componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Clase de un componente que tiene un bóton redondo y una etiqueta abajo.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class BotonRedondo extends JButton{

    // Variable de instancia - Icóno de fondo por default que tiene.
    private Image image = new ImageIcon("Botella.jpg").getImage();
    
    // Variable de instancia - Texto que tendrá su etiqueta.
    private String txt;
    
    // Variable de instancia - Color del texto de la etiqueta.
    private Color txtColor = Color.black;
    
    // Constante de clase - Espacio entre el boton y el panel.
    private final int ESPACIO = 30;

    /**
     * Constructor para objetosd e BotonRedondo
     */
    public BotonRedondo() {
        super();
        this.setContentAreaFilled(false); // Evitar pintar el fondo original
        this.setBorderPainted(false); // Sin bordes pintados
        this.repaint();
        txt = "";
    }

    /**
     * Método que te permite pintar el componente en forma de circulo.
     * @param g Graphics para pintar.
     */
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
        g.fillOval( 1,1,ancho - ESPACIO + 4,alto - ESPACIO + 4);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING
                            ,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.decode("#2A5A82"));// Color del fondo
        g2.fill(new Ellipse2D.Double(3, 3, ancho - ESPACIO, alto - ESPACIO)); // Forma del boton
        /*
        Color y dibujo del contorno, no necesario si tenemos el paintBorder()
        g2.setColor(Color.decode("#8312FF"));
        g2.drawOval(0, 0, ancho - 6, alto - 6);
        */
        drawString(g2);
        /** Limitamos la imagen a pintar, area de recorte */
        g2.setClip(new Ellipse2D.Float(3, 3, ancho - ESPACIO, alto - ESPACIO));
        /** Pinta la imagen en todo el boton */
        g2.drawImage(image, 3, 3, ancho - ESPACIO, alto - ESPACIO, this);
        g2.dispose();
    }

    /**
     * Método para pintar el borde del círculo.
     * @param g Graphics para pintar.
     */
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
        g2.drawOval(3, 3, ancho - ESPACIO, alto - ESPACIO); // Pintamos un circulo
        g2.dispose();
    }

    /**
     * Método para pintar el texto dado.
     */
    protected void drawString(Graphics2D g){
       float quit = this.txt.length() * 4;
       g.setColor(txtColor);
       g.drawString(this.txt,getWidth() / 2 - quit,getHeight() - 2);
    }
    
    /**
     * Método para colocar imagen al bóton, no requiere escalada, se adapta por 
     * completo al bóton.
     *
     * @param icono Imagen a colocar no escalada.
     */
    public void setImagen(ImageIcon icono) {
        if(icono == null) return;
        this.image = ((ImageIcon) icono).getImage();
    }

    /**
     * Método que te retorna el icono que tiene el bóton.
     *
     *  @return imágen que tiene el boton.
     */
    public Image getImagen() {
        return this.image;
    }

    /**
     * Establece el texto a pintar.
     *
     * @param texto Texto que tendrá el bóton.
     */
    @Override
    public void setText(String texto){
        this.txt = (texto != null ) ? texto : "null";
    }

    /**
     * Establece el color del texto que tiene el bóton.
     *
     * @param color Color que tendrá el texto.
     */
    public void setColorText(Color color){
        this.txtColor = (color != null) ? color : Color.decode("#2a6478");
    }
}
