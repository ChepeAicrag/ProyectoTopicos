/*
 * Componente boton circular con imagen y texto
 * Personalizado:
 *  Solo usamos el componente boton redondo, evitamos la etiqueta 
 */

package Componentes;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SpringLayout;

/**
 * 
 * @author García García José Ángel
 */
public class BCE2 extends JComponent{
        
    /* Variables de instacia */
    private BotonRedondo btn;
    
    public BCE2(){
        btn = new BotonRedondo();
        colocar();
    }
    
    /**
     * Constructor con imagen y texto
     * @param img Imagen que tendrá
     * @param text Texto de la etiqueta
     */
    public BCE2(ImageIcon img, String text){
        btn = new BotonRedondo();
        setImagen(img);
        setTexto(text);
        colocar();
    }
    
    /**
     * Constructor con texto y fondo circular
     * @param text Texto de la etiqueta
     */
    public BCE2(String text){
        btn = new BotonRedondo();
        setTexto(text);
        colocar();
    }
    
    /**
     * Coloca el boton en el componente
     */
    private void colocar(){
        SpringLayout s = new SpringLayout();
        setLayout(s);
        add(btn);
        s.putConstraint(SpringLayout.NORTH, btn, 0, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, btn, 0, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, btn, -0, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, btn, -0, SpringLayout.SOUTH, this);
    }

    /**
     * @param  text texto que tendrá la etiqueta
     */
    public void setTexto(String text){
        btn.setText(text);
        repaint();
    }
    
    /**
     * @return Texto que tiene la etiqueta
     */
    public String getText(){
        return btn.getText();
    }
    
    /**
     * Establece la imagen que contendrá el boton
     * @param img Imagen a colocar
     */
    public void setImagen(ImageIcon img){
        btn.setImagen(img);
    }
    
    /**
     * Indicar color del texto 
     * @param color color a usar
     */
    public void setColorText(Color color){
        btn.setColorText(color);
    }
    
    /**
     * @return Imagen contenida en el boton
     */
    public ImageIcon getImagen(){
        return new ImageIcon(btn.getImagen());
    }
     
    /**
     * Para agregar eventos a nuestro boton
     * @return El boton que contiene
     */
    public JButton getBoton(){
       return btn; 
    }
}