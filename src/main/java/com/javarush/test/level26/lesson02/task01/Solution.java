package com.javarush.test.level26.lesson02.task01;

import java.util.*;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        final double median;

        int index = array.length / 2;
        if (array.length % 2 != 0) {
            median = array[index];
        }
        else {
            double median1 = array[index-1];
            double median2 = array[index];
            median = (median1 + median2) / 2;
        }

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result = (int) (Math.abs(median-o1) - Math.abs(median-o2));
                return result == 0 ? o1 - o2 : result;
            }
        });

        return array;
    }
}
