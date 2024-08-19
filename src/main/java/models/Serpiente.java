package models;

import utils.Constants;

public class Serpiente {
    private int[] snakeX;
    private int[] snakeY;

    private int cuerpo_serpiente = 3;
    private static final int TOTAL_CUERPO_SERPIENTE = (Constants.PANTALLA * Constants.PANTALLA) / Constants.CUADRO_SIZE;

    public Serpiente() {
        snakeX = new int[TOTAL_CUERPO_SERPIENTE];
        snakeY = new int[TOTAL_CUERPO_SERPIENTE];
    }

    public int[] getSnakeX() {
        return snakeX;
    }

    public void setSnakeX(int i, int snakeX) {
        this.snakeX[i] = snakeX;
    }

    public void setSnakeY(int i, int snakeY) {
        this.snakeY[i] = snakeY;
    }

    public int[] getSnakeY() {
        return snakeY;
    }

    public void setSnakeY(int[] snakeY) {
        this.snakeY = snakeY;
    }

    public int getCuerpo_serpiente() {
        return cuerpo_serpiente;
    }

    public void setCuerpo_serpiente(int cuerpo_serpiente) {
        this.cuerpo_serpiente = cuerpo_serpiente;
    }

    public void incrementarCuerpo() {
        cuerpo_serpiente++;
    }
}
