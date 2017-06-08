package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
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
        int count = 0;
        while (fr.ready()) {
            count++;
            int read = fr.read();
            if (count % 2 == 0) {
                fw.write(read);
            }
        }
        fr.close();
        fw.close();
    }
}
