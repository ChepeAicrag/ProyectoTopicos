/** Renombara como Cortador */
package Procesos;

import java.awt.*;
import java.sql.Timestamp;

public class Cortador  extends Equipo{

    public Cortador(int id, BufferTandas bufferTandas, BufferTandas bufferPiniasCortadas){
        super(id, bufferTandas, bufferPiniasCortadas);
    }

    @Override
    public void run() {
        while(true){
            try {
                setAvailable(false);
                Color color = getBarra().getBackground();
                consumir();
                System.out.println("Corte terminado");
                actualizarBarra(0);
                ajustarBarra(color);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove();
        tanda.setId_Cortador(getIdentificador());
        tanda.setFechaInicio(new Timestamp(System.currentTimeMillis()));
        getEtqBarra().setForeground(Color.WHITE);
        getEtqBarra().setDatoBarra("Tanda: " + tanda.getId());
        cortar(tanda);
    }

    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Cortado");
        getTandasActualizar().put(tanda);
        getBufferTandasColocar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    private synchronized void cortar(Tanda tanda) throws InterruptedException{
        int total = tanda.getCantidadPinias(), // Este es el 100
                cont = 0;
        ajustarBarra(tanda.getColor());
        for (Object pinia : tanda.getPinias()) {
            sleep(2000);
            System.out.println(pinia);
            ((Pinia)(pinia)).setEstatus('C');
            System.out.println(pinia);
            cont++;
            actualizarBarra(cont * 100 / total);
        }
        producir(tanda);
    }

}
