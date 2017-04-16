package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileResultPath = args[0];

        List<String> listZipPath = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            listZipPath.add(args[i]);
        }
        Collections.sort(listZipPath);

        byte[] buff = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (String zipPath : listZipPath) {
            FileInputStream fis = new FileInputStream(zipPath);
            int count;
            while ((count = fis.read(buff)) != -1) {
                baos.write(buff, 0, count);
            }
        }

        FileOutputStream fos = new FileOutputStream(fileResultPath);
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray()));
        baos.close();
        while (zis.getNextEntry() != null) {
            int count;
            while ((count = zis.read(buff)) != -1) {
                fos.write(buff, 0, count);
            }
        }
        zis.close();
        fos.close();
    }
}
