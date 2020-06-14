package Procesos;

import java.awt.*;
import java.util.ArrayList;

public class Destilador2 extends Equipo{

    public Destilador2(int id, BufferTandas bufferPiniasFermentadas, BufferTandas bufferMezcalDestilado){
        super(id, bufferPiniasFermentadas, bufferMezcalDestilado);
    }

    @Override
    public void run() {
        while (true) {
            try {
                setAvailable(false);
                Color color = getBarra().getBackground();
                consumir();
                System.out.println("Destilador terminó");
                actualizarBarra(0);
                ajustarBarra(color);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove(); // Quita de las fermentadas
        tanda.setId_Destilador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        destilar(tanda); // Las consume para hornear
    }

    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Destilada");
        getTandasActualizar().put(tanda);
        getBufferTandasColocar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    /** Produce la pinia molida*/
    private void destilar(Tanda tanda) throws InterruptedException{
        ArrayList<Object> piniasEliminar = new ArrayList();
        ArrayList<Object> mezcales = new ArrayList();
        int total = tanda.getCantidadPinias(),
                cont = 0;
        ajustarBarra(tanda.getColor());
        for(int  i = 0; i < numDestilados(tanda);i++){
            for (Object pinia : tanda.getPinias()) {
                sleep(2000);
                System.out.println(pinia);
                if(i == 0){ // Para que solo 1 vez los agregue
                    Pinia p = (Pinia) pinia;
                    Mezcal mezcal = new Mezcal(p.getTipo()); // Asignamos el tipo
                    mezcal.setEstatus('D');
                    mezcales.add(mezcal);
                    piniasEliminar.add(pinia);
                    System.out.println(mezcal);
                }
                cont++;
                actualizarBarra(cont * 100 / total);
            }
            cont = 0;
            getBarra().setString("Destilado " + (i + 1));
            sleep(1000);
        }
        tanda.getPinias().removeAll(piniasEliminar); // Quita las piñas
        tanda.getPinias().addAll(mezcales); // Agrega los mezcales
        producir(tanda); // Ahora la tanda ya lleva objetos de mezcal
    }

    private int numDestilados(Tanda t){
        int numDestilados = 1;
        if (t.getPorcentajeAlcohol() == 48)
            numDestilados = 2;
        else if (t.getPorcentajeAlcohol() == 37)
            numDestilados = 3;
        return numDestilados;
    }
}
