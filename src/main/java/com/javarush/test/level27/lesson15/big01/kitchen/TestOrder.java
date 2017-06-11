package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected List<Dish> initDishes() throws IOException {
        List<Dish> dishList = new ArrayList<>();
        for (Dish dish : Dish.values()) {
            if (Math.random() * 10 > 5) dishList.add(dish);
        }
        return dishList;
    }
}
