/** Renombara como Cortador */
package Procesos;

import java.sql.Timestamp;

public class Cortador  extends Equipo{

    public Cortador(int id, BufferTandas bufferTandas, BufferTandas bufferPiniasCortadas){
        super(id, bufferTandas, bufferPiniasCortadas);
        setTexto("Cortador terminó");
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove();
        tanda.setId_Cortador(getIdentificador());
        tanda.setFechaInicio(new Timestamp(System.currentTimeMillis()));
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
        int total = tanda.getCantidadPinias(), cont = 0;
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
