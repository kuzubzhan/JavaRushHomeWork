package com.javarush.test.level20.lesson04.task05;

import java.io.*;

/* Как сериализовать что-то свое?
Сделайте так, чтобы сериализация класса Object была возможной
*/
public class Solution {
    public static class Object implements Serializable{
        public String string1;
        public String string2;
    }

    public static int countStrings;

    public static class String implements Serializable{
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }

    public static void main(java.lang.String[] args) throws Exception {
        Object ob = new Object();
        new String();
        ob.string1 = new String();
        new String();
        new String();
        ob.string2 = new String();

        ob.string1.print();
        ob.string2.print();


        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.tmp"));
        oos.writeObject(ob);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.tmp"));
        Object ob1 = (Object) ois.readObject();
        ois.close();

        ob1.string1.print();
        ob1.string2.print();
    }
}
