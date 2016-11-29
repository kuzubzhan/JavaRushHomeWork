package com.javarush.test.level22.lesson18.big01;

public class Figure {
    private int x;
    private int y;
    private int[][] matrix;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void left() {
        x--;

    }

    public void right() {
        x++;

    }

    public void down() {
        y++;

    }

    public void up() {
        y--;

    }

    public void downMaximum() {

    }

    public void rotate() {

    }

    public boolean isCurrentPositionAvailable() {
        return true;
    }

    public void landed() {

    }

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }
}
