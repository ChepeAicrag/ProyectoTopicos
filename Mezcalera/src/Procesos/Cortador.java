package Procesos;

import java.sql.Timestamp;

/**
 * Clase para el equipo Cortador.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Cortador extends Equipo{

    // Variable de instancia - Cantidad de cuchillas que tiene el cortador
    private int cantidadCuchilla;

    /**
     * Constructor para objetos de Cortador.
     *
     * @param id Id que tendrá el equipo cortador.
     * @param bufferTandas Buffer del que se consume las tandas.
     * @param bufferPiniasCortadas Buffer al que se produce tandas.
     */
    public Cortador(int id, BufferTandas bufferTandas, BufferTandas bufferPiniasCortadas){
        super(id, bufferTandas, bufferPiniasCortadas);
        setTexto("Cortador terminó");
    }

    /**
     * Consume la tanda lista para cortarse.
     *
     * @throws InterruptedException Error al remover una tanda.
     */
    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove();
        tanda.setId_Cortador(getIdentificador());
        tanda.setFechaInicio(new Timestamp(System.currentTimeMillis()));
        getEtqBarra().setDatoBarra("Tanda: " + tanda.getId());
        tanda.setEstado("Cortando");
        getTandasActualizar().put(tanda);
        cortar(tanda);
    }

    /**
     * Produce la tanda ya cortada.
     *
     * @param tanda Tanda ya cortada.
     * @throws InterruptedException Error al producir tanda.
     */
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Cortado");
        getTandasActualizar().put(tanda);
        getBufferTandasColocar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    /**
     * Cambia el estado de la tanda a cortada.
     *
     * @param tanda Tanda a cortar.
     * @throws InterruptedException Error al cirtar la tanda.
     */
    private synchronized void cortar(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(), cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000);
            System.out.println(pinia);
            ((Pinia)(pinia)).cambiarEstado('C');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }

    /**
     * Retorna la cantidad de cuchillos que tiene el cotador.
     *
     * @return cantidad de cuchillas.
     */
    public int getCantidadCuchilla() {
        return cantidadCuchilla;
    }

    /**
     * Establece la cantidad de cuchillos que tendrá el cortador.
     *
     * @param cantidadCuchilla Cantidad de cuchillas.
     */
    public void setCantidadCuchilla(int cantidadCuchilla) {
        this.cantidadCuchilla = cantidadCuchilla;
    }
}
