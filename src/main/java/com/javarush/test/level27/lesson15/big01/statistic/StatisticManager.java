package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage storage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        storage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Map<Date, Double> getAdvertisementStatistic() {
        Map<Date, Double> map = new TreeMap<>(Collections.<Date>reverseOrder());

        for (EventDataRow row : storage.getValueEventType(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow cast = (VideoSelectedEventDataRow) row;
            Date formatData = null;
            try {
                formatData = SimpleDateFormat.getDateInstance().parse(SimpleDateFormat.getDateInstance().format(cast.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!map.containsKey(formatData)) {
                map.put(formatData, (0.01 * (double) cast.getAmount()));
            } else {
                map.put(formatData, map.get(formatData) + (0.01 * (double) cast.getAmount()));
            }
        }

        return map;
    }

    public Map<Date, Map<String, Integer>> getCookWorkStatistic() {
        Map<Date, Map<String, Integer>> map = new TreeMap<>(Collections.<Date>reverseOrder());

        for (EventDataRow row : storage.getValueEventType(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow cast = (CookedOrderEventDataRow) row;
            int time = cast.getTime();
            if (time == 0)
                continue;
            if (time % 60 == 0)
                time = time / 60;
            else
                time = time / 60 + 1;

            Date formatData = null;
            try {
                formatData = SimpleDateFormat.getDateInstance().parse(SimpleDateFormat.getDateInstance().format(cast.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!map.containsKey(formatData)) {
                Map<String, Integer> cookParam = new TreeMap<>();
                cookParam.put(cast.getCookName(), time);
                map.put(formatData, cookParam);
            } else {
                Map<String, Integer> cookParam = map.get(formatData);
                if (!cookParam.containsKey(cast.getCookName())) {
                    cookParam.put(cast.getCookName(), time);
                } else {
                    cookParam.put(cast.getCookName(), cookParam.get(cast.getCookName()) + time);
                }
            }
        }

        return map;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> map;

        public StatisticStorage() {
            map = new HashMap<>();
            for (EventType eventType : EventType.values()) {
                map.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            map.get(data.getType()).add(data);
        }

        private List<EventDataRow> getValueEventType(EventType eventType) {
            return map.get(eventType);
        }
    }
}
