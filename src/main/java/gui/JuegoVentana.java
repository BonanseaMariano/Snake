package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class JuegoVentana extends JFrame {

    public JuegoVentana() {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void cargarPanel(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.pack();
        this.repaint();
        panel.requestFocus();
    }
}
