package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки(Условие задачи изменил)
В метод main первым параметром приходит имя файла, тело которого в кодировке UTF-8.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке Windows-1251.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        if (args.length != 0 && !"".equals(args[0]) && !"".equals(args[1])) {
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(args[0]));
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(args[1]));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            Charset utf8 = Charset.forName("UTF-8");
            Charset win1251 = Charset.forName("windows-1251");

            String winString = new String(buffer, utf8);
            buffer = winString.getBytes(win1251);
            fos.write(buffer);

            fis.close();
            fos.close();
        }
    }
}
