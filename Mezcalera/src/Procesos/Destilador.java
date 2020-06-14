package Procesos;

import java.util.ArrayList;

public class Destilador extends Equipo{

    public Destilador(int id, BufferTandas bufferPiniasFermentadas, BufferTandas bufferMezcalDestilado){
        super(id, bufferPiniasFermentadas, bufferMezcalDestilado);
        setTexto("Destilador terminó");
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
