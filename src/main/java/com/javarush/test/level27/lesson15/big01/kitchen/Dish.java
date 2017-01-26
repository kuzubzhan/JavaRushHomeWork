package com.javarush.test.level27.lesson15.big01.kitchen;

import java.util.Arrays;

public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString() {
        StringBuilder sb = new StringBuilder(Arrays.toString(values()));
        sb.delete(0, 1);
        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }
}
