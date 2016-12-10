package com.javarush.test.level25.lesson16.big01;

public class Ufo extends BaseObject {

    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Ufo(double x, double y) {
        super(x, y, 3);
    }

    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'U');
    }

    public void move() {
        double dx = Math.random() * 2 - 1;  //-1..1
        double dy = Math.random() * 2 - 1;  //-1..1
        y += dy;
        x += dx;
        checkBorders(radius, Space.game.getWidth()-radius, radius, Space.game.getHeight()/2 - radius);
        int toFire = (int) (Math.random() * 10);
        if (toFire == 0) fire();
    }

    public void fire() {
        Space.game.getBombs().add(new Bomb(x, y));
    }
}
