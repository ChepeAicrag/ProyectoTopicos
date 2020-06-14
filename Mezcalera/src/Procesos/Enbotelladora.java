package Procesos;

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Enbotelladora extends Equipo{

    private ArrayList <Integer> tandasTransportar;

    public Enbotelladora(int id, BufferTandas bufferMezcalDestilado, BufferTandas bufferBarriles){
        super(id, bufferMezcalDestilado, bufferBarriles);
    }

    @Override
    public void run() {
        while (true) {
            try {
                setAvailable(false);
                Color color = getBarra().getBackground();
                consumir();
                actualizarBarra(0);
                ajustarBarra(color);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }

    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove(); // Quita de las destilados
        tanda.setId_Enbotelladora(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        enbotellar(tanda); // Las consume para hornear
    }

    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Enbarrilada");
        tanda.setFechaFinal(new Timestamp(System.currentTimeMillis()));
        getTandasActualizar().put(tanda);
        tandasTransportar.add(tanda.getId());
        System.out.println("Tanda terminada lista >>>> \n " + tanda + " \n Fecha >> " + tanda.getFechaFinal());
        getBufferTandasColocar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
    }

    private void enbotellar(Tanda tanda) throws InterruptedException {
        ArrayList<Object> mezcalesEliminar = new ArrayList(), barriles = new ArrayList();
        int total = tanda.getCantidadPinias(), cont = 0;
        ajustarBarra(tanda.getColor());
        for (int i = 0; i < numAnejamiento(tanda); i++) {
            for (Object mezcal : tanda.getPinias()) {
                sleep(2000);
                System.out.println(mezcal);
                Mezcal m = (Mezcal) mezcal;
                if (i == 0) {
                    Barril barril = new Barril(m.getTipo());
                    barril.setEstatus('E'); // Listo  E: Enbotellado
                    barriles.add(barril);
                    mezcalesEliminar.add(mezcal);
                    System.out.println(barril);
                }
                cont++;
                actualizarBarra(cont * 100 / total);
            }
            cont = 0;
            getBarra().setString("Fase " + (i + 1));
            sleep(1000);
        }
        tanda.getPinias().removeAll(mezcalesEliminar); // Quita los mezcales
        tanda.getPinias().addAll(barriles); // Agrega los barriles
        producir(tanda); // Ahora la tanda ya lleva objetos de mezcal
    }

    private int numAnejamiento(Tanda t) {
        int numAnejamientos = 1;
        /** Blanco 2 meses * Resposado 12 meses * Añejo 24 meses * Madurado 10 meses*/
        int tipo = t.getTipoMezcal();
        if (tipo == 1)
            numAnejamientos = 12; // Por 2 meses
        else if (tipo == 2)
            numAnejamientos = 6;
        else if (tipo == 3)
            numAnejamientos = 5;
        return numAnejamientos;
    }

    public void setTandasTransportar(ArrayList <Integer> tandasTransportar){
        this.tandasTransportar = tandasTransportar;
    }
}
