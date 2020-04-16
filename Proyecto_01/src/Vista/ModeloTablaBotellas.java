/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author García García José Ángel
 */
public class ModeloTablaBotellas extends AbstractTableModel{
    private List<Object[]> dato;
    private String encabezado[] = new String[]{
            "No.Iden","Existencia","Precio","Presentacion","Fecha Env"};
    private Class tipos[] = new Class[]{
            String.class,String.class,String.class,String.class,String.class};
    
    @Override
    public Class getColumnClass(int c){
        return tipos[c];
    }
    
    @Override
    public int getRowCount() {
        return dato.size();
    }

    @Override
    public int getColumnCount() {
        return tipos.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return dato.get(r)[c];
    }
    @Override
    public String getColumnName(int col){
        return encabezado[col];
    }
    
    public void setDatos(List<Object[]> d){
        dato = d;
    }
}
