package Procesos;

/**
 * Clase para el equipo Molino.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Molino extends Equipo{

    /**
     * Constructor para objetos de Molino.
     * @param id Id que tendrá el equipo destilador.
     * @param bufferPiniasHorneadas Buffer del que se consume las tandas.
     * @param bufferPiniasMolidas Buffer al que se produce las tandas.
     */
    public Molino(int id,BufferTandas bufferPiniasHorneadas, BufferTandas bufferPiniasMolidas){
        super(id, bufferPiniasHorneadas, bufferPiniasMolidas);
        setTexto("Molino trabajó");
    }

    /**
     * Consume la tanda horneada para triturar.
     *
     * @throws InterruptedException Error al consumir.
     */
    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove(); // Quita de las horneadas
        tanda.setId_Triturador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        tanda.setEstado("Moliendo");
        getTandasActualizar().put(tanda);
        triturar(tanda);
    }

    /**
     * Produce la tanda ya triturada.
     *
     * @param tanda Tanda ya triturada.
     * @throws InterruptedException Error al producir.
     */
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Trituarada");
        getTandasActualizar().put(tanda);
        getBufferTandasColocar().put(tanda); 
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    /**
     * Cambia el estado de la tanda de horneada a triturada.
     *
     * @param tanda Tanda a triturar.
     * @throws InterruptedException Error al triturar.
     */
    private synchronized void triturar(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(),
                cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); 
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('T');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }
}
