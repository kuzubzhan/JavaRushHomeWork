package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new BufferedReader(new InputStreamReader(System.in)).readLine()));
            while (br.ready()) {
                sb.append(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] split = sb.toString().split("<");
        List<String> list = new LinkedList<>();
        Pattern p = Pattern.compile("([^>]*>)([^>]*)");
        Matcher m;
        for (String s : split) {
            if (s.contains(">")) {
                m = p.matcher(s);
                if (m.find()) {
                    list.add(m.group(1));
                    list.add(m.group(2));
                }
            } else list.add(s);
        }

        String[] arr = list.toArray(new String[]{});
        parser(arr, args[0], 0, arr.length);
    }

    private static void parser(String[] strings, String teg, int start, int end) {
        int countOpen = 0;
        int positionOpen = 0;
        int countClose = 0;
        String str = "";
        for (int i = start; i < end; i++) {
            String s = strings[i];
            if (s.startsWith(teg)) {
                countOpen++;
                if (countOpen == 1) positionOpen = i;
            }
            if (s.startsWith("/" + teg)) countClose++;
            if (s.contains(">")) s = "<" + s;
            if (countOpen > 0) {
                str += s;
                if (countOpen == countClose) {
                    if (countOpen >= 1) {
                        System.out.println(str);
                        if (countOpen > 1) parser(strings, teg, positionOpen + 1, i - 1);
                    }
                    str = "";
                    countOpen = countClose = 0;
                }
            }
        }
    }
}
