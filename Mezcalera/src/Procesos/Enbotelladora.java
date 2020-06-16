package Procesos;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Clase para el equipo Enbotelladora.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Enbotelladora extends Equipo{

    // Variable de instancia - Tandas a transportar.
    private ArrayList <Integer> tandasTransportar;

    /**
     * Constructor para objetos de la clase Enbotelladora.
     *
     * @param id Id que tendrá el equipo Enbotelladora.
     * @param bufferMezcalDestilado Buffer del que se consume las tandas.
     * @param bufferBarriles Buffer al que se produce tandas.
     */
    public Enbotelladora(int id, BufferTandas bufferMezcalDestilado, BufferTandas bufferBarriles){
        super(id, bufferMezcalDestilado, bufferBarriles);
        setTexto("Enbotelladora terminó");
    }

    /**
     * Consume las tanda destilada para enbotellarla.
     *
     * @throws InterruptedException Error al consumir.
     */
    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove();
        tanda.setId_Enbotelladora(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        tanda.setEstado("Enbotellando");
        getTandasActualizar().put(tanda);
        enbotellar(tanda);
    }

    /**
     * Produce la tanda ya enbotellada.
     *
     * @param tanda Tanda ya enbotellada.
     * @throws InterruptedException Error al producir.
     */
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Enbarrilada");
        tanda.setFechaFinal(new Timestamp(System.currentTimeMillis()));
        getTandasActualizar().put(tanda);
        tandasTransportar.add(tanda.getId());
        System.out.println("Tanda terminada lista >>>> \n " + tanda + " \n Fecha >> " + tanda.getFechaFinal());
        getBufferTandasColocar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    /**
     * Cambia el estado de la tanda de destilada a enbotellada.
     *
     * @param tanda Tanda a enbotellar.
     * @throws InterruptedException Error al enbotellar.
     */
    private void enbotellar(Tanda tanda) throws InterruptedException {
        ArrayList<Producto> mezcalesEliminar = new ArrayList(), barriles = new ArrayList();
        int total = tanda.getCantidadPinias(), cont = 0;
        ajustarBarra(tanda.getColor());
        for (int i = 0; i < numAnejamiento(tanda); i++) {
            for (Producto mezcal : tanda.getPinias()) {
                sleep(2000);
                System.out.println(mezcal);
                Mezcal m = (Mezcal) mezcal;
                if (i == 0) {
                    Barril barril = new Barril(m.getTipo());
                    barril.cambiarEstado('E');
                    barriles.add(barril);
                    mezcalesEliminar.add(mezcal);
                    System.out.println(barril);
                }
                cont++;
                actualizarBarra(cont * 100 / total);
            }
            cont = 0;
            getBarra().setString("Fase " + (i + 1));
            sleep(1000);
        }
        tanda.getPinias().removeAll(mezcalesEliminar);
        tanda.getPinias().addAll(barriles);
        producir(tanda);
    }

    /**
     * Calcula los meses de añejamiento de una cierta tanda.
     *
     * @param t Tanda de la que se calcula los meses de añejamiento.
     * @return El número de meses para añejar.
     */
    private int numAnejamiento(Tanda t) {
        int numAnejamientos = 1;
        /** Blanco 2 meses * Resposado 12 meses * Añejo 24 meses * Madurado 10 meses */
        int tipo = t.getTipoMezcal();
        if (tipo == 1)
            numAnejamientos = 12; // Por 2 meses
        else if (tipo == 2)
            numAnejamientos = 6;
        else if (tipo == 3)
            numAnejamientos = 5;
        return numAnejamientos;
    }

    /**
     * Establece el array de ID para marcar las tandas a trasnportar.
     *
     * @param tandasTransportar Array a establecer.
     */
    public void setTandasTransportar(ArrayList <Integer> tandasTransportar){
        this.tandasTransportar = tandasTransportar;
    }
}
