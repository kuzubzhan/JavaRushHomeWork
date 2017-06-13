package com.javarush.test.level40.lesson10.task02;

/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
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

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
        Solution sol = new Solution();
        DateTimeFormatter df;
        if (date.matches("^\\d+[.]\\d+[.]\\d{4}\\s\\d{2}[:]\\d{2}[:]\\d{2}$")) {
            df = DateTimeFormat.forPattern("dd.M.yyyy HH:mm:ss");
            DateTime dt = df.parseDateTime(date);
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
                    dt.get(DateTimeFieldType.dayOfMonth()),
                    dt.get(DateTimeFieldType.dayOfWeek()) == 7 ? 1 : dt.get(DateTimeFieldType.dayOfWeek()) + 1,
                    dt.get(DateTimeFieldType.dayOfMonth()),
                    dt.get(DateTimeFieldType.dayOfYear()),
                    sol.weekOfMonth(dt),
                    sol.weekOfYear(dt),
                    dt.get(DateTimeFieldType.monthOfYear()) - 1,
                    dt.get(DateTimeFieldType.year()),
                    dt.get(DateTimeFieldType.era()),
                    dt.get(DateTimeFieldType.hourOfDay()) > 12 ? 1 : 0,
                    dt.get(DateTimeFieldType.hourOfDay()) > 12 ? dt.get(DateTimeFieldType.hourOfDay()) - 12 : dt.get(DateTimeFieldType.hourOfDay()),
                    dt.get(DateTimeFieldType.hourOfDay()),
                    dt.get(DateTimeFieldType.minuteOfHour()),
                    dt.get(DateTimeFieldType.secondOfMinute())
            );

        } else if (date.matches("^\\d+[.]\\d+[.]\\d{4}$")) {
            df = DateTimeFormat.forPattern("dd.M.yyyy");
            DateTime dt = df.parseDateTime(date);
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
                    dt.get(DateTimeFieldType.dayOfMonth()),
                    dt.get(DateTimeFieldType.dayOfWeek()) == 7 ? 1 : dt.get(DateTimeFieldType.dayOfWeek()) + 1,
                    dt.get(DateTimeFieldType.dayOfMonth()),
                    dt.get(DateTimeFieldType.dayOfYear()),
                    sol.weekOfMonth(dt),
                    sol.weekOfYear(dt),
                    dt.get(DateTimeFieldType.monthOfYear()) - 1,
                    dt.get(DateTimeFieldType.year()),
                    dt.get(DateTimeFieldType.era())
            );
        } else if (date.matches("^\\d{2}[:]\\d{2}[:]\\d{2}$")) {
            df = DateTimeFormat.forPattern("HH:mm:ss");
            DateTime dt = df.parseDateTime(date);
            System.out.printf(
                            "AM или PM: %d\n" +
                            "Часы: %d\n" +
                            "Часы дня: %d\n" +
                            "Минуты: %d\n" +
                            "Секунды: %d",
                    dt.get(DateTimeFieldType.hourOfDay()) > 12 ? 1 : 0,
                    dt.get(DateTimeFieldType.hourOfDay()) > 12 ? dt.get(DateTimeFieldType.hourOfDay()) - 12 : dt.get(DateTimeFieldType.hourOfDay()),
                    dt.get(DateTimeFieldType.hourOfDay()),
                    dt.get(DateTimeFieldType.minuteOfHour()),
                    dt.get(DateTimeFieldType.secondOfMinute())
            );
        }
        System.out.println();
    }

    private int weekOfMonth(DateTime d) {
        int i = d.getDayOfMonth() - d.getDayOfWeek();
        return (int) Math.ceil(i / 7d) + 1;
    }

    private int weekOfYear(DateTime d) {
        int i = d.getWeekOfWeekyear();
        int var = (d.dayOfYear().withMinimumValue().getWeekyear() == d.getYear()) ? 0 : 1;
        return i + var;
    }
}
