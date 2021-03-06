package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = initDishes();
    }

    @Override
    public String toString() {
        String result = "";
        if (dishes != null && !dishes.isEmpty()) {
            result = "Your order: " + dishes.toString() + " of " + tablet;
        }
        return result;
    }

    public int getTotalCookingTime() {
        int result = 0;
        for (Dish dish : dishes) {
            result += dish.getDuration();
        }
        return result;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected List<Dish> initDishes() throws IOException {
        return dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
