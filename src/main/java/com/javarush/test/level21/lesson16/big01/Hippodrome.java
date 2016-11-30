package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by frenchman on 09.10.2016.
 */
public class Hippodrome {
    private ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse one = new Horse("One", 3, 0);
        Horse two = new Horse("Two", 3, 0);
        Horse three = new Horse("Three", 3, 0);
        game.getHorses().add(one);
        game.getHorses().add(two);
        game.getHorses().add(three);
        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse h : getHorses()) {
            h.move();
        }
    }

    public void print() {
        for (Horse h : getHorses()) {
            h.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        double dist = -1.0;
        Horse winner = null;
        for (Horse h : getHorses()) {
            if (h.getDistance() > dist)
                winner = h;
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is "+getWinner().getName()+"!");
    }
}
