package com.javarush.test.level40.lesson10.task01;

/* Работа с датами
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        try {
            Calendar calendar = Calendar.getInstance();
            DateFormat df;
            if (date.matches("^\\d+[.]\\d[.]\\d{4}\\s\\d{2}[:]\\d{2}[:]\\d{2}$")) {
                df = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");
                calendar.setTime(df.parse(date));
                System.out.printf(
                                "День: %d\n" +
                                "День недели: %d\n" +
                                "День месяца: %d\n" +
                                "День года: %d\n" +
                                "Неделя месяца: %d\n" +
                                "Неделя года: %d\n" +
                                "Месяц: %d\n" +
                                "Год: %d\n" +
                                "Эра: %d\n" +
                                "AM или PM: %d\n" +
                                "Часы: %d\n" +
                                "Часы дня: %d\n" +
                                "Минуты: %d\n" +
                                "Секунды: %d",
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.DAY_OF_WEEK),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.DAY_OF_YEAR),
                        calendar.get(Calendar.WEEK_OF_MONTH),
                        calendar.get(Calendar.WEEK_OF_YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.ERA),
                        calendar.get(Calendar.AM_PM),
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND)
                );

            } else if (date.matches("^\\d+[.]\\d[.]\\d{4}$")) {
                df = new SimpleDateFormat("dd.M.yyyy");
                calendar.setTime(df.parse(date));
                System.out.printf(
                                "День: %d\n" +
                                "День недели: %d\n" +
                                "День месяца: %d\n" +
                                "День года: %d\n" +
                                "Неделя месяца: %d\n" +
                                "Неделя года: %d\n" +
                                "Месяц: %d\n" +
                                "Год: %d\n" +
                                "Эра: %d", +
                                calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.DAY_OF_WEEK),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.DAY_OF_YEAR),
                        calendar.get(Calendar.WEEK_OF_MONTH),
                        calendar.get(Calendar.WEEK_OF_YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.ERA)
                );
            } else if (date.matches("^\\d{2}[:]\\d{2}[:]\\d{2}$")) {
                df = new SimpleDateFormat("HH:mm:ss");
                calendar.setTime(df.parse(date));
                System.out.printf(
                                "AM или PM: %d\n" +
                                "Часы: %d\n" +
                                "Часы дня: %d\n" +
                                "Минуты: %d\n" +
                                "Секунды: %d",
                        calendar.get(Calendar.AM_PM),
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND)
                );
            }
            System.out.println();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
