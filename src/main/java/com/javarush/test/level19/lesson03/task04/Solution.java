package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner object;

        public PersonScannerAdapter(Scanner object) {
            this.object = object;
        }

        @Override
        public Person read() throws IOException {
            String[] all = object.nextLine().split(" ");
            int y = Integer.parseInt(all[5]);
            int m = Integer.parseInt(all[4]) - 1;
            int d = Integer.parseInt(all[3]);
            GregorianCalendar gc = new GregorianCalendar(y, m, d);
            return new Person(all[1], all[2], all[0], gc.getTime());
        }

        @Override
        public void close() throws IOException {
            object.close();
        }
    }
}
