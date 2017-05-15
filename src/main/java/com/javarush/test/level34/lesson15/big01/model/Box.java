package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
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
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(centerX, centerY, getWidth(), getHeight());
        graphics.drawLine(centerX, centerY, centerX + getWidth(), centerY + getHeight());
        graphics.drawLine(centerX, centerY + getHeight(), centerX + getWidth(), centerY);
    }
}
