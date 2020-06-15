package Componentes;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

/**
 * Clase de un componente que tiene una etiqueta circular y una barra de proceso.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class ECP extends JComponent {

    // Variable de instancia - Barra de proceso.
    private JProgressBar barra;

    // Variable de instancia - Etiqueta redonda con imagen.
    private EtiquetaRedonda etq;

    // Variable de instancia - Etiqueta para el dato de la barra.
    private JLabel datoBarra;
    
    /**
     * Constructor para objetos de ECP.
     */
    public ECP() {
        barra = new JProgressBar();
        etq = new EtiquetaRedonda("texto a colocar");
        datoBarra = new JLabel("Textoo");
        colocar();
    }

    /**
     * Constructor para objetos de ECP con una imagen y un valor dado.
     *
     * @param img Imagen que tendrá la etiqueta
     * @param val Valor del ProgressBar
     */
    public ECP(ImageIcon img, int val) {
        barra = new JProgressBar();
        etq = new EtiquetaRedonda("Texto Ejemplo");
        datoBarra = new JLabel("Texto",SwingUtilities.CENTER);
        setValor(val);
        setImagen(img);
        colocar();
    }
    
    /**
     * Coloca los componentes adecuadamente.
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
        add(datoBarra);
        s.putConstraint(SpringLayout.NORTH, datoBarra, 5, SpringLayout.SOUTH, barra);
        s.putConstraint(SpringLayout.WEST, datoBarra, 10, SpringLayout.WEST, this);
        s.putConstraint(SpringLayout.EAST, datoBarra, -10, SpringLayout.EAST, this);
    }
    
    /**
     * Establece la imagen que tendrá la etiqueta.
     *
     * @param img Imágen a colocar.
     */
    public void setImagen(ImageIcon img) {
        etq.setImagen(img);
    }

    /**
     * Establece el valor a PorgressBar.
     *
     * @param val Valor a establecer.
     */
    public void setValor(int val) {
        barra.setValue((val < 100 && val > 0) ? val : 0);
        barra.setString(val + "%");
        barra.setStringPainted(true);
        barra.repaint();
        barra.revalidate();
    }

    /**
     * Retorna el valor que tiene la barra de progreso.
     *
     * @return porcentaje actual de la barra.
     */
    public int getValor(){
        return (barra.getString().contains("100")) ? 100 : barra.getValue();
    }

    /**
     * Retorna la barra correspondiente a la etiqueta.
     *
     * @return la barra de progreso contenida.
     */
    public JProgressBar getBarra() {
        return barra;
    }

    /**
     * Retorna la etiqueta correspodiente al componente.
     *
     * @return la etiqueta colocada.
     */
    public JLabel getEtiqueta() {
        return etq;
    }

    /**
     * Establece el dato por asignar a la barra.
     *
     * @param texto Texto a colocar.
     */
    public void setDatoBarra(String texto){
        datoBarra.setForeground(Color.WHITE);
        datoBarra.setText(texto);
        datoBarra.setAlignmentX(CENTER_ALIGNMENT);
    }
}
