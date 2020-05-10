/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Componentes;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author García García José Ángel
 */
public class BarraEleccion extends JPanel{
    
    /* Array de Botones para los tipos de magueys*/
    private ArrayList<BCE2> magueys;
    /* Cantidad de magueys*/
    private int nMagueys;
    
    /**
     * Constructor simple
     */
    public BarraEleccion(){
        magueys = new ArrayList<>();
    }
    
    /**
     * @param nMagueys Cantidad de botones
     */
    public BarraEleccion(int nMagueys) {
        setCantidadMagueys(nMagueys);
        agregarElemetos();
    }
    
    /**
     * Constructor Barra de botones con imagenes y textos dados 
     */
    public BarraEleccion(ImageIcon[] imagenes, String[] textos) {
        if(validarCantidad(imagenes, textos)){
            setCantidadMagueys(imagenes.length); 
            agregarElementos(imagenes, textos); 
        }else{
            setCantidadMagueys(imagenes.length);
            agregarElemetos();
            setImagenes(imagenes);
        }
    }
    
    /**
     * Agrega los elementos con su imagen y texto correspondiete
     * @param imagenes Arrelgo de imagenes a establecer
     * @param textos Arreglo de textos a establecer
     */
    private void agregarElementos(ImageIcon[] imagenes, String[] textos){
        // Ya estamos seguro de que son igual tamaño
        
        setLayout(new GridLayout(1, 0));
        for (int i = 0; i < nMagueys; i++){ 
            BCE2 e = new BCE2(imagenes[i], textos[i]); 
            magueys.add(e);
            add(e);
        }
    }
    
    /**
     * Agrega los elementos al arreglo
     */
    private void agregarElemetos(){
        setLayout(new GridLayout(1, 0));     
        for (int i = 0; i < nMagueys; i++) {
            BCE2 e = new BCE2();
            magueys.add(e);
            add(e);
        }
    }
    
    
    /**
     * Valida que el numero de imagene y textos sea el correcto
     *
     * @return true si son iguales y false de lo contrario
     */
    private boolean validarCantidad(ImageIcon[] imagenes, String[] textos) {
        if (imagenes == null || textos == null) {
            return false;
        }
        return (imagenes.length == textos.length);
    }
    
    /**
     * @return true si no tiene botones y false de lo contrario.
     */
    public boolean vacio(){
        return nMagueys == 0;
    }
    
    /**
     * Retorna el botón en una posición dada, en un rango de 0 a n
     *
     * @param pos Posición dada
     * @return Botón de la posición dada si está vacía retorna un null
     */
    public BCE2 getPos(int pos) {
        return (vacio()) ? null : magueys.get(pos);
    }
    
    /**
     * Coloca las imagenes y porcentajes dados
     * @param imagenes Arrelgo de imagenes a colocar
     * @param textos Arreglo de textos a colocar
     */
    public void setImagenesYPorcentajes(ImageIcon[] imagenes, String[] textos) {
        if (validarCantidad(imagenes,textos) && !vacio()) {
            setTextos(textos);
            setImagenes(imagenes);
        }
        // else y no coloca nada
    }
    
    /**
     * Coloca las imagenes a los botones
     * @param imagenes Arreglo de imagenes a colorcar
     */
    public void setImagenes(ImageIcon[] imagenes) {
        if (!vacio() && imagenes.length == nMagueys) {
            for (int i = 0; i < nMagueys; i++) {
                magueys.get(i).setImagen(imagenes[i]);
            }
        }
    }
    
    /**
     * Coloca los texos a los botones
     * @param textos Arreglo de textos a colorcar
     */
    public void setTextos(String[] textos){
        if (!vacio() && textos.length == nMagueys) {
            for (int i = 0; i < nMagueys; i++) {
                magueys.get(i).setTexto(textos[i]);
            }
        }
    }
    
    /**
     * Establece la cantidad de magueys que hay
     * @param nMagueys Cantidad de magueys
     */
    public void setCantidadMagueys(int nMagueys){
        this.nMagueys = (nMagueys > 0 ) ? nMagueys : 0;
        this.magueys = (magueys == null) ? new ArrayList<>(nMagueys) : new ArrayList<>();
    }
    
    /**
     * Devuelve la cantidad de botones que tiene  
     * @return La cantidad de magueys que hay
     */
    public int getCantidadMagueys(){
        return nMagueys;
    }
}
