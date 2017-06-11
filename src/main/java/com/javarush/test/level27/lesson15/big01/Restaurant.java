package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Amigo_2");
        cook1.setQueue(queue);
        cook2.setQueue(queue);
        Waitor waitor = new Waitor();

        cook1.addObserver(waitor);
        cook2.addObserver(waitor);

        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Tablet t = new Tablet(i);
            t.setQueue(queue);
            tabletList.add(t);
        }

        Thread thread1 = new Thread(cook1);
        Thread thread2 = new Thread(cook2);
        thread1.start();
        thread2.start();

        Thread thread = new Thread(new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
