package com.javarush.test.level22.lesson18.big01;

public class Field {
    private int width;
    private int height;
    private int[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Field(int width, int height, int[][] matrix) {
        this.width = width;
        this.height = height;
        this.matrix = matrix;
    }
}
