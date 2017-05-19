package com.javarush.test.level35.lesson10.bonus01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException {
        Set<Animal> result = new HashSet<>();
        File dir = new File(pathToAnimals);

        ClassLoader loader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                Class<?> aClass = null;
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(name));
                    aClass = defineClass(null, bytes, 0, bytes.length);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return aClass;
            }
        };

        for (File file : dir.listFiles()) {
            try {
                Class<?> aClass = loader.loadClass(file.getAbsolutePath());
                Animal o = (Animal) aClass.newInstance();
                result.add(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
