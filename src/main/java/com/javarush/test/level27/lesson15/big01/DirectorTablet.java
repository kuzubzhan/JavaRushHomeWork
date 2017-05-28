package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DirectorTablet {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public void printAdvertisementProfit() {
        StatisticEventManager manager = StatisticEventManager.getInstance();
        double total = 0;
        for (Map.Entry<Date, Double> entry : manager.getAdvertisementStatistic().entrySet()) {
            total += entry.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %.2f", dateFormat.format(entry.getKey()), entry.getValue()));
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total));
    }

    public void printCookWorkloading() {
        StatisticEventManager manager = StatisticEventManager.getInstance();
        for (Map.Entry<Date, Map<String, Integer>> entry : manager.getCookWorkStatistic().entrySet()) {
            ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()));
            for (Map.Entry<String, Integer> cooks : entry.getValue().entrySet()) {
                if (cooks.getValue() > 0)
                    ConsoleHelper.writeMessage(String.format("%s - %d min", cooks.getKey(), cooks.getValue()));
            }
        }
    }

    public void printActiveVideoSet() {
        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        for (Map.Entry<String, Integer> entry : manager.getActiveVideo().entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %d", entry.getKey(), entry.getValue()));
        }
    }

    public void printArchivedVideoSet() {
        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        for (String s : manager.getArchivedVideo()) {
            ConsoleHelper.writeMessage(String.format("%s", s));
        }
    }
}
