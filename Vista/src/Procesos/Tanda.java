/*
 * La Tanda a producir
 */

package Procesos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author García García José Ángel
 */
public class Tanda {
    ;
    private ArrayList<Object> pinias;
    private int  id, tipoMaguey, porcentajeAlcohol, tipoMezcal, cantidadPinias;
    private Color color;
    private static int id_Contador = 1;
    private Date fechaInicio, fechaFinal;
    private String estado;

    public Tanda(){
        
    }
    
    public Tanda(int tipoMaguey, int porcentajeAlcohol, int tipoMezcal, int cantidadPinias) {
        this.tipoMaguey = tipoMaguey;
        this.porcentajeAlcohol = porcentajeAlcohol;
        this.tipoMezcal = tipoMezcal;
        this.cantidadPinias = cantidadPinias;
        /*
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        */
        id = id_Contador++;
        generarColor();
        generarPinias();
        estado = "Registrada";
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

    public void setFechaInicio(Date fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    
    public Date getFechaInicio(){
        return fechaInicio;
    }
    
    public void setFechaFinal(Date fechaFinal){
        this.fechaFinal = fechaFinal;
    }
    
    public Date getFechaFinal(){
        return fechaFinal;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public int getTipoMaguey() {
        return tipoMaguey;
    }

    public ArrayList<Object> getPinias() {
        return pinias;
    }

    public double getPorcentajeAlcohol() {
        return porcentajeAlcohol;
    }

    public int getTipoMezcal() {
        return tipoMezcal;
    }

    public int getCantidadPinias() {
        return cantidadPinias;
    }
    
    public Color getColor(){
        return color;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public String getEstado(){
        return estado;
    }
    
    @Override
    public String toString() {
        return "Tanda{" + "tipoMaguey=" + tipoMaguey + ", porcentajeAlcohol=" + porcentajeAlcohol + ", tipoMezcal=" + tipoMezcal + ", cantidadPinias=" + cantidadPinias + '}';
    }
    
    
}
