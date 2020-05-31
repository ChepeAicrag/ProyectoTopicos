/*
 * Para el informe final
 */

package Modelo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author García García José Ángel
 */
public class ModeloTablaInforme extends AbstractTableModel{

    private List<Object[]> dato;
    private String encabezado[] = new String[]{
            "IdTanda","Tipo Maguey","% Alcohol","Tipo Mezcal","N° Piñas","Estatus",
            "Cortado","Horneado","Molido","Fermentado","Destilado","Enbotellado",
            "Consumidor","Fecha Inicio","Fecha Final"};
    private Class tipos[] = new Class[]{
            String.class,String.class,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class};
    
    public void setDatos(List<Object[]> d) {
        dato = d;
    }

    @Override
    public Class getColumnClass(int c){
        return tipos[c];
    }
    
    @Override
    public int getRowCount() {
        try{
        return dato.size();
        }catch(Exception e){
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return encabezado.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return dato.get(r)[c];
    }
    @Override
    public String getColumnName(int col){
        return encabezado[col];
    }
    
}
