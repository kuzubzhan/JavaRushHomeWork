package com.javarush.test.level24.lesson14.big01;

public abstract class BaseObject {
    double x;
    double y;
    double radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public abstract void draw();

    public abstract void move();

    public boolean isIntersec(BaseObject o) {
        double dist = Math.sqrt(Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2));
        return dist < Math.max(radius, o.getRadius());
    }
}
