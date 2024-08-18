package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class JuegoVentana extends JFrame {

    private KeyListener keyListener;

    public JuegoVentana(KeyListener keyListener) {
        this.keyListener = keyListener;
        this.setTitle("Snake");
        cargarPanel(new JuegoContenido(keyListener));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void cargarPanel(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.pack();
        this.repaint();
    }
}
