package Procesos;

/**
 * Clase para el equipo Fermentador.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Fermentador extends Equipo{

    /**
     * Constructor para objetos de Fermentador.
     * @param id Id que tendrá el equipo Fermentador.
     * @param bufferPiniasMolidas Buffer del que se consume las tandas.
     * @param bufferPiniasFermentadas Buffer al que se produce tandas.
     */
    public Fermentador(int id, BufferTandas bufferPiniasMolidas, BufferTandas bufferPiniasFermentadas){
        super(id, bufferPiniasMolidas, bufferPiniasFermentadas);
        setTexto("Fermentador terminó");
    }

    /**
     * Consume la tandas molida para fermentarla.
     *
     * @throws InterruptedException Error al consumir.
     */
    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove();
        tanda.setId_Fermentador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        tanda.setEstado("Fermentando");
        getTandasActualizar().put(tanda);
        fermentar(tanda);
    }

    /**
     * Produce la tanda ya fermentada.
     *
     * @param tanda Tanda ya fermentada.
     * @throws InterruptedException Error al producir.
     */
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Fermentada");
        getTandasActualizar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        getBufferTandasColocar().put(tanda);
        setAvailable(true);
    }

    /**
     * Cambia el estado de la tanda a fermentada.
     *
     * @param tanda Tanda a fermentar.
     * @throws InterruptedException Error al fermentar.
     */
    private void fermentar(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(), cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000);
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('F');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }
}
