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
    private int  id, tipoMaguey, porcentajeAlcohol, tipoMezcal, cantidadPinias,
                 id_Cortador, id_Horneador, id_Triturador, id_Fermentador, 
                 id_Destilador, id_Enbotelladora;
    private Color color;
    private static int id_Contador = 1;
    private Date fechaInicio, fechaFinal;
    private String estado;
   
    public Tanda(){
        
    }
    
    // Se crea con los id para las FK
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

    public int getPorcentajeAlcohol() {
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

    public int getId_Cortador() {
        return id_Cortador;
    }

    public void setId_Cortador(int id_Cortador) {
        this.id_Cortador = id_Cortador;
    }

    public int getId_Horneador() {
        return id_Horneador;
    }

    public void setId_Horneador(int id_Horneador) {
        this.id_Horneador = id_Horneador;
    }

    public int getId_Triturador() {
        return id_Triturador;
    }

    public void setId_Triturador(int id_Triturador) {
        this.id_Triturador = id_Triturador;
    }

    public int getId_Fermentador() {
        return id_Fermentador;
    }

    public void setId_Fermentador(int id_Fermentador) {
        this.id_Fermentador = id_Fermentador;
    }

    public int getId_Destilador() {
        return id_Destilador;
    }

    public void setId_Destilador(int id_Destilador) {
        this.id_Destilador = id_Destilador;
    }

    public int getId_Enbotelladora() {
        return id_Enbotelladora;
    }

    public void setId_Enbotelladora(int id_Enbotelladora) {
        this.id_Enbotelladora = id_Enbotelladora;
    }
}
