package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fn1 = args[0];
        String fn2 = args[1];
        BufferedReader fr = new BufferedReader(new FileReader(fn1));
        BufferedWriter fw = new BufferedWriter(new FileWriter(fn2));
        Pattern p = Pattern.compile(".*\\d.*");
        Matcher m;
        while (fr.ready()) {
            String[] in = fr.readLine().split("\\s");
            for (String s : in) {
                m = p.matcher(s);
                if (m.find()) fw.write(s + " ");
            }
        }
        fr.close();
        fw.close();
    }
}
