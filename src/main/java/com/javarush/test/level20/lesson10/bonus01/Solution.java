package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int[] ints = new int[String.valueOf(N).length()];
        for (int i = 0; i < ints.length; i++)
            ints[i] = (int) Math.pow(10, i + 1);
        int[][] powArr = new int[10][String.valueOf(N).length()];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < powArr[i].length; j++) {
                powArr[i][j] = (int) Math.pow(i, j + 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        String numStr;
        byte[] numArr;
        int numLen;
        int lowNum;
        int zeroCount;
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        for (Integer i = 10; i < N; i++) {
            numStr = i.toString();
            numArr = numStr.getBytes();
            lowNum = numArr[numArr.length - 1] - 48;
            Arrays.sort(numArr);
            numLen = numArr.length;
            long currSumm = 0;
            for (int j = numLen - 1; j >= 0; j--) {
                currSumm += powArr[numArr[j] - 48][numLen - 1];
                if (currSumm > i) break;
            }
            if (currSumm == i) {
                list.add(i);
                if (lowNum == 0) {
                    list.add(i + 1);
                }
                i = i + 10 - lowNum - 1;
            } else if (currSumm > i) {
                if (lowNum == 0) {
                    zeroCount = 0;
                    for (int j = numLen - 1; j >= 0; j--) {
                        if (numStr.charAt(j) != '0') break;
                        zeroCount++;
                    }
                } else {
                    zeroCount = 0;
                }
                if (zeroCount == 0) {
                    i = i + (10 - lowNum) - 1;
                } else {
                    int r = ints[zeroCount];
                    i = ((i / r) + 1) * r - 1;
                }
            } else {
                long dif = i - currSumm;
                int j;
                for (j = 0; j < 10; j++) {
                    if (powArr[j][numLen - 1] > dif) break;
                }
                i = i + (j - 1);
            }
        }
        int[] result = new int[list.size()];
        Integer[] arr = list.toArray(new Integer[list.size()]);
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        long fm1 = Runtime.getRuntime().freeMemory();
        long t1 = (new Date()).getTime();
        System.out.println(Arrays.toString(getNumbers(100_000_000)));
        long fm2 = Runtime.getRuntime().freeMemory();
        long t2 = (new Date()).getTime();
        System.out.println((fm1 - fm2) / 1024 / 1024);
        System.out.println((t2 - t1) / 1000);
    }
}
