/*
 * Etiqueta con progress bar
 */
package Componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

/**
 *
 * @author García García José Ángel
 */
public class ECP extends JComponent {

    // Variables de instancia
    private JProgressBar barra;
    private EtiquetaRedonda etq;

    /**
     * Constructor simple
     */
    public ECP() {
        barra = new JProgressBar();
        etq = new EtiquetaRedonda("texto a colocar");
        colocar();
    }

    /**
     * Constructor para nuestro proyecto
     *
     * @param img Imagen que tendrá la etiqueta
     * @param val Valor del ProgressBar
     */
    public ECP(ImageIcon img, int val) {
        barra = new JProgressBar();
        etq = new EtiquetaRedonda("Texto Ejemplo");
        setValor(val);
        setImagen(img);
        colocar();
    }
    
    /**
     * Coloca los componentes adecuadamente
     */
    private void colocar(){
        setPreferredSize(new Dimension(100, 200));
        setSize(100, 200);
        SpringLayout s = new SpringLayout();
        setLayout(s);
        
        add(etq);
        s.putConstraint(SpringLayout.NORTH, etq, 10, SpringLayout.NORTH, this);
        s.putConstraint(SpringLayout.WEST, etq, 10, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, etq, -10, SpringLayout.EAST, this);
        s.putConstraint(SpringLayout.SOUTH, etq, -50, SpringLayout.SOUTH, this);
        
        add(barra);
        s.putConstraint(SpringLayout.NORTH, barra, 10, SpringLayout.SOUTH, etq);
        s.putConstraint(SpringLayout.WEST, barra, 10, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, barra, -10, SpringLayout.EAST, this);
        //s.putConstraint(SpringLayout.SOUTH, barra, -10, SpringLayout.SOUTH, this);
        
    }
    
    /**
     * Establece la imagen a la etiqueta
     *
     * @param img Imagen a colocar
     */
    public void setImagen(ImageIcon img) {
        etq.setImagen(img);
    }

    /**
     * Establece el valor a PorgressBar
     *
     * @param val Valor a establecer
     */
    public void setValor(int val) {
        barra.setValue((val < 100 && val > 0) ? val : 0);
        barra.setString(val + "%");
        barra.setStringPainted(true);
    }
    /**
     * Retorna el valor que tiene la barra de progreso
     * @return Porcentaje actual de la barra
     */
    public int getValor(){
        // Tomando en cuenta que la barra al llegar a 100 retorna 0
        return (barra.getString().contains("100")) ? 100 : barra.getValue();
    }

    /**
     * @return La barra de progreso contenida
     */
    public JProgressBar getBarra() {
        return barra;
    }

    /**
     * @return La etiqueta colocada
     */
    public JLabel getEtiqueta() {
        return etq;
    }
}
