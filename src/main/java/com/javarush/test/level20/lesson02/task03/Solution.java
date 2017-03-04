package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws IOException {
        //implement this method - реализуйте этот метод
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fn = br.readLine();
        br.close();
        InputStream inputStream = new FileInputStream(fn);
        Properties p = new Properties();
        p.load(inputStream);
        inputStream.close();

        for (Map.Entry<Object, Object> pr : p.entrySet()) {
            properties.put(String.valueOf(pr.getKey()), String.valueOf(pr.getValue()));
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties p = new Properties();
        p.putAll(properties);
        p.store(outputStream, null);
        outputStream.close();
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties p = new Properties();
        p.load(inputStream);
        inputStream.close();
        for (Map.Entry<Object, Object> pr : p.entrySet()) {
            properties.put(String.valueOf(pr.getKey()), String.valueOf(pr.getValue()));
        }
    }
}
