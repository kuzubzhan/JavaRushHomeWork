package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.OurHashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> longSet = new HashSet<>();
        for (String string : strings) {
            longSet.add(shortener.getId(string));
        }

        return longSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();
        for (Long key : keys) {
            stringSet.add(shortener.getString(key));
        }

        return stringSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Shortener shortener = new Shortener(strategy);
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }

        Date start = new Date();
        Set<Long> longSet = getIds(shortener, stringSet);
        Date finish = new Date();
        Helper.printMessage(finish.getTime() - start.getTime() + "");

        start = new Date();
        Set<String> stringSetFromMethod = getStrings(shortener, longSet);
        finish = new Date();
        Helper.printMessage(finish.getTime() - start.getTime() + "");


        if (stringSet.containsAll(stringSetFromMethod) && stringSetFromMethod.containsAll(stringSet)) {
            Helper.printMessage("Тест пройден.");
        }
        else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000L);
        testStrategy(new OurHashMapStorageStrategy(), 10000L);
    }
}
