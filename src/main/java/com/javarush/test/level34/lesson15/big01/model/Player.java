package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        int centerX = getX() - getWidth() / 2;
        int centerY = getY() - getHeight() / 2;
        graphics.setColor(Color.YELLOW);
        graphics.drawOval(centerX,centerY,getWidth(),getHeight());
        graphics.fillOval(centerX,centerY,getWidth(),getHeight());
    }
}
