/*
 * Genera panel de barra con n elementos del componente ECP (Etiqueta Con ProgressBar)
 */
package Componentes;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author García García José Ángel
 */
public class BarraProceso extends JPanel {

    /* La cantidad de etiquetas que tendrá */
    private ArrayList<ECP> etiquetas;
    /* Progress circular que indica el proceso de cada barra */
    private ProgressCircular estado;
    /* La cantidad de procesos que tiene */
    private int nProcesos;
    
    private BCE2 cancelar = new BCE2(new ImageIcon(getClass().getResource("/Vista/cancelar.png")), "Cancelar");
    
    /* Si fue creado con imagenes
    private boolean full = false;
    private ImageIcon[] imagenes;
    private int[] porcentajes;
    */
    
    /**
     * Constructor simple
     */
    public BarraProceso() {
        etiquetas = new ArrayList<>();
        estado = new ProgressCircular(0);
    }

    /**
     * @param nProcesos Cantidad de ECP
     */
    public BarraProceso(int nProcesos) {
        estado = new ProgressCircular(0);
        setCantidadProcesos(nProcesos);
        agregarElemetos();
        actualizarEstado();
    }

    /**
     * Constructor para botones con imagenes y porcentajes dados
     */
    public BarraProceso(ImageIcon[] imagenes, int[] porcentajes) {
        estado = new ProgressCircular(0);
        if(validarCantidad(imagenes, porcentajes)){
            setCantidadProcesos(imagenes.length); // Creamos el arreglo de etiquetas
            agregarElementos(imagenes, porcentajes); // Agregamos lo meentos
            //this.imagenes = imagenes;
            //this.porcentajes = porcentajes;
        }else{
            setCantidadProcesos(imagenes.length);
            agregarElemetos();
            setImagenes(imagenes);
        }
        actualizarEstado();
    }
    
    /**
     * Agrega los elementos con su imagen y porcentaje correspondiete
     * @param imagenes Arrelgo de imagenes a establecer
     * @param porcentajes Arreglo de porcentajes a establecer
     */
    private void agregarElementos(ImageIcon[] imagenes, int[] porcentajes){
        // Ya estamos seguro de que son igual tamaño
        setLayout(new GridLayout(1, 0));
        setSize(700, 220);
        add(cancelar);
        for (int i = 0; i < nProcesos; i++){ 
            ECP e = new ECP(imagenes[i], porcentajes[i]); 
            etiquetas.add(e);
            add(e);
        }
        //add(estado);
    }
    
    /**
     * Agrega los elementos al arreglo
     */
    private void agregarElemetos(){
        setLayout(new GridLayout(1, 0));
        add(cancelar);       
        for (int i = 0; i < nProcesos; i++) {
            ECP e = new ECP();
            etiquetas.add(e);
            add(e);
        }
        add(estado);
    }
    
    /**
     * Valida que el numero de imagene y porcentajes sea el correcto
     *
     * @return true si son iguales y false de lo contrario
     */
    private boolean validarCantidad(ImageIcon[] imagenes, int[] porcentajes) {
        if (imagenes == null || porcentajes == null) {
            return false;
        }
        return (imagenes.length == porcentajes.length);
    }

    /**
     * Coloca las imagenes y porcentajes dados
     * @param imagenes Arrelgo de imagenes a colocar
     * @param porcentajes Arreglo de porcentajes a colocar
     */
    public void setImagenesYPorcentajes(ImageIcon[] imagenes, int[] porcentajes) {
        if (validarCantidad(imagenes, porcentajes) && !vacio()) {
            setPorcentajes(porcentajes);
            setImagenes(imagenes);
        }
        // else y no coloca nada
    }

    /**
     * Coloca los porcentajes a las etiquetas
     *
     * @param porcentajes Arreglo de porcentajes a colorcar
     */
    public void setPorcentajes(int[] porcentajes){
        if (!vacio() && porcentajes.length == nProcesos) {
            for (int i = 0; i < nProcesos; i++) {
                etiquetas.get(i).setValor(porcentajes[i]);
            }
        }
    }

    /**
     * Coloca las imagenes a las etiquetas
     *
     * @param imagenes Arreglo de imagenes a colorcar
     */
    public void setImagenes(ImageIcon[] imagenes) {
        if (!vacio() && imagenes.length == nProcesos) {
            for (int i = 0; i < nProcesos; i++) {
                etiquetas.get(i).setImagen(imagenes[i]);
            }
        }
    }

    /**
     * Coloca los componentes en el JPane, se ocupa
     */
    private void colocar() {
        SpringLayout s = new SpringLayout();
        for (ECP c : etiquetas) {
            
        }
    }

    /**
     * @return true si no tiene etiquetas y false de lo contrario.
     */
    private boolean vacio() {
        return nProcesos == 0;
    }

    /**
     * Retorna la etiqueta en una posición dada, en un rango de 0 a n
     *
     * @param pos Posición dada
     * @return Etiqueta de la posición dada si está vacía retorna un null
     */
    public ECP getPos(int pos) {
        return (vacio()) ? null : etiquetas.get(pos);
    }

    /**
     * Establece la cantidad de procesos
     *
     * @param nProcesos cantidad de componentes ECP a tener
     */
    public void setCantidadProcesos(int nProcesos) {
        this.nProcesos = (nProcesos < 0) ? 0 : nProcesos;
        this.etiquetas = (etiquetas == null) ? new ArrayList<>(nProcesos) : new ArrayList<>();
    }
    /**
     * Devuelve la cantidad de procesos
     * @return Cantidad de etiquetas que tiene 
     */
    public int getCantidadProcesos() {
        return nProcesos;
    }
    
    /**
     * Actualiza el estado de la barra de estado
     */
    public void actualizarEstado(){
        double promedio = 0;
        for (ECP e : etiquetas) {
            promedio += e.getValor();
        }
        estado.setPorcentaje(promedio / nProcesos);
        estado.repaint();
    }
    
    /**
     * Devuelve el botón cancelar
     * @return  El boton cancelar
     */
    public JButton getBotonCancelar(){
        return cancelar.getBoton();
    }
}
