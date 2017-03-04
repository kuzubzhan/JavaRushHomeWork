package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person() {
            super();
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            mother = (Person)in.readObject();
            father = (Person)in.readObject();
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            children = (List) in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Person p = new Person("jean", "ivanov", 20);
//        p.setMother(new Person("olga", "ivanova",35));
//        p.setFather(new Person("alim", "ivanov",35));
//        p.setChildren(Arrays.asList(new Person("eugen", "ivanov", 10), new Person("kristy", "ivanova", 7)));
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data1.tmp"));
//        oos.writeObject(p);
//        oos.close();
//
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data1.tmp"));
//        Person p1 = (Person) ois.readObject();
//        ois.close();
//
//        System.out.println(p1.mother);
//        System.out.println(p1.father);
//        System.out.println(p1.firstName);
//        System.out.println(p1.lastName);
//        System.out.println(p1.age);
//        System.out.println(p1.children);
//        for (Person per : p1.children) {
//            System.out.println(per);
//        }
    }
}
