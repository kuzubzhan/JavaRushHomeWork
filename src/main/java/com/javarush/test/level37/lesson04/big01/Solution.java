package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

public class Solution {
    public static void main(String[] args) {
        MaleFactory factory = new MaleFactory();
        System.out.println(factory.getPerson(99));
        System.out.println(factory.getPerson(4));
        System.out.println(factory.getPerson(15));
    }
}
