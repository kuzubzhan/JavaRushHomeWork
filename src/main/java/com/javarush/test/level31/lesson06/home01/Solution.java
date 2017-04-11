package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        String zipPath = args[1];

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath));
        Map<ZipEntry, byte[]> entryMap = new HashMap<>();
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int count;
            while ((count = zis.read(bytes)) != -1) {
                baos.write(bytes, 0, count);
            }
            entryMap.put(entry, baos.toByteArray());
        }
        zis.close();

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));
        String fileName = new File(filePath).getName();
        ZipEntry newZipEntry = new ZipEntry("new/" + fileName);

        for (Map.Entry<ZipEntry, byte[]> entryEntry : entryMap.entrySet()) {
            if (entryEntry.getKey().getName().equals(fileName)) {
                FileInputStream fis = new FileInputStream(filePath);
                byte[] bytesFromFile = new byte[fis.available()];
                fis.close();
                zos.putNextEntry(newZipEntry);
                zos.write(bytesFromFile);
                continue;
            }
            zos.putNextEntry(entryEntry.getKey());
            zos.write(entryEntry.getValue());
            zos.closeEntry();
        }
        zos.close();
    }
}
