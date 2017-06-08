package com.javarush.test.level19.lesson08.task05;

/* Дублируем текст
Считайте с консоли имя файла
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна дублировать вывод всего текста в файл, имя которого вы считали
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Закройте поток файла

Пример вывода на экран:
it's a text for testing

Пример тела файла:
it's a text for testing
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fn1 = br.readLine();
        br.close();
        BufferedWriter fw = new BufferedWriter(new FileWriter(fn1));

        PrintStream a = System.out;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream b = new PrintStream(stream);
        System.setOut(b);
        testString.printSomething();
        System.setOut(a);
        String out = stream.toString();
        System.out.println(out);
        fw.write(out);
        fw.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

