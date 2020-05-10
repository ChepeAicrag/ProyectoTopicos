/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestVistas;

import javax.swing.ImageIcon;
import jdk.nashorn.internal.objects.NativeDebug;

/**
 * 
 * @author García García José Ángel
 */
public class TestVistaProducir {
    
    public static void main(String[] args) {
        new TestVistaProducir().run();
    }
    
    public void run(){
        ImageIcon img = new ImageIcon(
                getClass().getResource("/TestVistas/maguey.png"));
        String[] txts = {"Tobalá","Cuishe","Coyote","Tepeztate",
                        "Papalote","Cenizo","Estoquillo","Mexicano"};
        ImageIcon[] imagenes = {img,img,img,img,img,img,img,img};
        new Vista.VistaProducir(imagenes,txts);
    }
}
