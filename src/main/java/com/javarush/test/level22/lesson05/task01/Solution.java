package com.javarush.test.level22.lesson05.task01;


/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        String s = "JavaRush - лучший сервис обучения Java.";
//        String s = "JavaRush -луч ший се";
//        String s = null;
        System.out.println(getPartOfString(s));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        String[] s = string.split(" ");
        if (s.length < 5)  throw new TooShortStringException();
        String rez = "";
        for (int i = 1; i < 5; i++) {
            if (i == 4) {
                rez += s[i];
                break;
            }
            rez += s[i]+" ";
        }
        return rez;
    }

    public static class TooShortStringException extends Exception {
    }
}
