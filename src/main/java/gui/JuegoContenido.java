package gui;

import models.Comida;
import models.Serpiente;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JuegoContenido extends JPanel {

    private Serpiente serpiente = new Serpiente();
    private Comida comida = new Comida();

    public JuegoContenido(KeyListener listener, Serpiente serpiente, Comida comida) {
        this.serpiente = serpiente;
        this.comida = comida;
        this.setPreferredSize(new Dimension(Constants.PANTALLA, Constants.PANTALLA));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(listener);
    }

    public void dibujarSerpiente(Graphics g) {

        //Dibujar Snake
        for (int i = 0; i < serpiente.getCuerpo_serpiente(); i++) {
            g.setColor(Color.green);
            g.fillRect(serpiente.getSnakeX()[i], serpiente.getSnakeY()[i], Constants.CUADRO_SIZE, Constants.CUADRO_SIZE);

            g.setColor(Color.black);
            g.drawRect(serpiente.getSnakeX()[i], serpiente.getSnakeY()[i], Constants.CUADRO_SIZE, Constants.CUADRO_SIZE);
        }

        //Dibujar ojos Serpiente
        g.setColor(Color.black);
        g.fillOval(serpiente.getSnakeX()[0] + 5, serpiente.getSnakeY()[0] + 5, 5, 5);
        g.fillOval(serpiente.getSnakeX()[0] + 15, serpiente.getSnakeY()[0] + 5, 5, 5);

        repaint();
    }

    public void dibujarComida(Graphics g) {
        //Dibujar Comida
        g.setColor(Color.red);
        g.fillOval(comida.getComidaX(), comida.getComidaY(), Constants.CUADRO_SIZE, Constants.CUADRO_SIZE);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Dibujar Cuadricula
        for (int i = 0; i < Constants.CUADRADOS_EN_PARALELO; i++) {
            g.drawLine(0, i * Constants.CUADRO_SIZE, Constants.PANTALLA, i * Constants.CUADRO_SIZE);
            g.drawLine(i * Constants.CUADRO_SIZE, 0, i * Constants.CUADRO_SIZE, Constants.PANTALLA);
        }

        dibujarSerpiente(g);
        dibujarComida(g);
    }


}
