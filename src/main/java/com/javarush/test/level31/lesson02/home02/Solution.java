package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new LinkedList<>();
        Stack<File> fileStack = new Stack<>();
        fileStack.push(new File(root));
        while (!fileStack.isEmpty()) {
            File[] files = fileStack.pop().listFiles();
            for (File file : files) {
                if (file.isDirectory()) fileStack.push(file);
                else if (file.isFile()) result.add(file.getAbsolutePath());
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        for (String s : getFileTree("src\\main\\resources\\lesson02\\home01")) {
            System.out.println(s);
        }
    }
}
