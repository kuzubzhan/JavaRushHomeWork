package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return br.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishList = new ArrayList<>();
        String str;
        writeMessage("Choose dishes");
        writeMessage(Dish.allDishesToString());
        while (! "exit".equals((str = readString()))) {
            try {
                dishList.add(Dish.valueOf(str));
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(str + " is not detected");
            }
        }
        return dishList;
    }
}
