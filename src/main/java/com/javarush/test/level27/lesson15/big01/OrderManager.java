package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                StatisticEventManager manager = StatisticEventManager.getInstance();
                while (true) {
                    try {
                        Thread.sleep(10);
                        if (!queue.isEmpty()) {
                            for (Cook cook : manager.getCooks()) {
                                if (!cook.isBusy()) {
                                    cook.startCookingOrder(queue.take());
                                }
                            }
                        }
                    }catch (InterruptedException e){
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        try {
            queue.put(order);
        } catch (InterruptedException e) {
        }
    }
}
