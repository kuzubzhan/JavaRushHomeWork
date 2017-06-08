package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream a = System.out;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream b = new PrintStream(stream);
        System.setOut(b);
        testString.printSomething();
        System.setOut(a);
        String[] out = stream.toString().split("\\s");
        int rez = 0;
        if ("+".equals(out[1])) {
            rez = Integer.parseInt(out[0]) + Integer.parseInt(out[2]);
        } else if ("-".equals(out[1])) {
            rez = Integer.parseInt(out[0]) - Integer.parseInt(out[2]);
        } else if ("*".equals(out[1])) {
            rez = Integer.parseInt(out[0]) * Integer.parseInt(out[2]);
        }
        System.out.printf("%s %s %s = \n%s", out[0], out[1], out[2], rez);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}
