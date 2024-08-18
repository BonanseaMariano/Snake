package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class JuegoContenido extends JPanel implements ActionListener {

    //Pantalla
    static final int PANTALLA = 600;
    static final int CUADRO_SIZE = 25;
    static final int CUADRADOS_EN_PARALELO = (int) PANTALLA / CUADRO_SIZE;

    //Snake,
    static final int TOTAL_CUERPO_SERPIENTE = (PANTALLA * PANTALLA) / CUADRO_SIZE;
    int[] snakeX = new int[TOTAL_CUERPO_SERPIENTE];
    int[] snakeY = new int[TOTAL_CUERPO_SERPIENTE];
    int cuerpo_serpiente = 3;
    char direccion = 'd'; //awsd

    //Comida,
    int comidaX;
    int comidaY;

    //Timer,
    boolean running = true;
    static final int DELAY = 100;
    Timer timer;

    //Otros,
    Random random = new Random();


    public JuegoContenido() {
        this.setPreferredSize(new Dimension(PANTALLA, PANTALLA));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new Controles());
        this.iniciarJuego();
    }

    public void iniciarJuego() {
        agregarComida();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void agregarComida() {
        comidaX = random.nextInt(CUADRADOS_EN_PARALELO) * CUADRO_SIZE;
        comidaY = random.nextInt(CUADRADOS_EN_PARALELO) * CUADRO_SIZE;
    }

    public void moverSnake() {
        //Cuerpo
        for (int i = cuerpo_serpiente; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        //Cabeza
        switch (direccion) {
            case 'w':
                snakeY[0] = snakeY[0] - CUADRO_SIZE;
                break;
            case 's':
                snakeY[0] = snakeY[0] + CUADRO_SIZE;
                break;
            case 'a':
                snakeX[0] = snakeX[0] - CUADRO_SIZE;
                break;
            case 'd':
                snakeX[0] = snakeX[0] + CUADRO_SIZE;
                break;
        }
    }

    public void checarComida() {
        if (snakeX[0] == comidaX && snakeY[0] == comidaY) {
            cuerpo_serpiente++;
            agregarComida();
        }
    }


    public void checarColisiones() {
        //Colisiones con pantalla
        if (snakeX[0] > PANTALLA - CUADRO_SIZE || snakeX[0] < 0 || snakeY[0] > PANTALLA - CUADRO_SIZE || snakeY[0] < 0) {
            running = false;
        }
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
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Dibujar Cuadricula
        for (int i = 0; i < CUADRADOS_EN_PARALELO; i++) {
            g.drawLine(0, i * CUADRO_SIZE, PANTALLA, i * CUADRO_SIZE);
            g.drawLine(i * CUADRO_SIZE, 0, i * CUADRO_SIZE, PANTALLA);
        }

        //Dibujar Comida
        g.setColor(Color.red);
        g.fillOval(comidaX, comidaY, CUADRO_SIZE, CUADRO_SIZE);

        //Dibujar Snake
        g.setColor(Color.green);
        for (int i = 0; i < cuerpo_serpiente; i++) {
            g.fillRect(snakeX[i], snakeY[i], CUADRO_SIZE, CUADRO_SIZE);
        }
    }

    //Clase privada para controles
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
