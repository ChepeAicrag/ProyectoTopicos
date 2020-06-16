package Procesos;

/**
 * Clase para representar el Mezcal de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Mezcal extends Producto{

    // Variable de instancia - Sabor del mezcal.
    private String sabor;

    /**
     * Constructor para objetos de Mezcal.
     *
     * @param tipo Tipo de maguey.
     */
    public Mezcal(int tipo){
        super(tipo);
    }

    /**
     * Cambia el estado del mezcal.
     *
     * @param estado Estado a establecer.
     */
    @Override
    public void cambiarEstado(char estado) {
        setEstado(estado);
    }

    /**
     * Retorna el sabor del mezcal.
     *
     * @return Sabor del mezcal.
     */
    public String getSabor() {
        return sabor;
    }

    /**
     * Establecer el sabor al mezcal.
     *
     * @param sabor Sabora a establecer.
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    /**
     * Retorna una representación del mezcal.
     *
     * @return Datos del mezcal.
     */
    @Override
    public String toString() {
        return super.toString() + "Mezcal{" +
                "sabor='" + sabor + '\'' +
                '}';
    }
}
