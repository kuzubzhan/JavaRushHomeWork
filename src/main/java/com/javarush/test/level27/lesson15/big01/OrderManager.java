package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> blockingQueue = new LinkedBlockingQueue<>();

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        try {
            blockingQueue.put(order);
        } catch (InterruptedException e) {
        }
    }
}
