package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader fr = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> map = new TreeMap<>();
        while (fr.ready()) {
            String[] in = fr.readLine().split("\\s");
            if (map.containsKey(in[0])) {
                double value = map.get(in[0]);
                map.put(in[0], value + Double.parseDouble(in[1]));
            } else
                map.put(in[0], Double.parseDouble(in[1]));
        }
        fr.close();

        double max = 0;
        String person = "";
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue() > max) {
                max = pair.getValue();
                person = pair.getKey() + " ";
            } else if (pair.getValue() == max) person += pair.getKey() + " ";
        }
        System.out.println(person);
    }
}
