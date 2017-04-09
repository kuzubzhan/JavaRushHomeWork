package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    private static List<File> fileList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        searchFiles(path);

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        fileList.remove(new File(resultFileAbsolutePath));

        Path oldName = Paths.get(resultFileAbsolutePath);
        Path newName = Files.move(oldName, oldName.resolveSibling("allFilesContent.txt"));

        BufferedWriter bw = new BufferedWriter(new FileWriter(newName.toFile()));
        for (File file : fileList) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                bw.write(br.readLine());
                bw.newLine();
            }
            br.close();
        }
        bw.close();
    }

    public static void searchFiles(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) return;
        if (files.length == 0) dir.delete();
        else {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.length() > 50) file.delete();
                    else fileList.add(file);
                } else searchFiles(file.getAbsolutePath());
            }
        }
    }
}
