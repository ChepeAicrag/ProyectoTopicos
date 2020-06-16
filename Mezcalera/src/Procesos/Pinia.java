package Procesos;

import java.sql.Date;

/**
 * Clase para representar las Piñas de la tanda.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class Pinia extends Producto{

    private Date anioCosecha;

    /**
     * Constructor para objetos de Producto.
     *
     * @param tipo Tipo de Producto.
     */
    public Pinia(int tipo) {
        super(tipo);
    }

    /**
     * Cambiar el estado del producto.
     *
     * @param estado Estado a asignar.
     */
    @Override
    public void cambiarEstado(char estado) {
        super.setEstado(estado);
    }

    /**
     * Retorna el año de cosecha de la piña.
     *
     * @return El año de cosecha.
     */
    public Date getAnioCosecha() {
        return anioCosecha;
    }

    /**
     * Establece el año de cosecha a la piña.
     *
     * @param anioCosecha Fecha a establecer.
     */
    public void setAnioCosecha(Date anioCosecha) {
        this.anioCosecha = anioCosecha;
    }
}
