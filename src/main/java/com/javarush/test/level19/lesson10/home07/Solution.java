package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fn1 = args[0];
        String fn2 = args[1];
        BufferedReader fr = new BufferedReader(new FileReader(fn1));
        BufferedWriter fw = new BufferedWriter(new FileWriter(fn2));
        String out = "";
        while (fr.ready()) {
            String[] in = fr.readLine().split("\\s");
            for (String s : in) {
                if (s.length() > 6) {
                    out += s + ",";
                }
            }
        }
        fw.write(out.replaceFirst(",$", ""));
        fr.close();
        fw.close();
    }
}
