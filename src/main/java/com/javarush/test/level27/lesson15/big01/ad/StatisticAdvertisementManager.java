package com.javarush.test.level27.lesson15.big01.ad;

import java.util.*;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public Map<String, Integer> getActiveVideo() {
        Map<String, Integer> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0)
                map.put(advertisement.getName(), advertisement.getHits());
        }
        return map;
    }

    public Set<String> getArchivedVideo() {
        Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() == 0)
                set.add(advertisement.getName());
        }
        return set;
    }
}
