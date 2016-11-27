package com.javarush.test.level23.lesson13.big01;

import java.util.ArrayList;

public class Snake {
    private ArrayList<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake(int x, int y) {
        sections = new ArrayList<>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public ArrayList<SnakeSection> getSections() {
        return sections;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }
    public int getX() {
        return sections.get(0).getX();
    }

    public int getY() {
        return sections.get(0).getY();
    }

    public void move() {
        if (!isAlive) return;
        SnakeSection head = sections.get(0);

        if (direction == SnakeDirection.UP) {
            sections.add(0, head = new SnakeSection(sections.get(0).getX(), sections.get(0).getY() - 1));
            sections.remove(sections.size()-1);
        }
        else if (direction == SnakeDirection.DOWN) {
            sections.add(0, head = new SnakeSection(sections.get(0).getX(), sections.get(0).getY() + 1));
            sections.remove(sections.size()-1);
        }
        else if (direction == SnakeDirection.LEFT) {
            sections.add(0, head = new SnakeSection(sections.get(0).getX() - 1, sections.get(0).getY()));
            sections.remove(sections.size()-1);
        }
        else if (direction == SnakeDirection.RIGHT) {
            sections.add(0, head = new SnakeSection(sections.get(0).getX() + 1, sections.get(0).getY()));
            sections.remove(sections.size()-1);
        }
        if (head.getX() < 0 || head.getX() >= Room.game.getWidth() ||  head.getY() < 0 || head.getY() >= Room.game.getHeight()) isAlive = false;
        if (sections.contains(head)) isAlive = false;
        if (head.getX() == Room.game.getMouse().getX() && head.getY() == Room.game.getMouse().getY()) Room.game.eatMouse();
    }
}
