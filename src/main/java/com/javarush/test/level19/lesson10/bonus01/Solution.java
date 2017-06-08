package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String orig = br.readLine();
        String edit = br.readLine();
        br.close();
        BufferedReader frOrig = new BufferedReader(new FileReader(orig));
        BufferedReader frEdit = new BufferedReader(new FileReader(edit));
        List<String> listOrig = new ArrayList<>();
        List<String> listEdit = new ArrayList<>();
        while (frOrig.ready()) {
            listOrig.add(frOrig.readLine());
        }
        while (frEdit.ready()) {
            listEdit.add(frEdit.readLine());
        }
        frOrig.close();
        frEdit.close();

        String sOrig;
        String sEdit;
        int i = 0;
        int j = 0;
        boolean continueFlag = false;
        for (; i < listOrig.size(); ) {
            if (!(j < listEdit.size())) break;
            sOrig = listOrig.get(i);
            sEdit = listEdit.get(j);
            if (sOrig.equals(sEdit)) {
                if (!continueFlag) {
                    lines.add(new LineItem(Type.SAME, sOrig));
                    i++;
                    j++;
                } else {
                    continueFlag = false;
                    lines.add(new LineItem(Type.REMOVED, listOrig.get(i - 1)));
                    lines.add(new LineItem(Type.SAME, sOrig));
                    i++;
                    j++;
                }
            } else {
                if (continueFlag) {
                    continueFlag = false;
                    lines.add(new LineItem(Type.ADDED, sEdit));
                    i--;
                    j++;
                    continue;
                }
                continueFlag = true;
                i++;
            }
        }

        for (; i < listOrig.size(); i++) {
            sOrig = listOrig.get(i);
            lines.add(new LineItem(Type.REMOVED, sOrig));
        }

        for (; j < listEdit.size(); j++) {
            sEdit = listEdit.get(j);
            lines.add(new LineItem(Type.ADDED, sEdit));
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
