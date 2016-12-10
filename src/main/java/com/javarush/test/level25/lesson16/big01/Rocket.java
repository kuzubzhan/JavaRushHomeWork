package com.javarush.test.level25.lesson16.big01;

public class Rocket extends BaseObject {
    public Rocket(double x, double y) {
        super(x, y, 1);
    }

    public void draw(Canvas canvas)
    {
        canvas.setPoint(x,y,'R');
    }

    public void move()
    {
        y--;
    }
}
