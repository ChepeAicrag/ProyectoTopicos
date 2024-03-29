package Procesos;

import java.util.ArrayList;

/**
 * Clase para el equipo Destilador.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Destilador extends Equipo {

    // Variable de instancia - Tipo de tubo en el que se destila.
    private String tipoTubo, sabor;

    /**
     * Constructor para objetos de Destilador.
     *
     * @param id Id que tendrá el equipo destilador.
     * @param bufferPiniasFermentadas Buffer del que se consume las tandas.
     * @param bufferMezcalDestilado Buffer al que se produce las tandas.
     */
    public Destilador(int id, BufferTandas bufferPiniasFermentadas, BufferTandas bufferMezcalDestilado){
        super(id, bufferPiniasFermentadas, bufferMezcalDestilado);
        setTexto("Destilador terminó");
    }

    /**
     * Consume la tanda fermentada para destilar.
     *
     * @throws InterruptedException Error al consumir.
     */
    @Override
    public void consumir() throws InterruptedException {
        Tanda tanda = getBufferTandasTomar().remove();
        tanda.setId_Destilador(getIdentificador());
        getEtqBarra().setDatoBarra("Tanda " + tanda.getId());
        tanda.setEstado("Destilando");
        getTandasActualizar().put(tanda);
        destilar(tanda);
    }

    /**
     * Produce la tanda ya destilada.
     *
     * @param tanda Tanda ya destilada.
     * @throws InterruptedException Error al producir.
     */
    @Override
    public void producir(Tanda tanda) throws InterruptedException {
        tanda.setEstado("Destilada");
        getTandasActualizar().put(tanda);
        getBufferTandasColocar().put(tanda);
        getEtqBarra().setDatoBarra("---");
        setAvailable(true);
        tanda.getPinias().stream().forEach(o -> ((Mezcal) o).setSabor(sabor));
        tanda.getPinias().stream().forEach(x -> ((Mezcal)x).toString());
    }

    /**
     * Destila la tanda pasando de piñas a mezcal.
     *
     * @param tanda Tanda a destilar.
     * @throws InterruptedException Error al destilar.
     */
    private void destilar(Tanda tanda) throws InterruptedException{
        ArrayList<Producto> piniasEliminar = new ArrayList();
        ArrayList<Producto> mezcales = new ArrayList();
        int total = tanda.getCantidadPinias(),
                cont = 0;
        ajustarBarra(tanda.getColor());
        for(int  i = 0; i < numDestilados(tanda);i++){
            for (Producto pinia : tanda.getPinias()) {
                sleep(2000);
                System.out.println(pinia);
                if(i == 0){
                    Pinia p = (Pinia) pinia;
                    Mezcal mezcal = new Mezcal(p.getTipo());
                    mezcal.cambiarEstado('D');
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
        tanda.getPinias().removeAll(piniasEliminar);
        tanda.getPinias().addAll(mezcales);
        producir(tanda);
    }

    /**
     * Método para calcular el número de destilados a realizar de una tanda dada.
     *
     * @param t Tanda a la que se le calcula.
     * @return Cantidad de destilados a realizar.
     */
    private int numDestilados(Tanda t){
        int numDestilados = 1;
        sabor = " ";
        if (t.getPorcentajeAlcohol() == 48){
            numDestilados = 2;
            sabor = "Tuene";
        }
        else if (t.getPorcentajeAlcohol() == 37){
            numDestilados = 3;
            sabor = "Fuerte";
        }
        if(numDestilados == 1)
            sabor = "Muy Fuerte";
        return numDestilados;
    }

    /**
     * Retorna el tipo de tubo utilizado para destilar.
     *
     * @return Tipo de tubo.
     */
    public String getTipoTubo() {
        return tipoTubo;
    }

    /**
     * Establece el tipo de tubo a utilizar para destilar.
     *
     * @param tipoTubo Tipo a establecer.
     */
    public void setTipoTubo(String tipoTubo) {
        this.tipoTubo = tipoTubo;
    }
}
