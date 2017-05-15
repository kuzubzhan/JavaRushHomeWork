package com.javarush.test.level34.lesson15.big01.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int newXPosition = getX();
        int newYPosition = getY();

        if (direction == Direction.LEFT)
            newXPosition = getX() - Model.FIELD_SELL_SIZE;
        if (direction == Direction.RIGHT)
            newXPosition = getX() + Model.FIELD_SELL_SIZE;
        if (direction == Direction.UP)
            newYPosition = getY() - Model.FIELD_SELL_SIZE;
        if (direction == Direction.DOWN)
            newYPosition = getY() + Model.FIELD_SELL_SIZE;

        return newXPosition == gameObject.getX() && newYPosition == gameObject.getY();
    }
}
