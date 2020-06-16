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

    @Override
    public void cambiarEstado(char estado) {
        super.setEstado(estado);
    }

    public Date getAnioCosecha() {
        return anioCosecha;
    }

    public void setAnioCosecha(Date anioCosecha) {
        this.anioCosecha = anioCosecha;
    }
}
