package Componentes;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javafx.scene.shape.Circle;
import javax.swing.ImageIcon;

/**
 * Clase de un componente que tiene una etiqueta circular y una barra de proceso.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class ProgressCircular extends Canvas{

    // Constante de clase - Pincel para bordes.
    public static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    // Variable de clase - Porcentaje.
    private double porcentaje;

    // Variable de instancia - Arco para dibujar la parte del porcentaje.
    private Arc2D arc;

    // Variable de instancia - Color correspondiente al porcentaje dado.
    private Color colorBar;

    // Varaible de instancia - Imagen de fondo.
    private Image icono;

    /**
     * Constructor para objetos ProgressCircular
     *
     * @param porcentaje  Porcentaje inicial.
     */
    public ProgressCircular(double porcentaje){
        this.porcentaje = porcentaje;
        this.arc = new Arc2D.Double();
        this.colorBar = Color.black;
    }

    /**
     * Constructor para objetos ProgressCircular
     *
     * @param porcentaje  Valor del porcentaje inicial.
     * @param img Imagen de fondo.
     */
    public ProgressCircular(double porcentaje, Image img){
        this.porcentaje = porcentaje;
        this.arc = new Arc2D.Double();
        this.colorBar = Color.black;
        this.icono = img;
    }
    
    /**
     * Método para pintar de forma circular el progress.
     * @param g Graphics para pintar.
     */
    @Override
    public void paint(Graphics g){
        int ancho = this.getWidth()
            ,alto = this.getHeight();
        Image image = this.createImage(ancho, alto);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.translate(ancho / 2, alto / 2);
        drawContorno(ancho, alto, g2);
        drawString(porcentaje + "%", g2);
        g.setColor(Color.decode("#2A5A82"));
        g.drawImage(image, 3, 3,ancho,alto, this);
    }

    /**
     * Método para pintar el contorno del progress de forma circular.
     *
     * @param g Graphics2D para pintar.
     * @param alto Alto de la dimensión del componente.
     * @param ancho Ancho de la dimensión del componente.
     */
    private void drawContorno(double ancho, double alto, Graphics2D g){
        double diametro = (ancho > alto) ? alto : ancho 
               ,radio = diametro / 2
               ,degsFinal = - porcentaje / 100 * 360
               , x = -radio
               , y = x;
        diametro = diametro - STROKE.getLineWidth() / 2;
        g.setStroke(STROKE);
        g.setColor(this.colorBar);
        arc.setArc(x, y, diametro, diametro, 90, degsFinal, Arc2D.OPEN);
        g.draw(arc);
    }

    /**
     * Método para pintar el valor del porcentaje.
     *
     * @param g Graphics2D para pintar.
     * @param val Valor a pintar.
     */
    public void drawString(String val, Graphics2D g){
        Rectangle2D rec = getFont().getStringBounds(val,g.getFontRenderContext());
        double x = -rec.getWidth() / 2
               , y = -rec.getCenterY();
        g.drawString(val,(float) x, (float) y);
    }

    /**
     * Método para actualizar la pintada del componente.
     *
     * @param g Graphics para pintar.
     */
    @Override
    public void update(Graphics g){
        this.paint(g);
    }

    /**
     * Retorna el valor del porcentaje actual del componente.
     *
     * @return valor del porcentaje actual.
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * Estabelce el porcentaje a pintar en el componente.
     * Deberá estár en un rango de 0 100.
     *
     * @param porcentaje Valor a establecer.
     */
    public void setPorcentaje(double porcentaje) {
        if (porcentaje > 100 || porcentaje < 0) {
            porcentaje = 0;
        }
        this.porcentaje = porcentaje;
    }

    /**
     * Retorna el color de la barra para pintar.
     *
     * @return el color utilizado para pintar.
     */
    public Color getColorBar() {
        return colorBar;
    }

    /**
     * Establece el color de la barra para pintar.
     *
     * @param colorBar Color a utilizar para pintar en la barra.
     */
    public void setColorBar(Color colorBar) {
        this.colorBar = colorBar;
    }
}
