
package Modelo;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Clase para hacer la personalización de las tablas.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public class ColorearFilas extends DefaultTableCellRenderer {

    // Constante de instancia - Columna a modificar.
    private final int columna_modificar;

    /**
     * Constructor para objetos de la clase ColorearFilas.
     */
    public ColorearFilas(int Colpatron) {
        this.columna_modificar = Colpatron;
    }
    /**
     * Método para realizar la modificación de la tabla
     * de acuerdo a nuestra necesidad.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        Font font = new Font("Helvetica", Font.ITALIC, 12);
        table.setForeground(Color.BLACK);
        table.setFont(font); // Colocar la fuente de mi gusto
        table.setGridColor(Color.BLACK);
        if(table.getValueAt(row, columna_modificar).toString().equals("Registrada"))
            setForeground(Color.RED);
        else
                setForeground(Color.BLUE);
        setHorizontalAlignment(SwingConstants.CENTER);
        if (Selected)
            table.setSelectionBackground(Color.LIGHT_GRAY);
        return this;
    }
}
