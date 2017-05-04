package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        boolean result = false;
        String fileName = f.getName();
        if (f.isDirectory() || fileName.toLowerCase().endsWith(".html") || fileName.toLowerCase().endsWith(".htm")) {
            result = true;
        }

        return result;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
