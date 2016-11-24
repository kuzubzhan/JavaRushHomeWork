package com.javarush.test.level22.lesson09.task01;

import java.io.*;
import java.util.*;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();

        Scanner scanner = new Scanner(new FileReader(fileName));
        List<String> wordList = new ArrayList<>();
        while (scanner.hasNext()) {
            wordList.add(scanner.next());
        }
        scanner.close();

        List<String> pairList = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            String revString = new StringBuilder(wordList.get(i)).reverse().toString();
            for (int j = i+1; j < wordList.size(); j++) {
                if (revString.equals(wordList.get(j))) {
                    if (! pairList.contains(revString)) {
                        pairList.add(revString);
                        Pair pair = new Pair();
                        pair.first = new StringBuilder(revString).reverse().toString();
                        pair.second = revString;
                        result.add(pair);
                        System.out.println(pair.first + " " + pair.second);
                    }
                }
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
