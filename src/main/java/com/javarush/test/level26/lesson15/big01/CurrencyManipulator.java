package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            sum += pair.getKey() * pair.getValue();
        }
        return sum;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int amount = expectedAmount;

        Map<Integer, Integer> result = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        Map<Integer, Integer> temp = new HashMap<>(denominations);

        List<Integer> list = new ArrayList<>(temp.keySet());
        Collections.sort(list);
        Collections.reverse(list);

        for (Integer arr : list) {
            int key = arr;
            int value = temp.get(key);
            while (true) {
                if (key > amount || value == 0) {
                    temp.put(key, value);
                    break;
                }
                amount = amount - key;
                value--;
                if (result.containsKey(key)) result.put(key, result.get(key) + 1);
                else result.put(key, 1);
            }
        }

        if (amount > 0) throw new NotEnoughMoneyException();
        else {
            for (Map.Entry<Integer, Integer> pair : result.entrySet()) {
                ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
            }
            denominations.clear();
            denominations.putAll(temp);
            ConsoleHelper.writeMessage("Transaction Ok");
        }

        return result;
    }
}
