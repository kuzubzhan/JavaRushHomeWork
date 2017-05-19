package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T, S extends Convertable<T>> Map<T, S> convert(List<S> list) {
        Map<T, S> result = new HashMap<>();
        for (S s : list) {
            result.put(s.getKey(), s);
        }
        return result;
    }
}
