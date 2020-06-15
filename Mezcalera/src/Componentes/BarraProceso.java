package Componentes;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Clase de un componente tipo JPanel que representa una Barra de procesos (Etiquetas).
 * @author Garcíaa García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class BarraProceso extends JPanel {

    // Variable de instancia - La cantidad de etiquetas que tendrá.
    private ArrayList<ECP> etiquetas;

    // Variable de instancia - Progress circular que indica el proceso de cada barra.
    private ProgressCircular estado;

    // Variable de instancia - La cantidad de procesos que tiene.
    private int numeroProcesos;

    // Variable de intancia - La imagende fondo que tiene la barra.
    private Image imagenFondo = new ImageIcon(getClass().getResource("/Imagenes/fondo_barra.jpg")).getImage();

    /**
     * Constructor para objetos de BarraProceso.
     */
    public BarraProceso() {
        etiquetas = new ArrayList<>();
        estado = new ProgressCircular(0);
    }

    /**
     * Constructor para objetos de BarraProceso con un
     * numero de etiquetas dado.
     *
     * @param numeroProcesos Cantidad de ECP.
     */
    public BarraProceso(int numeroProcesos) {
        estado = new ProgressCircular(0);
        setCantidadProcesos(numeroProcesos);
        agregarElemetos();
        actualizarEstado();
    }

    /**
     * Constructor para botones con imagenes y porcentajes dados.
     * Si son de diferente tamaño, se colocan unicamente
     * las imagenes.
     *
     * @param imagenes Arreglo de imagenes que tendrá las etiquetas.
     */
    public BarraProceso(ImageIcon[] imagenes, int[] porcentajes) {
        estado = new ProgressCircular(0);
        if(validarCantidad(imagenes, porcentajes)){
            setCantidadProcesos(imagenes.length);
            agregarElementos(imagenes, porcentajes);
        }else{
            setCantidadProcesos(imagenes.length);
            agregarElemetos();
            setImagenes(imagenes);
        }
        actualizarEstado();
    }
    
    /**
     * Agrega los elementos con su imagen y porcentaje correspondiete.
     *
     * @param imagenes Arrelgo de imagenes a establecer.
     * @param porcentajes Arreglo de porcentajes a establecer.
     */
    private void agregarElementos(ImageIcon[] imagenes, int[] porcentajes){
        setLayout(new GridLayout(1, 0));
        setSize(700, 220);
        for (int i = 0; i < numeroProcesos; i++){
            ECP e = new ECP(imagenes[i], porcentajes[i]); 
            etiquetas.add(e);
            add(e);
        }
        /** Para nuestro proyecto no lo usaremos */
        //add(estado);
    }
    
    /**
     * Agrega los elementos al array y al panel.
     */
    private void agregarElemetos(){
        setLayout(new GridLayout(1, 0));
        for (int i = 0; i < numeroProcesos; i++) {
            ECP e = new ECP();
            etiquetas.add(e);
            add(e);
        }
        add(estado);
    }
    
    /**
     * Valida que el numero de imagenes y porcentajes sea el correcto.
     *
     * @return true si son iguales y false de lo contrario
     */
    private boolean validarCantidad(ImageIcon[] imagenes, int[] porcentajes) {
        return  (imagenes == null || porcentajes == null) ? false :(imagenes.length == porcentajes.length);
    }

    /**
     * Coloca a las etiquetas las imagenes y porcentajes dados.
     * Si son de diferentes cantidades no las coloca a las etiquetas.
     *
     * @param imagenes Arreglo de imagenes a colocar.
     * @param porcentajes Arreglo de porcentajes a colocar.
     */
    public void setImagenesYPorcentajes(ImageIcon[] imagenes, int[] porcentajes) {
        if (validarCantidad(imagenes, porcentajes) && !vacio()) {
            setPorcentajes(porcentajes);
            setImagenes(imagenes);
        }
    }

    /**
     * Coloca los porcentajes dados a las etiquetas.
     *
     * @param porcentajes Arreglo de porcentajes a colocar.
     */
    public void setPorcentajes(int[] porcentajes){
        if (!vacio() && porcentajes.length == numeroProcesos)
            for (int i = 0; i < numeroProcesos; i++)
                etiquetas.get(i).setValor(porcentajes[i]);
    }

    /**
     * Coloca las imagenes a las etiquetas.
     *
     * @param imagenes Arreglo de imagenes a colocar.
     */
    public void setImagenes(ImageIcon[] imagenes) {
        if (!vacio() && imagenes.length == numeroProcesos)
            for (int i = 0; i < numeroProcesos; i++)
                etiquetas.get(i).setImagen(imagenes[i]);
    }

    /**
     * @return true si no tiene etiquetas y false de lo contrario.
     */
    private boolean vacio() {
        return numeroProcesos == 0;
    }

    /**
     * Retorna la etiqueta en una posición dada, en un rango de 0 a n.
     *
     * @param pos Posición dada.
     * @return ECP componente de la posición dada, si está vacía retorna un null.
     */
    public ECP getPos(int pos) {
        return (vacio()) ? null : etiquetas.get(pos);
    }

    /**
     * Establece la cantidad de procesos.
     *
     * @param numeroProcesos Cantidad de componentes ECP a tener.
     */
    public void setCantidadProcesos(int numeroProcesos) {
        this.numeroProcesos = (numeroProcesos < 0) ? 0 : numeroProcesos;
        this.etiquetas = (etiquetas == null) ? new ArrayList<>(numeroProcesos) : new ArrayList<>();
    }

    /**
     * Devuelve la cantidad de etiquetas que tiene la barra.
     *
     * @return Cantidad de etiquetas que tiene.
     */
    public int getCantidadProcesos() {
        return numeroProcesos;
    }
    
    /**
     * Actualiza el estado de la barra de procesos.
     */
    public void actualizarEstado(){
        double promedio = 0;
        for (ECP e : etiquetas)
            promedio += e.getValor();
        estado.setPorcentaje(promedio / numeroProcesos);
        estado.repaint();
    }

    /**
     * Método para pintar la imágen de fondo.
     * @param g Graphics para pintar.
     */
     @Override
    public void paint(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
