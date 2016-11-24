package com.javarush.test.level22.lesson09.task03;

import java.io.*;
import java.util.*;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) {
        List<String> wordList;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String fileName = br.readLine();
            br.close();

            Scanner scanner = new Scanner(new FileReader(fileName));
            wordList = new ArrayList<>();
            while (scanner.hasNext()) {
                wordList.add(scanner.next());
            }
            scanner.close();

            StringBuilder result = getLine(wordList.toArray(new String[wordList.size()]));
            System.out.println(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();

        if (words == null || words.length == 0)
            return result;
        if (words[0].equals(""))
            return result.append(words[0]);

        int count = 0;
        for (int i = 0; i < words.length; i++) {
            String firstChar = words[i].substring(words[i].length() - 1);
            String tmp = null;
            for (int j = i+1; j < words.length; j++) {
                if (firstChar.equalsIgnoreCase(words[j].substring(0, 1))) {
                    tmp = words[i+1];
                    if (j > i+1) {
                        words[i+1] = words[j];
                        words[j] = tmp;
                    }
                    break;
                }
            }
            /**
             * Если в файле не много строк, думаю 300 циклов будет достаточно
             */
            if (tmp == null && i < words.length - 1 && count < 300 ) {
                Collections.shuffle(Arrays.asList(words));
                count++;
                i = -1;
            }
        }

        for (String s : words) {
            result.append(s).append(" ");
        }
        return result;
    }
}
