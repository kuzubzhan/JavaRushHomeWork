package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
        students = new ArrayList<>();
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        if (!students.isEmpty()) {
            for (Student student : students) {
                if (student.getAverageGrade() == averageGrade)
                    return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        if (!students.isEmpty()) {
            Student student = students.get(0);
            for (Student student1 : students) {
                if (student1.getAverageGrade() > student.getAverageGrade())
                    student = student1;
            }
            return student;
        }
        return null;
    }

    public Student getStudentWithMinAverageGrade() {
        if (!students.isEmpty()) {
            Student student = students.get(0);
            for (Student student1 : students) {
                if (student1.getAverageGrade() < student.getAverageGrade())
                    student = student1;
            }
            return student;
        }
        return null;
    }

    public void expel(Student student) {
        if (students.contains(student))
            students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
