package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, включая символы новой строки. Результат вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fn1 = br.readLine();
        String fn2 = br.readLine();
        br.close();
        BufferedReader fr = new BufferedReader(new FileReader(fn1));
        BufferedWriter fw = new BufferedWriter(new FileWriter(fn2));
        while (fr.ready()) {
            String in = fr.readLine();
            String out = in.replaceAll("\\p{Punct}", "");
            fw.write(out);
        }
        fr.close();
        fw.close();
    }
}
