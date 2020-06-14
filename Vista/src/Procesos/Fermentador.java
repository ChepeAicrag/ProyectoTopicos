package Procesos;

import java.awt.*;

public class Fermentador extends Equipo{

    public Fermentador(int id, BufferTandas bufferPiniasMolidas, BufferTandas bufferPiniasFermentadas){
        super(id, bufferPiniasMolidas, bufferPiniasFermentadas);
    }

    @Override
    public void run() {
        while (true) {
            try {
                setAvailable(false);
                Color color = getBarra().getBackground();
                consumir();
                System.out.println("Fermentador terminado");
                actualizarBarra(0);
                ajustarBarra(color);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
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
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('F'); // Triturada o molida
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }
}
