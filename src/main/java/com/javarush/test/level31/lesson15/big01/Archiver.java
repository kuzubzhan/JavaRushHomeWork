package com.javarush.test.level31.lesson15.big01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter path to zipFile");
        Path path = Paths.get(br.readLine());
        ZipFileManager fileManager = new ZipFileManager(path);

        System.out.println("Enter path to file");
        path = Paths.get(br.readLine());
        fileManager.createZip(path);

        CommandExecutor.execute(Operation.EXIT);
    }
}
