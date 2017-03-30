package com.javarush.test.level20.lesson10.bonus03;

import java.util.LinkedList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        for (Word word : detectAllWords(crossword, "emoh", "same", "home", "ver", "rev", "rov")) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        Solution sol = new Solution();
        List<Word> list = new LinkedList<>();
        for (String word : words) {
            for (Word w : sol.searchAllSymbols(crossword, word)) {
                if (w != null) {
                    for (Vector vector : Vector.values()) {
                        Word w1 = sol.getString(crossword, w, vector, word);
                        if (w1 != null) list.add(w1);
                    }
                }
            }
        }
        return list;
    }

    private List<Word> searchAllSymbols(int[][] crossword, String word) {
        List<Word> list = new LinkedList<>();
        if (word != null && !word.isEmpty()) {
            int symbol = word.charAt(0);
            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[0].length; x++) {
                    if (crossword[y][x] == symbol) {
                        Word w = new Word(String.valueOf((char) symbol));
                        w.setStartPoint(x, y);
                        list.add(w);
                    }
                }
            }
        }
        return list;
    }

    private Symbol getNextSymbol(int[][] crossword, int x, int y, Vector vector) {
        try {
            switch (vector) {
                case N:
                    return new Symbol(x, y - 1, crossword[y - 1][x]);
                case NE:
                    return new Symbol(x + 1, y - 1, crossword[y - 1][x + 1]);
                case E:
                    return new Symbol(x + 1, y, crossword[y][x + 1]);
                case SE:
                    return new Symbol(x + 1, y + 1, crossword[y + 1][x + 1]);
                case S:
                    return new Symbol(x, y + 1, crossword[y + 1][x]);
                case SW:
                    return new Symbol(x - 1, y + 1, crossword[y + 1][x - 1]);
                case W:
                    return new Symbol(x - 1, y, crossword[y][x - 1]);
                case NW:
                    return new Symbol(x - 1, y - 1, crossword[y - 1][x - 1]);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private Word getString(int[][] crossword, Word word, Vector vector, String targetStr) {
        String result = word.text;
        int x = word.startX;
        int y = word.startY;
        int endX;
        int endY;
        while (true) {
            Symbol s = getNextSymbol(crossword, x, y, vector);
            if (s == null) return null;
            result += (char) s.symbol;
            x = s.x;
            y = s.y;
            if (targetStr.contains(result)) {
                endX = s.x;
                endY = s.y;
                if (targetStr.equals(result)) {
                    break;
                }
            } else return null;
        }
        Word w = new Word(result);
        w.startX = word.startX;
        w.endX = endX;
        w.startY = word.startY;
        w.endY = endY;
        return w;
    }

    private class Symbol {
        private int x;
        private int y;
        private int symbol;

        private Symbol(int x, int y, int symbol) {
            this.x = x;
            this.y = y;
            this.symbol = symbol;
        }
    }

    private enum Vector {
        N,
        NE,
        E,
        SE,
        S,
        SW,
        W,
        NW
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
