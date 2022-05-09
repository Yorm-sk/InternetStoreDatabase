package com.solvd.xml_parser.classes_for_parsing;

import com.solvd.xml_parser.classes_for_parsing.Person;

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
