/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * 
 * @author García García José Ángel
 */
public class ProgressCircular extends Canvas{
    public static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private double porcentaje;
    private Arc2D arc;
    private Color colorBar;
    private Image icono;
    
    public ProgressCircular(double porcentaje){
        this.porcentaje = porcentaje;
        this.arc = new Arc2D.Double();
        this.colorBar = Color.black;
    }
    
    public ProgressCircular(double porcentaje, Image img){
        this.porcentaje = porcentaje;
        this.arc = new Arc2D.Double();
        this.colorBar = Color.black;
        this.icono = img;
    }
    
    
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
    
    
    // Dibuja el contorno circular 
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
    
    public void drawString(String val, Graphics2D g){
        Rectangle2D rec = getFont().getStringBounds(val,g.getFontRenderContext());
        double x = -rec.getWidth() / 2
               , y = -rec.getCenterY();
        g.drawString(val,(float) x, (float) y);
    }
    
    
    
    
    @Override
    public void update(Graphics g){
        this.paint(g);
    }
    
    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        if (porcentaje > 100 || porcentaje < 0) {
            porcentaje = 0;
        }
        this.porcentaje = porcentaje;
    }

    public Color getColorBar() {
        return colorBar;
    }

    public void setColorBar(Color colorBar) {
        this.colorBar = colorBar;
    }
   
}
