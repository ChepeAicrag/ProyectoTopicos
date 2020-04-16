package Vista;

import Controlador.Controlador;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.Vista_Registro;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author García García José Ángel, Chávez Sánchez Kevin Edilberto
 */
public class Vista_Login extends JFrame {

    private JLabel etqUsuario, etqContraseña, etqRegistrate;
    private JCheckBox cbRecuerdame;
    public JTextField txtUsuario;
    public JPasswordField txtContraseña;
    public JButton btnIngresar, btnRegistrar;
    private SpringLayout sp;
    private JPanel pPer, contenedor;
    private Vista_Registro Ventana_Registro;
    private Controlador c;
    private Hilo hilo;
    
    class Hilo extends Thread{
        public void run(){
            setSize(450, 350);
            setVisible(true);
            setLocationRelativeTo(null);
            add(principal());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
    
    public Vista_Login() {
        hilo = new Hilo();
        hilo.start();
       }

    public JPanel principal() {
        sp = new SpringLayout();
        contenedor = new JPanel(sp);
        contenedor.setBackground(Color.decode("#233D53"));
        btnIngresar = new JButton("Ingresar");
        pPer = PanelDataUser();
        addPanelDataPer();
        btnIngresar.setEnabled(false);
        btnIngresar.setActionCommand("ingresar");
        sp.putConstraint(SpringLayout.NORTH, btnIngresar, 5, SpringLayout.SOUTH, pPer);
        sp.putConstraint(SpringLayout.WEST, btnIngresar, 300, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, btnIngresar, -2, SpringLayout.SOUTH, contenedor);
        contenedor.add(btnIngresar);
        contenedor.updateUI();
        pPer.setVisible(true);
       
        return contenedor;
    }

    public void habilitarBoton() {
        if (!txtUsuario.getText().isEmpty() && !txtContraseña.getText().isEmpty()) {
            btnIngresar.setEnabled(true);
        } else {
            btnIngresar.setEnabled(false);
        }
    }

    public void addPanelDataPer() {
        contenedor.add(pPer);
        sp.putConstraint(SpringLayout.NORTH, pPer, 12, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, pPer, 12, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, pPer, -12, SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, pPer, -30, SpringLayout.SOUTH, contenedor);

    }

    public JPanel PanelDataUser() {
        SpringLayout s = new SpringLayout();
        JPanel panel = new JPanel(s);
        panel.setBorder(BorderFactory.createTitledBorder(" I N G R E S A R "));
        ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/userLogin.png"));
        ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel imagen = new JLabel(icono);
        panel.add(imagen);
        s.putConstraint(SpringLayout.NORTH, imagen, 60, SpringLayout.NORTH, panel);
        s.putConstraint(SpringLayout.WEST, imagen, 20, SpringLayout.WEST, panel);
        etqRegistrate = new JLabel("¿No tienes una cuenta?");
        panel.add(etqRegistrate);
        s.putConstraint(SpringLayout.NORTH, etqRegistrate, 165, SpringLayout.NORTH, panel);
        s.putConstraint(SpringLayout.WEST, etqRegistrate, 8, SpringLayout.WEST, panel);
        btnRegistrar = new JButton("¡Registrate!");
        btnRegistrar.setActionCommand("registrar_Login");
        panel.add(btnRegistrar);
        s.putConstraint(SpringLayout.NORTH, btnRegistrar, 190, SpringLayout.NORTH, panel);
        s.putConstraint(SpringLayout.WEST, btnRegistrar, 20, SpringLayout.WEST, panel);
        etqUsuario = new JLabel("Usuario : ");
        txtUsuario = new JTextField(16);
        panel.add(etqUsuario);
        s.putConstraint(SpringLayout.WEST, etqUsuario, 130, SpringLayout.WEST, panel);
        s.putConstraint(SpringLayout.NORTH, etqUsuario, 70, SpringLayout.NORTH, panel);
        panel.add(txtUsuario);
        s.putConstraint(SpringLayout.WEST, txtUsuario, 22, SpringLayout.EAST, etqUsuario);
        s.putConstraint(SpringLayout.NORTH, txtUsuario, 70, SpringLayout.NORTH, panel);
        txtUsuario.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                habilitarBoton();
            }
        });
        etqContraseña = new JLabel("Contraseña : ");
        txtContraseña = new JPasswordField(16);
        panel.add(etqContraseña);
        s.putConstraint(SpringLayout.NORTH, etqContraseña, 10, SpringLayout.SOUTH, etqUsuario);
        s.putConstraint(SpringLayout.WEST, etqContraseña, 130, SpringLayout.WEST, panel);
        panel.add(txtContraseña);
        s.putConstraint(SpringLayout.NORTH, txtContraseña, 10, SpringLayout.SOUTH, etqUsuario);
        s.putConstraint(SpringLayout.WEST, txtContraseña, 1, SpringLayout.EAST, etqContraseña);
        txtContraseña.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                habilitarBoton();
            }
        });
        cbRecuerdame = new JCheckBox("Recuerdame");
        panel.add(cbRecuerdame);
        s.putConstraint(SpringLayout.NORTH, cbRecuerdame, 40, SpringLayout.SOUTH, etqUsuario);
        s.putConstraint(SpringLayout.WEST, cbRecuerdame, 1, SpringLayout.EAST, etqContraseña);
        return panel;
    }

    private JFrame getFrame() {
        return this;
    }

    public void conectarControlador(Controlador c) {
        btnRegistrar.addActionListener(c);
        btnIngresar.addActionListener(c);
    }

    public Vista_Registro getVistaRegistro() {
        return Ventana_Registro;
    }
        
    public Thread getHilo(){
        return hilo;
    }
    
}
