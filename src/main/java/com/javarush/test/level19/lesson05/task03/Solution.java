package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fn1 = br.readLine();
        String fn2 = br.readLine();
        br.close();
        Pattern p = Pattern.compile("\\D");
        Matcher m;
        Scanner fr = new Scanner(new FileReader(fn1));
        BufferedWriter fw = new BufferedWriter(new FileWriter(fn2));
        while (fr.hasNext()) {
            String s = fr.next();
            m = p.matcher(s);
            if (!m.find()) {
                fw.write(s + " ");
                System.out.println(s);
            }
        }
        fr.close();
        fw.close();
    }
}
