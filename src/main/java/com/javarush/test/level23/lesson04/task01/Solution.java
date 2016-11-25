package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution s1 = new Solution();
        s1.innerClasses = new InnerClass[]{s1.new InnerClass(), s1.new InnerClass()};

        Solution s2 = new Solution();
        s2.innerClasses = new InnerClass[]{s2.new InnerClass(), s2.new InnerClass()};

        return new Solution[] {s1, s2};
    }
}
