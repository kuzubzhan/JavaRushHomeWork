package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        int start = 0;
        int end;
        String[] rez = new String[2];
        for (int i = 0; i < 2; i++) {
            end = string.indexOf("\t", start);
            if (end == -1) throw new TooShortStringException();
            rez[i] = string.substring(start, end)+"";
            start = end+1;
        }
        return rez[1];
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("tab0\ttab\ttab1\t"));       //tab
        System.out.println(getPartOfString("\t\t"));                    //
        System.out.println(getPartOfString("123\t123"));                //Exception
        System.out.println(getPartOfString(null));                      //Exception
    }
}
