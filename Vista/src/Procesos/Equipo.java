package Procesos;

import Componentes.ECP;

import javax.swing.*;
import java.awt.*;

public abstract class Equipo extends Thread {

    private int id;
    private boolean isAvailable;
    private JProgressBar barra;
    private Color color = Color.RED;
    private ECP etqBarra;



    public int getIdentificador() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public JProgressBar getBarra() {
        return barra;
    }

    public void setBarra(JProgressBar barra) {
        this.barra = barra;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ECP getEtqBarra() {
        return etqBarra;
    }

    public void setEtqBarra(ECP etqBarra) {
        this.etqBarra = etqBarra;
    }
}
