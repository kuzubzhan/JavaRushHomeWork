package com.javarush.test.level25.lesson16.big01;

public class SpaceShip extends BaseObject {
    private double dx = 0.0;

    public SpaceShip(int x, int y) {
        super(x, y, 3);
    }

    public void moveLeft() {
        this.dx = -1;
    }

    public void moveRight() {
        this.dx = 1;
    }

    public void move() {
        x += dx;
        if (x + radius > Space.game.getWidth()) x = Space.game.getWidth();
        if (x - radius < 0) x = 0;
    }

    public void draw(Canvas canvas) {

    }

    public void fire() {
        Rocket rocketLeft = new Rocket(x - radius, y);
        Rocket rocketRight = new Rocket(x + radius, y);
        Space.game.getRockets().add(rocketLeft);
        Space.game.getRockets().add(rocketRight);
    }
}
