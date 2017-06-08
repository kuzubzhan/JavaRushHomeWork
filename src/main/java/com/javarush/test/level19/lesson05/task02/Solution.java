package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fn1 = br.readLine();
        br.close();
        BufferedReader fr = new BufferedReader(new FileReader(fn1));
        int count = 0;
        while (fr.ready()) {
            String in = fr.readLine();
            String[] split = in.split("[^\\w]");
            for (String s : split) {
                if ("world".equals(s)) count++;
            }
        }
        System.out.println(count);
        fr.close();
    }
}
