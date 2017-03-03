package com.javarush.test.level30.lesson02.task01;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        String number;
        if (s.startsWith("0x")) {
            s = s.substring(2, s.length());
            number = Integer.toString(Integer.parseInt(s, 16));
        } else if (s.startsWith("0b")) {
            s = s.substring(2, s.length());
            number = Integer.toString(Integer.parseInt(s, 2));
        } else if (s.startsWith("0")) {
            s = s.substring(1, s.length());
            number = Integer.toString(Integer.parseInt(s, 8));
        } else
            number = s;
        return number;
    }
}
