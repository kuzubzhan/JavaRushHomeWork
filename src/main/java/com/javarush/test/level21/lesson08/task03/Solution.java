package com.javarush.test.level21.lesson08.task03;

/* Запретить клонирование
Разрешите клонировать класс А
Запретите клонировать класс B
Разрешите клонировать класс C
Метод main не участвует в тестировании.
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return new C(this.getI(),this.getJ(),this.getName());
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
//        A a = new A(5,6);
//        A cc = (A)a.clone();

//        B b = new B(5,6,"kuk");
//        B cc = (B) b.clone();

        C c = new C(5,6,"kuk");
        C cc = (C) c.clone();
        System.out.println(cc.getI()+" "+cc.getJ()+" "+cc.getName());
    }
}
