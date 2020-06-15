package Procesos;

import Componentes.ECP;
import javax.swing.JProgressBar;
import javax.swing.BorderFactory;
import java.awt.Color;

/**
 * Clase para Equipo usado en el proceso.
 * @author García García José Ángel
 * @author Sánchez Chávez Kevin Edilberto
 * @version 1.0 14/06/2020
 */
public abstract class Equipo extends Thread implements Consumidor, Productor{

    // Variable de instancia - Identificador.
    private int id;

    // Variable de instancia - Disponibilidad.
    private boolean isAvailable;

    // Variable de instancia - Texto para identificar equipo.
    private String texto;

    // Variable de instancia - Barra de progreso.
    private JProgressBar barra;

    // Variable de instancia - Color de texto.
    private Color color = Color.RED;

    // Variable de instancia - Etiqueta redonda de la barra.
    private ECP etqBarra;

    // Variables de instancia - Buffers de donde tomar, actualizar y colocar respectivamente una tanda.
    private BufferTandas bufferTandasTomar, tandasActualizar, bufferTandasColocar;

    /**
     * Constructor para objetos de Equipo
     * @param id Id que tendrá el equipo.
     * @param bufferTandasTomar Buffer del que se consume las tandas.
     * @param bufferTandasColocar Buffer al que se produce tandas.
     */
    public Equipo(int id, BufferTandas bufferTandasTomar, BufferTandas bufferTandasColocar){
        setID(id);
        setBufferTandasTomar(bufferTandasTomar);
        setBufferTandasColocar(bufferTandasColocar);
        setAvailable(true);
    }

    /**
     * Método que genera la acción del equipo.
     */
    @Override
    public void run() {
        while (true) {
            try {
                setAvailable(true);
                color = getBarra().getBackground();
                consumir();
                System.out.println(texto);
                actualizarBarra(0);
                ajustarBarra(color);
            } catch (InterruptedException ex) {
                System.err.println(ex.getCause());
            }
        }
    }

    /**
     * Retorna el identificador único del equipo.
     * @return identificado del equipo.
     */
    public int getIdentificador() {
        return id;
    }

    /**
     * Estalbece el identificador.
     *
     * @param id Valor a establecer.
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Retorna si el equipo está disponible.
     *
     * @return true si está disponible y false de lo contario.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Establece la disponibilidad del equipo.
     * @param available Estado a establecer.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    /**
     * Retorna el texto que tiene el equipo.
     *
     * @return Texto perteneciente al equipo.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Establece el texto a imprimir.
     *
     * @param texto Texto a imprimir.
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Retorna la barra que pertenece al equipo.
     *
     * @return Barra correspondiente al equipo.
     */
    public JProgressBar getBarra() {
        return barra;
    }

    /**
     * Establece la barra que el equipo controlará.
     *
     * @param barra Barra a establecer.
     */
    public void setBarra(JProgressBar barra) {
        this.barra = barra;
    }

    /**
     * Retorna el color que tiene la barra.
     *
     * @return Color de la barra.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establecer color a la barra.
     *
     * @param color Color a establecer.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Retorna la etiqueta correspondiente al equipo.
     *
     * @return Etiqueta redonda.
     */
    public ECP getEtqBarra() {
        return etqBarra;
    }

    /**
     * Establece una barra identificadora al equipo.
     *
     * @param etqBarra La barra que controlará el equipo.
     */
    public void setBarraIdentificador(ECP etqBarra) {
        this.etqBarra = etqBarra;
        setBarra(etqBarra.getBarra());
    }

    /**
     * Retorna el buffer de tandas de las que toma el equipo.
     *
     * @return Buffer de tandas para consumir.
     */
    public BufferTandas getBufferTandasTomar() {
        return bufferTandasTomar;
    }

    /**
     * Establece el buffer de tandas del que tomará el equipo.
     *
     * @param bufferTandasTomar Buffer de tandas a establecer.
     */
    public void setBufferTandasTomar(BufferTandas bufferTandasTomar) {
        this.bufferTandasTomar = bufferTandasTomar;
    }

    /**
     * Retorna el buffer de tandas que se actualizan.
     *
     * @return Buffer de tandas a actualizar.
     */
    public BufferTandas getTandasActualizar() {
        return tandasActualizar;
    }

    /**
     * Establece el buffer de tandas a actualizar.
     *
     * @param tandasActualizar Buffer de tandas a actualizar.
     */
    public void setTandasActualizar(BufferTandas tandasActualizar) {
        this.tandasActualizar = tandasActualizar;
    }

    /**
     * Retorna el buffer de tandas donde se colocan las tandas producidas.
     *
     * @return Buffer de tandas a colocar.
     */
    public BufferTandas getBufferTandasColocar() {
        return bufferTandasColocar;
    }

    /**
     * Establece el buffer de tandas donde se colocaran las tandas producidas.
     *
     * @param bufferTandasColocar Buffer de tandas a establecer.
     */
    public void setBufferTandasColocar(BufferTandas bufferTandasColocar) {
        this.bufferTandasColocar = bufferTandasColocar;
    }

    /**
     * Actualiza el valor de la barra del equipo.
     *
     * @param val Valor a establecer a la barra.
     */
    public void actualizarBarra(int val){
        barra.setValue(val);
        if(val != 0)
            barra.setString(val + "%");
        else
            barra.setString("Libre...");
    }

    /**
     * Ajusta colores de Foreground y Background de la barra.
     *
     * @param color Color a establecer.
     */
    public void ajustarBarra(Color color){
        barra.setBackground(color); // Color de la barra que se rellena
        barra.setForeground(Color.black); // Color de la barra que rellena
        if(this.color != color)
            barra.setBorder(BorderFactory.createLineBorder(color));
        else
            barra.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
