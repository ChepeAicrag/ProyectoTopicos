package Procesos;

public class Fermentador extends Equipo{

    public Fermentador(int id, BufferTandas bufferPiniasMolidas, BufferTandas bufferPiniasFermentadas){
        super(id, bufferPiniasMolidas, bufferPiniasFermentadas);
        setTexto("Fermentador terminó");
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove(); // Quita de las molidas
        tanda.setId_Fermentador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        fermentar(tanda); // Las consume para hornear
    }

    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Fermentada");
        getTandasActualizar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        getBufferTandasColocar().put(tanda); // Las produce
        setAvailable(true);
    }

    /** Produce la pinia molida*/
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
