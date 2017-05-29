package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Cook cook = new Cook("Amigo");
        Cook cook2 = new Cook("Amigo_2");
        Waitor waitor = new Waitor();

        cook.addObserver(waitor);
        cook2.addObserver(waitor);

        StatisticEventManager.getInstance().register(cook);
        StatisticEventManager.getInstance().register(cook2);

        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Tablet t = new Tablet(i);
            t.addObserver(cook);
            t.addObserver(cook2);
            tabletList.add(t);
        }

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
