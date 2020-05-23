/*
 * La Tanda a producir
 */

package Procesos;

import java.util.ArrayList;

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

    public Tanda(char tipoMaguey, double porcentajeAlcohol, String tipoMezcal, int cantidadPinias) {
        this.tipoMaguey = tipoMaguey;
        this.porcentajeAlcohol = porcentajeAlcohol;
        this.tipoMezcal = tipoMezcal;
        this.cantidadPinias = cantidadPinias;
        generarPinias();
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

    @Override
    public String toString() {
        return "Tanda{" + "tipoMaguey=" + tipoMaguey + ", porcentajeAlcohol=" + porcentajeAlcohol + ", tipoMezcal=" + tipoMezcal + ", cantidadPinias=" + cantidadPinias + '}';
    }
    
    
}
