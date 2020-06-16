package Procesos;

/**
 * Clase controladora, aquí se encuentra la parte lógica del proyecto.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Barril extends Producto{

    private String capacidadBarril;

    /**
     * Constructor para objetos de Barril.
     *
     * @param tipo Tipo de Barril.
     */
    public Barril(int tipo) {
        super(tipo);
    }

    @Override
    public void cambiarEstado(char estado) {
        setEstado('L');
    }

    public String getCapacidadBarril() {
        return capacidadBarril;
    }

    public void setCapacidadBarril(String capacidadBarril) {
        this.capacidadBarril = capacidadBarril;
    }
}
