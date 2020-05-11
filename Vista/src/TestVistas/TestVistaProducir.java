/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestVistas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 
 * @author García García José Ángel
 */
public class TestVistaProducir extends JFrame{
    
    
    public TestVistaProducir(){
        setVisible(true);
        setSize(1080,680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        run();
        revalidate();
        
    }
    public static void main(String[] args) {
        new TestVistaProducir();
    }
    
    public void run(){
        ImageIcon img = new ImageIcon(
                getClass().getResource("/TestVistas/maguey.png"));
        String[] txts = {"Tobalá","Cuishe","Coyote","Tepeztate",
                        "Papalote","Cenizo","Estoquillo","Mexicano"};
        ImageIcon[] imagenes = {img,img,img,img,img,img,img,img};
        add(new Vista.VistaProducir(imagenes,txts));
    }
}
