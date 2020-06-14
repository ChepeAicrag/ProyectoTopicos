package Procesos;

public class Molino extends Equipo{

    public Molino(int id,BufferTandas bufferPiniasHorneadas, BufferTandas bufferPiniasMolidas){
        super(id, bufferPiniasHorneadas, bufferPiniasMolidas);
        setTexto("Molino trabajó");
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove(); // Quita de las horneadas
        tanda.setId_Triturador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        moler(tanda); // Las consume para hornear
    }

    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Trituarada");
        getTandasActualizar().put(tanda);
        getBufferTandasColocar().put(tanda); // Las produce
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    /** Produce la pinia molida*/
    private synchronized void moler(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(),
                cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('T');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }
}
