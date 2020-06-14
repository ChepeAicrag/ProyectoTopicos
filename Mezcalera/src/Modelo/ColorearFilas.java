/** Clase creada para poner de colores la tabla */

package Modelo;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilas extends DefaultTableCellRenderer {

    private final int columna_patron;
    private boolean op;

    public ColorearFilas(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    public ColorearFilas(int Colpatron, boolean op) {
        this.columna_patron = Colpatron;
        this.op = op;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        Font font = new Font("Helvetica", Font.ITALIC, 12);
        table.setForeground(Color.BLACK);
        table.setFont(font); // Colocar la fuente de mi gusto
        table.setGridColor(Color.BLACK);
        if(table.getValueAt(row, columna_patron).toString().equals("Registrada")) {
            setForeground(Color.RED);
        }else{
                setForeground(Color.BLUE);
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        if (Selected)
            table.setSelectionBackground(Color.LIGHT_GRAY);
        return this;
    }
}
