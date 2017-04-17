package com.javarush.test.level31.lesson15.big01;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile))){
            String name = source.getFileName().toString();
            zos.putNextEntry(new ZipEntry(name));
            try (InputStream is = Files.newInputStream(source)){
                int count;
                byte[] buff = new byte[1024];
                while ((count = is.read(buff)) != -1) {
                    zos.write(buff, 0, count);
                }
            }
            zos.closeEntry();
        }
    }
}
