package Procesos;

import java.util.Random;

/**
 * Clase para el equipo Horno.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Horno extends Equipo{

    // Variable de instancia - Cantidad de leña disponible para el horno. Independienteme de la BD.
    private int cantidadLenia;

    /**
     * Constructor para objetos de Horno.
     * @param id Id que tendrá el equipo destilador.
     * @param bufferPiniasCortadas Buffer del que se consume las tandas.
     * @param bufferPiniasHorneadas Buffer al que se produce las tandas.
     */
    public Horno(int id, BufferTandas bufferPiniasCortadas, BufferTandas bufferPiniasHorneadas){
        super(id, bufferPiniasCortadas, bufferPiniasHorneadas);
        setTexto("Horno consumió");
    }

    /**
     * Consume la tanda cortada para hornear.
     *
     * @throws InterruptedException Error al consumir.
     */
    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove();
        tanda.setId_Horneador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        tanda.setEstado("Horneando");
        getTandasActualizar().put(tanda);
        hornear(tanda);
    }

    /**
     * Produce la tanda ya horneada.
     *
     * @param tanda Tanda ya horneada.
     * @throws InterruptedException Error al producir.
     */
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Horneada");
        getTandasActualizar().put(tanda); // Le indica al controlador que actulice la BD
        getBufferTandasColocar().put(tanda); // Las produce
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
        cantidadLenia -= new Random().nextInt(500);
    }

    /**
     * Cambia el estado de la tanda de cortada a horneada.
     * @param tanda Tanda a hornear.
     * @throws InterruptedException Error al hornear.
     */
    private synchronized void hornear(Tanda tanda) throws InterruptedException{
        if(cantidadLenia <= 0){
            Thread.sleep(3000);
            cantidadLenia += 10000;
        }
        int total = tanda.getCantidadPinias(), cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000);
            System.out.println(pinia);
            ((Pinia)(pinia)).cambiarEstado('H');
            cont++;
            System.out.println(pinia);
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }

    /**
     * Retorna la cantidad de leña actual.
     *
     * @return Cantidad de leña.
     */
    public int getCantidadLenia() {
        return cantidadLenia;
    }

    /**
     * Establece la cantidad de leña disponible para el horno.
     *
     * @param cantidadLenia Cantidad a establecer.
     */
    public void setCantidadLenia(int cantidadLenia) {
        this.cantidadLenia = cantidadLenia;
    }
}
