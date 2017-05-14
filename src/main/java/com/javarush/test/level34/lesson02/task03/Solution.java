package com.javarush.test.level34.lesson02.task03;

/* Разложение на множители с помощью рекурсии
Разложить целое число n > 1 на простые множители.
Вывести в консоль через пробел все множители в порядке возрастания.
Написать рекуррентный метод для вычисления простых множителей.
Не создавайте статические переменные и поля класса.
Пример:
132
Вывод на консоль:
2 2 3 11
*/
public class Solution {
    public void recursion(int n) {
        if (n < 2) return;

        int del = 2;
        if (n % del != 0) {
            while (del < n) {
                del++;
                if (n % del == 0) {
                    break;
                }
            }
        }
        System.out.print(del + " ");
        recursion(n / del);
    }
}
