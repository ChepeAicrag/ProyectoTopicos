/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 * 
 * @author García García José Ángel
 */
public class Vista_Productor extends JFrame{
    
    public ModeloTablaMezcal mtm;
    
    public Vista_Productor(){
        mtm = new ModeloTablaMezcal();
    }
    
    public void iniciar(){
        setSize(800, 700);
        setVisible(true);
        add(principal());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    }
    public JPanel principal(){
        JPanel p = new JPanel();
        JTable tabla = new JTable(mtm);
        JTableHeader hTab = tabla.getTableHeader();
        tabla.setTableHeader(hTab);
        p.add(tabla);
        return p;
    }
}
