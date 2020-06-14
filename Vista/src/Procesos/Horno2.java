package Procesos;

import java.awt.*;

public class Horno2 extends Equipo{

    public Horno2(int id, BufferTandas bufferPiniasCortadas, BufferTandas bufferPiniasHorneadas){
        super(id, bufferPiniasCortadas, bufferPiniasHorneadas);
    }

    @Override
    public synchronized void run(){
        while (true) {
            try {
                setAvailable(true);
                Color color = getBarra().getBackground();
                consumir();
                System.out.println("Horno consumió");
                actualizarBarra(0);
                ajustarBarra(color);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove(); // Quita de las cortadas
        tanda.setId_Horneador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        hornear(tanda); // Las consume para hornear
    }

    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Horneada");
        getTandasActualizar().put(tanda); // Le indica al controlador que actulice la BD
        getBufferTandasColocar().put(tanda); // Las produce
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    /** Produce la pinia horneada*/
    private synchronized void hornear(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(),
                cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000); // Tiempo por estimar
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('H');
            cont++;
            System.out.println(pinia);
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }
}
