package Procesos;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Clase para representar una Tanda de producción.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Tanda {

    // Variable de instancia - Array de piñas.
    private ArrayList<Producto> pinias;

    // Variable de instancia - Identificadores de cada equipo.
    private int  id, tipoMaguey, porcentajeAlcohol, tipoMezcal, cantidadPinias,
                 id_Cortador, id_Horneador, id_Triturador, id_Fermentador, 
                 id_Destilador, id_Enbotelladora, id_Transportador, id_Cliente;

    // Variable de instancia - Color que la identifica.
    private Color color;

    // Variable de clase - Identificador.
    private static int id_Contador = 1;

    // Variable de instancia - Fechas de producción de la tanda.
    private Timestamp fechaInicio, fechaFinal;

    // Variable de instancia - Estado de la tanda.
    private String estado;

    /**
     * Constructor para objetos de Tanda.
     */
    public Tanda(){
        
    }

    /**
     * Constructor para objetos de Tanda.
     *
     * @param tipoMaguey Valor de la FK para el tipo de maguey.
     * @param porcentajeAlcohol Valor de la FK para el porcentaje de alcohol.
     * @param tipoMezcal Valor de la FK para el tipo de mezcal.
     * @param cantidadPinias Cantidad de piñas que tendrá la tanda.
     */
    public Tanda(int tipoMaguey, int porcentajeAlcohol, int tipoMezcal, int cantidadPinias) {
        this.tipoMaguey = tipoMaguey;
        this.porcentajeAlcohol = porcentajeAlcohol;
        this.tipoMezcal = tipoMezcal;
        this.cantidadPinias = cantidadPinias;
        //id = id_Contador++;
        generarColor();
        generarPinias();
        estado = "Registrada";
    }

    /**
     * Genera un color de tono claro para identificar la tanda.
     */
    private void generarColor(){
        /** Generamos un color, jamás será el negro*/
        Random ran = new Random();
        float r = (float) (ran.nextFloat() / 2f + 0.5);
        float g = (float) (ran.nextFloat() / 2f + 0.5);
        float b = (float) (ran.nextFloat() / 2f + 0.5);
        color = new Color(r, g, b);
    }

    /**
     * Genera objeto de las piñas que se utilizarán.
     */
    private void generarPinias(){
        pinias = new ArrayList();
        for (int i = 0; i < cantidadPinias ; i++)
            pinias.add(new Pinia(tipoMaguey));
    }

    /**
     * Establece la fecha de inicio de producción.
     *
     * @param fechaInicio Fecha a establecer.
     */
    public void setFechaInicio(Timestamp fechaInicio){
        this.fechaInicio = fechaInicio;
    }

    /**
     * Retorna la fecha en que se inición la producción.
     *
     * @return Fecha de inicio.
     */
    public Date getFechaInicio(){
        return fechaInicio;
    }

    /**
     * Establce la fecha final de la producción.
     *
     * @param fechaFinal Fecha a establecer.
     */
    public void setFechaFinal(Timestamp fechaFinal){
        this.fechaFinal = fechaFinal;
    }

    /**
     * Retorna la fecha final de la producción.
     *
     * @return Fecha final.
     */
    public Date getFechaFinal(){
        return fechaFinal;
    }

    /**
     * Establece el id a la tanda.
     *
     * @param id Valor a establecer.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Retorna el id de la tanda.
     *
     * @return Identificador único de la tanda.
     */
    public int getId(){
        return id;
    }

    /**
     * Retorna el identificador del tipo de maguey.
     *
     * @return Tipo de maguey.
     */
    public int getTipoMaguey() {
        return tipoMaguey;
    }

    /**
     * Retorna el array de las piñas.
     *
     * @return Array de pinias.
     */
    public ArrayList<Producto> getPinias() {
        return pinias;
    }

    /**
     * Retorna el valor del porcentaje de alcohol.
     *
     * @return Porcentaje de alcohol.
     */
    public int getPorcentajeAlcohol() {
        return porcentajeAlcohol;
    }

    /**
     * Retorna el identificador del tipo de mezcal.
     *
     * @return Tipo de mezcal.
     */
    public int getTipoMezcal() {
        return tipoMezcal;
    }

    /**
     * Retorna la cantidad de piñas que posee la tanda.
     *
     * @return Cantidad de piñas.
     */
    public int getCantidadPinias() {
        return cantidadPinias;
    }

    /**
     * Retorna el color que identifica a la tanda.
     *
     * @return Color de la tanda.
     */
    public Color getColor(){
        return color;
    }

    /**
     * Establece el estado a la tanda.
     *
     * @param estado Estado a establecer.
     */
    public void setEstado(String estado){
        this.estado = estado;
    }

    /**
     * Retorna el estado en el que se encuentre la tanda.
     *
     * @return Estado actual de la tanda.
     */
    public String getEstado(){
        return estado;
    }

    /**
     * Representación de la tanda.
     *
     * @return Datos basicos de la tanda.
     */
    @Override
    public String toString() {
        return "Tanda{" +
                "pinias=" + pinias +
                ", id=" + id +
                ", tipoMaguey=" + tipoMaguey +
                ", porcentajeAlcohol=" + porcentajeAlcohol +
                ", tipoMezcal=" + tipoMezcal +
                ", cantidadPinias=" + cantidadPinias +
                ", id_Cortador=" + id_Cortador +
                ", id_Horneador=" + id_Horneador +
                ", id_Triturador=" + id_Triturador +
                ", id_Fermentador=" + id_Fermentador +
                ", id_Destilador=" + id_Destilador +
                ", id_Enbotelladora=" + id_Enbotelladora +
                ", id_Transportador=" + id_Transportador +
                ", id_Cliente=" + id_Cliente +
                ", color=" + color +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinal=" + fechaFinal +
                ", estado='" + estado + '\'' +
                '}';
    }

    //@Override
    //public String toString() {
      //  return "Tanda{" + "tipoMaguey=" + tipoMaguey + ", porcentajeAlcohol=" + porcentajeAlcohol + ", tipoMezcal=" + tipoMezcal + ", cantidadPinias=" + cantidadPinias + '}';
    //}

    /**
     * Retorna el identificador del cortador que trabajó la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Cortador() {
        return id_Cortador;
    }

    /**
     * Establece el identificador del cortador que trabajó la tanda.
     *
     * @param id_Cortador Valor del identificador.
     */
    public void setId_Cortador(int id_Cortador) {
        this.id_Cortador = id_Cortador;
    }

    /**
     * Retorna el identificador del horneador que trabajó la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Horneador() {
        return id_Horneador;
    }

    /**
     * Establece el identificador del horneador que trabajó la tanda.
     *
     * @param id_Horneador Valor del identificador.
     */
    public void setId_Horneador(int id_Horneador) {
        this.id_Horneador = id_Horneador;
    }

    /**
     * Retorna el identificador del triturador que trabajó la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Triturador() {
        return id_Triturador;
    }

    /**
     * Establece el identificador del triturador que trabajó la tanda.
     *
     * @param id_Triturador Valor del identificador.
     */
    public void setId_Triturador(int id_Triturador) {
        this.id_Triturador = id_Triturador;
    }

    /**
     * Retorna el identificador del fermentador que trabajó la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Fermentador() {
        return id_Fermentador;
    }

    /**
     * Establece el identificador del fermentador que trabajó la tanda.
     *
     * @param id_Fermentador Valor del identificador.
     */
    public void setId_Fermentador(int id_Fermentador) {
        this.id_Fermentador = id_Fermentador;
    }

    /**
     * Retorna el identificador del destilador que trabajó la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Destilador() {
        return id_Destilador;
    }

    /**
     * Establece el identificador del destilador que trabajó la tanda.
     *
     * @param id_Destilador Valor del identificador.
     */
    public void setId_Destilador(int id_Destilador) {
        this.id_Destilador = id_Destilador;
    }

    /**
     * Retorna el identificador de la enbotelladora que trabajó la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Enbotelladora() {
        return id_Enbotelladora;
    }

    /**
     * Establece el identificador de la enbotelladora que trabajó la tanda.
     *
     * @param id_Enbotelladora Valor del identificador.
     */
    public void setId_Enbotelladora(int id_Enbotelladora) {
        this.id_Enbotelladora = id_Enbotelladora;
    }

    /**
     * Establece el identificador del que transportó la tanda.
     *
     * @param id_Transportador Valor del identificador.
     */
    public void setId_Transportador(int id_Transportador){this.id_Transportador = id_Transportador;}

    /**
     * Retorna el identificador del transportador de la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Transportador(){return id_Transportador;}

    /**
     * Retorna el identificador del cliente que compró la tanda.
     *
     * @return Valor del identificador.
     */
    public int getId_Cliente() {
        return id_Cliente;
    }

    /**
     * Establece el identificador del cliente que compró la tanda.
     *
     * @param id_Cliente Valor del identificador.
     */
    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
}
