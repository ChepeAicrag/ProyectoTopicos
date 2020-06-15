package Componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Clase de un componente tipo JPanel que representa una Barra de elección (Botones).
 * @author Garcíaa García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class BarraEleccion extends JPanel{

    // Variable de instancia - Array de Botones para los tipos de magueyes.
    private ArrayList<BCE> magueyes;

    // Variable de instancia - Cantidad de magueyes.
    private int numeroMagueyes;

    // Variable de instancia - Imagen de fondo.
    private Image imagen;

    /**
     * Constructor para objetos de BarraEleccion.
     */
    public BarraEleccion(){
        magueyes = new ArrayList<>();
    }
    
    /**
     * Constructor para objetos de BarraEleccion con una cantidad
     * de botones dada.
     *
     * @param numeroMagueyes Cantidad de botones.
     */
    public BarraEleccion(int numeroMagueyes) {
        setCantidadmagueyes(numeroMagueyes);
        agregarElemetos();
    }
    
    /**
     * Constructor para objetos de BarraEleccion con los elementos
     * de imagenes y textos dados.
     * Si son de diferente tamaño, se colocan unicamente las imagenes.
     *
     * @param imagenes Arreglo de imagenes que tendrán los botones.
     * @param textos Arreglo de textos que tendrán los botones.
     */
    public BarraEleccion(ImageIcon[] imagenes, String[] textos) {
        if(validarCantidad(imagenes, textos)){
            setCantidadmagueyes(imagenes.length); 
            agregarElementos(imagenes, textos); 
        }else{
            setCantidadmagueyes(imagenes.length);
            agregarElemetos();
            setImagenes(imagenes);
        }
    }
    
    /**
     * Agrega los elementos con su imagen y texto correspondiente.
     *
     * @param imagenes Arrelgo de imagenes a establecer.
     * @param textos Arreglo de textos a establecer.
     */
    private void agregarElementos(ImageIcon[] imagenes, String[] textos){
        setLayout(new GridLayout(1, 0));
        for (int i = 0; i < numeroMagueyes; i++){ 
            BCE e = new BCE(imagenes[i], textos[i]); 
            magueyes.add(e);
            add(e);
        }
    }
    
    /**
     * Agrega los elementos al array de botones y al panel.
     */
    private void agregarElemetos(){
        setLayout(new GridLayout(1, 0));     
        for (int i = 0; i < numeroMagueyes; i++) {
            BCE e = new BCE();
            magueyes.add(e);
            add(e);
        }
    }
    
    
    /**
     * Valida que el numero de imagenes y textos sea el correcto.
     *
     * @return true si son iguales y false de lo contrario.
     */
    private boolean validarCantidad(ImageIcon[] imagenes, String[] textos) {
        return (imagenes == null || textos == null) ? false : imagenes.length == textos.length;
    }
    
    /**
     * Método que indica si hay o no botones.
     *
     * @return true si no tiene botones y false de lo contrario.
     */
    public boolean vacio(){
        return numeroMagueyes == 0;
    }
    
    /**
     * Retorna el botón en una posición dada, en un rango de 0 a n.
     *
     * @param pos Posición dada.
     * @return Botón de la posición dada, si está vacía retorna un null.
     */
    public BCE getPos(int pos) {
        return (vacio()) ? null : magueyes.get(pos);
    }
    
    /**
     * Coloca las imagenes y textos dados a los botones.
     * Si son de tamaños diferentes no se colocan.
     * Si no hay botones, tampoco se colocan los elementos dados.
     *
     * @param imagenes Arreglo de imagenes a colocar.
     * @param textos Arreglo de textos a colocar.
     */
    public void setImagenesYPorcentajes(ImageIcon[] imagenes, String[] textos) {
        if (validarCantidad(imagenes,textos) && !vacio()) {
            setTextos(textos);
            setImagenes(imagenes);
        }
    }
    
    /**
     * Coloca las imagenes a los botones.
     * Si está vacia o la cantidad de imagenes suepera la de los botones,
     * no se establecen las imagenes dadas.
     *
     * @param imagenes Arreglo de imagenes a colorcar.
     */
    public void setImagenes(ImageIcon[] imagenes) {
        if (!vacio() && imagenes.length == numeroMagueyes)
            for (int i = 0; i < numeroMagueyes; i++)
                magueyes.get(i).setImagen(imagenes[i]);
    }
    
    /**
     * Coloca los texos a los botones.
     * Si está vacia o la cantidad de textos suepera la de los botones,
     * no se establecen los textos dados.
     *
     * @param textos Arreglo de textos a colorcar.
     */
    public void setTextos(String[] textos){
        if (!vacio() && textos.length == numeroMagueyes)
            for (int i = 0; i < numeroMagueyes; i++) {
                magueyes.get(i).setTexto(textos[i].toUpperCase());
                magueyes.get(i).setFont(new Font("Arial", Font.BOLD, 14));
                magueyes.get(i).setColorText(Color.WHITE);
            }
    }
    
    /**
     * Establece la cantidad de magueyes que tendrá la barra.
     * 
     * @param numeroMagueyes Cantidad de magueyes.
     */
    public void setCantidadmagueyes(int numeroMagueyes){
        this.numeroMagueyes = (numeroMagueyes > 0 ) ? numeroMagueyes : 0;
        this.magueyes = (magueyes == null) ? new ArrayList<>(numeroMagueyes) : new ArrayList<>();
    }
    
    /**
     * Retorna la cantidad de botones que tiene la barra.
     *
     * @return La cantidad de magueyes que hay.
     */
    public int getCantidadmagueyes(){
        return numeroMagueyes;
    }
    
    /**
     * Retorna el array que contiene a los botones.
     *
     * @return magueyes ArrayList de botones.
     */
    public ArrayList<BCE> getBotones(){
        return magueyes;
    }

    /**
     * Método para colocar una imágen de fondo.
     *
     * @param imagen Imagen a colocar de fondo.
     */
    public void setFondoImagen(ImageIcon imagen){
        this.imagen = imagen.getImage();
    }

    /**
     * Método para pintar la imágen de fondo.
     * @param g Graphics para pintar.
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}
