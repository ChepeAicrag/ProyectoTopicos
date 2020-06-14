package Componentes;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SpringLayout;

/**
 * Clase de un componente que tiene un bóton redondo y una etiqueta abajo.
 * @author Garcíaa García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */

public class BCE extends JComponent{
        
    // Variable de instacia - Bóton redondo con imágen.
    private BotonRedondo btn;

    /**
     * Constructor para objetos de BCE.
     */

    public BCE(){
        btn = new BotonRedondo();
        colocar();
    }
    
    /**
     * Constructor para objetos de BCE con imagen y texto.
     *
     * @param img Imagen que tendrá.
     * @param text Texto de la etiqueta.
     */

    public BCE(ImageIcon img, String text){
        btn = new BotonRedondo();
        setImagen(img);
        setTexto(text);
        colocar();
    }
    
    /**
     * Constructor para obetos BCE con texto y fondo circular.
     *
     * @param text Texto de la etiqueta
     */

    public BCE(String text){
        btn = new BotonRedondo();
        setTexto(text);
        colocar();
    }
    
    /**
     * Coloca el bóton redondo en el componente.
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
     * Coloca el texto dado a la etiqueta del componente.
     *
     * @param text Texto que tendrá la etiqueta.
     */

    public void setTexto(String text){
        btn.setText(text);
        repaint();
    }
    
    /**
     * @return texto que tiene la etiqueta.
     */

    public String getText(){
        return btn.getText();
    }
    
    /**
     * Establece la imagen que contendrá el bóton.
     *
     * @param img Imagen a colocar.
     */

    public void setImagen(ImageIcon img){
        btn.setImagen(img);
    }
    
    /**
     * Establece el color del texto de la etiqueta.
     *
     * @param color Color a usar.
     */
    public void setColorText(Color color){
        btn.setColorText(color);
    }
    
    /**
     * @return imágen contenida en el bóton redondo.
     */

    public ImageIcon getImagen(){
        return new ImageIcon(btn.getImagen());
    }
     
    /**
     * Retorna el bóton redondo.
     *
     * @return el boton que contiene.
     */

    public JButton getBoton(){
       return btn; 
    }
}
