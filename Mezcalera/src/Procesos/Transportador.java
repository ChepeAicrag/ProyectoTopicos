/*
 * Clase de Transportador, se encagará de transportar los barriles de mezcal
 */

package Procesos;

/**
 * 
 * @author García García José Ángel
 */
public class Transportador extends Thread{
    
    private int x, y, movimientoX, movimientoY, distanciaMaximaEnX, distanciaMaxinaEnY;
    private static Transportador yo;
    
    private Transportador(){
    
    }
    
    public static Transportador getInstancia(){
        if (yo == null) {
            yo = new Transportador();
        }
        return yo;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMovimientoX() {
        return movimientoX;
    }

    public void setMovimientoX(int movimientoX) {
        this.movimientoX = movimientoX;
    }

    public int getMovimientoY() {
        return movimientoY;
    }

    public void setMovimientoY(int movimientoY) {
        this.movimientoY = movimientoY;
    }

    public int getDistanciaMaximaEnX() {
        return distanciaMaximaEnX;
    }

    public void setDistanciaMaximaEnX(int distanciaMaximaEnX) {
        this.distanciaMaximaEnX = distanciaMaximaEnX;
    }

    public int getDistanciaMaxinaEnY() {
        return distanciaMaxinaEnY;
    }

    public void setDistanciaMaxinaEnY(int distanciaMaxinaEnY) {
        this.distanciaMaxinaEnY = distanciaMaxinaEnY;
    }
    
    @Override
    public void run(){
        while (true) {            
            if (x >= distanciaMaximaEnX - 50) 
                movimientoX = -1;
            if (y >= distanciaMaxinaEnY - 50)
                movimientoY = -1;
            if (x <= 0)
                movimientoX = 1;
            if (y <= 0)
                movimientoY = 1;
            mover();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }
    
    public void mover(){
        x += movimientoX;
        y += movimientoY;
    }
}
