package com.javarush.test.level36.lesson08.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> set = new TreeSet<>();
        BufferedReader fr = new BufferedReader(new FileReader(args[0]));
        while (fr.ready()) {
            String str = fr.readLine();
            String[] splitString = str.split("[\\p{Punct}|\\s]");
            for (String s : splitString) {
                for (char c : s.toLowerCase().toCharArray()) {
                    set.add(c);
                }
            }
        }
        fr.close();

        int i = 0;
        for (Character character : set) {
            if (i > 4) break;
            System.out.print(character);
            i++;
        }
    }
}
