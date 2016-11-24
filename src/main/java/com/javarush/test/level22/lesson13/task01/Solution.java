package com.javarush.test.level22.lesson13.task01;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution {

    public static String[] getTokens(String query, String delimiter) {
        List<String> result = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);

        while (stringTokenizer.hasMoreTokens()) {
            result.add(stringTokenizer.nextToken());
        }
        return result.toArray(new String[result.size()]);
    }
}
