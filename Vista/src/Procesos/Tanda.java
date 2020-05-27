/*
 * La Tanda a producir
 */

package Procesos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author García García José Ángel
 */
public class Tanda {
    
    private char tipoMaguey;
    private ArrayList<Object> pinias;
    private double porcentajeAlcohol;
    private String tipoMezcal;
    private int cantidadPinias;
    private Color color;
    public Tanda(char tipoMaguey, double porcentajeAlcohol, String tipoMezcal, int cantidadPinias) {
        this.tipoMaguey = tipoMaguey;
        this.porcentajeAlcohol = porcentajeAlcohol;
        this.tipoMezcal = tipoMezcal;
        this.cantidadPinias = cantidadPinias;
        generarColor();
        generarPinias();
    }
    
    private void generarColor(){
        /** Generamos un color, jamás será el negro*/
        Random ran = new Random();
        float r = (float) (ran.nextFloat() / 2f + 0.5);
        float g = (float) (ran.nextFloat() / 2f + 0.5);
        float b = (float) (ran.nextFloat() / 2f + 0.5);
        color = new Color(r, g, b);
    }
    
    private void generarPinias(){
        pinias = new ArrayList();
        for (int i = 0; i < cantidadPinias ; i++) {
            pinias.add(new Pinia(tipoMaguey));
        }
    }

    public char getTipoMaguey() {
        return tipoMaguey;
    }

    public ArrayList<Object> getPinias() {
        return pinias;
    }

    public double getPorcentajeAlcohol() {
        return porcentajeAlcohol;
    }

    public String getTipoMezcal() {
        return tipoMezcal;
    }

    public int getCantidadPinias() {
        return cantidadPinias;
    }
    
    public Color getColor(){
        return color;
    }

    @Override
    public String toString() {
        return "Tanda{" + "tipoMaguey=" + tipoMaguey + ", porcentajeAlcohol=" + porcentajeAlcohol + ", tipoMezcal=" + tipoMezcal + ", cantidadPinias=" + cantidadPinias + '}';
    }
    
    
}
