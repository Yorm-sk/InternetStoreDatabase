package com.solvd.parsers.sax.classes_for_parsing;

public class Student extends Person {
    public Student() {
    }

    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Student{"+ super.toString() + "}";
    }
}
