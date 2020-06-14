package Procesos;

import Componentes.ECP;

import javax.swing.*;
import java.awt.*;

public abstract class Equipo extends Thread implements Consumidor, Productor{

    private int id;
    private boolean isAvailable;
    private JProgressBar barra;
    private Color color = Color.RED;
    private ECP etqBarra;
    private BufferTandas bufferTandasTomar, tandasActualizar, bufferTandasColocar;

    public Equipo(int id, BufferTandas bufferTandasTomar, BufferTandas bufferTandasColocar){
        setID(id);
        setBufferTandasTomar(bufferTandasTomar);
        setBufferTandasColocar(bufferTandasColocar);
        setAvailable(true);
    }

    public int getIdentificador() {
        return id;
    }

    public void setID(int id) {
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

    public void setBarraIdentificador(ECP etqBarra) {
        this.etqBarra = etqBarra;
        setBarra(etqBarra.getBarra());
    }

    public BufferTandas getBufferTandasTomar() {
        return bufferTandasTomar;
    }

    public void setBufferTandasTomar(BufferTandas bufferTandasTomar) {
        this.bufferTandasTomar = bufferTandasTomar;
    }

    public BufferTandas getTandasActualizar() {
        return tandasActualizar;
    }

    public void setTandasActualizar(BufferTandas tandasActualizar) {
        this.tandasActualizar = tandasActualizar;
    }

    public BufferTandas getBufferTandasColocar() {
        return bufferTandasColocar;
    }

    public void setBufferTandasColocar(BufferTandas bufferTandasColocar) {
        this.bufferTandasColocar = bufferTandasColocar;
    }

    public void actualizarBarra(int val){
        barra.setValue(val);
        if(val != 0)
            barra.setString(val + "%");
        else
            barra.setString("Libre...");
    }

    public void ajustarBarra(Color color){
        barra.setBackground(color); // Color de la barra que se rellena
        barra.setForeground(Color.black); // Color de la barra que rellena
        if(this.color != color)
            barra.setBorder(BorderFactory.createLineBorder(color));
        else
            barra.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
