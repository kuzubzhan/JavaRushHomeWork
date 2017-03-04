package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
//            File your_file_name = File.createTempFile("your_file_name", null);
//            OutputStream outputStream = new FileOutputStream(your_file_name);
//            InputStream inputStream = new FileInputStream(your_file_name);
            OutputStream outputStream = new FileOutputStream("C:\\tmp\\data.txt.part2");
            InputStream inputStream = new FileInputStream("C:\\tmp\\data.txt.part2");

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User u = new User();
            u.setFirstName("ivan");
            u.setLastName("ivanov");
            u.setBirthDate(new Date());
            u.setMale(true);
            u.setCountry(User.Country.RUSSIA);
            javaRush.users.add(u);

            User u1 = new User();
            u1.setFirstName("ivana");
            u1.setLastName("ivanova");
            u1.setBirthDate(new Date());
            u1.setMale(false);
            u1.setCountry(User.Country.UKRAINE);
            javaRush.users.add(u1);

            User u2 = new User();
            u2.setFirstName("sidor");
            u2.setLastName("sidorov");
            u2.setBirthDate(new Date());
            u2.setMale(true);
            u2.setCountry(User.Country.OTHER);
            javaRush.users.add(u2);

            javaRush.save(outputStream);
            outputStream.flush();

            Thread.sleep(10000);
            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));
            outputStream.close();
            inputStream.close();

            for (User us:loadedObject.users) {
                System.out.println(us.getFirstName());
                System.out.println(us.getLastName());
                System.out.println(us.getBirthDate());
                System.out.println(us.isMale());
                System.out.println(us.getCountry());
            }


        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with ForExamples file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            int size = users.size();
            printWriter.println(size);
            if (size > 0) {
                for (User u : users) {
                    printWriter.println(u.getFirstName());
                    printWriter.println(u.getLastName());
                    printWriter.println(u.getBirthDate().getTime());
                    printWriter.println(u.isMale());
                    printWriter.println(u.getCountry().getDisplayedName());
                }
            }
            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            int size = Integer.parseInt(br.readLine());
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    User u = new User();
                    u.setFirstName(br.readLine());
                    u.setLastName(br.readLine());
                    u.setBirthDate(new Date(Long.parseLong(br.readLine())));
                    u.setMale(Boolean.parseBoolean(br.readLine()));
                    String country = br.readLine();
                    if ("Ukraine".equals(country)) u.setCountry(User.Country.UKRAINE);
                    else if ("Russia".equals(country)) u.setCountry(User.Country.RUSSIA);
                    else if ("Other".equals(country)) u.setCountry(User.Country.OTHER);
                    this.users.add(u);
                }
            }
            br.close();
        }
    }
}
