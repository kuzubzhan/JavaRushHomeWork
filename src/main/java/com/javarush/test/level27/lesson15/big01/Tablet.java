package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder() {
        Order order;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }

    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }
}
