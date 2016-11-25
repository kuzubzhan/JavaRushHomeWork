package com.javarush.test.level22.lesson09.task02;

import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params == null) return sb;

        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (pair.getValue() == null)
                continue;
            if (sb.length() > 0) sb.append(" and ");
            sb.append(pair.getKey()).append(" = '").append(pair.getValue()).append("'");
        }
        return sb;
    }
}
