package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> currManMap = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator result;
        if (currManMap.containsKey(currencyCode)) result = currManMap.get(currencyCode);
        else {
            result = new CurrencyManipulator(currencyCode);
            currManMap.put(currencyCode, result);
        }

        return result;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return currManMap.values();
    }
}
