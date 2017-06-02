package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("E:/Documents/JavaProjects/JavaRushHomeWork/src/main/java/com/javarush/test/level39/lesson09/big01/logs/"));

        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getNumberOfUniqueIPs(new Date(), null));
        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
    }
}
