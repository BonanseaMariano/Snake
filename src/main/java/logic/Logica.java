package logic;

import gui.JuegoContenido;
import gui.JuegoVentana;
import models.Comida;
import models.Serpiente;
import utils.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Logica implements ActionListener {
    private static Logica instancia;

    //Snake,
    private Serpiente serpiente;
    private char direccion = 'd';

    //Comida
    private Comida comida;

    //Timer,
    private boolean running = true;
    private Timer timer;

    //GUI
    private JuegoVentana juego;
    private JuegoContenido panel;

    //Otros,
    private Random random = new Random();

    public static Logica getInstance() {
        if (instancia == null) {
            instancia = new Logica();
        }
        return instancia;
    }

    private Logica() {
        serpiente = new Serpiente();
        comida = new Comida();
        juego = new JuegoVentana();
        panel = new JuegoContenido(new Controles(), serpiente, comida);
        juego.cargarPanel(panel);
        juego.setLocationRelativeTo(null);
        this.iniciarJuego();
    }

    public void iniciarJuego() {
        agregarComida();
        timer = new Timer(Constants.DELAY, this);
        timer.start();
    }

    public void agregarComida() {
        comida.setComidaX(random.nextInt(Constants.CUADRADOS_EN_PARALELO) * Constants.CUADRO_SIZE);
        comida.setComidaY(random.nextInt(Constants.CUADRADOS_EN_PARALELO) * Constants.CUADRO_SIZE);
    }

    private void moverSnake() {

        //Temporales
        int[] sX = serpiente.getSnakeX();
        int[] sY = serpiente.getSnakeY();

        //Cuerpo
        for (int i = serpiente.getCuerpo_serpiente(); i > 0; i--) {
            serpiente.setSnakeX(i, sX[i - 1]);
            serpiente.setSnakeY(i, sY[i - 1]);
        }

        //Cabeza
        switch (direccion) {
            case 'w':
                serpiente.setSnakeY(0, sY[0] - Constants.CUADRO_SIZE);
                break;
            case 's':
                serpiente.setSnakeY(0, sY[0] + Constants.CUADRO_SIZE);
                break;
            case 'a':
                serpiente.setSnakeX(0, sX[0] - Constants.CUADRO_SIZE);
                break;
            case 'd':
                serpiente.setSnakeX(0, sX[0] + Constants.CUADRO_SIZE);
                break;
        }
    }

    private void checarComida() {
        if (serpiente.getSnakeX()[0] == comida.getComidaX() && serpiente.getSnakeY()[0] == comida.getComidaY()) {
            serpiente.incrementarCuerpo();
            agregarComida();
        }
    }

    private void checarColisiones() {
        //Temporales
        int[] sX = serpiente.getSnakeX();
        int[] sY = serpiente.getSnakeY();

        //Colisiones con pantalla
        if (sX[0] > Constants.PANTALLA - Constants.CUADRO_SIZE || sX[0] < 0 || sY[0] > Constants.PANTALLA - Constants.CUADRO_SIZE || sY[0] < 0) {
            finJuego();
        }

        //Colisiones con el cuerpo
        for (int i = 1; i < serpiente.getCuerpo_serpiente(); i++) {
            if (sX[0] == sX[i] && sY[0] == sY[i]) {
                finJuego();
            }
        }
    }

    private void finJuego() {
        running = false;
        timer.stop();
        direccion = 'd';
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moverSnake();
            checarComida();
            checarColisiones();
        }
    }

    //Inner class para los controles
    private class Controles extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'w':
                    if (direccion != 's') {
                        direccion = 'w';
                    }
                    break;
                case 'a':
                    if (direccion != 'd') {
                        direccion = 'a';
                    }
                    break;
                case 's':
                    if (direccion != 'w') {
                        direccion = 's';
                    }
                    break;
                case 'd':
                    if (direccion != 'a') {
                        direccion = 'd';
                    }
                    break;
            }
        }
    }
}
