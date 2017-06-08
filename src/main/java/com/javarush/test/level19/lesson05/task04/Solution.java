package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
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
            fw.write(in.replaceAll("\\.", "\\!") + "\n");
        }
        fr.close();
        fw.close();
    }
}
