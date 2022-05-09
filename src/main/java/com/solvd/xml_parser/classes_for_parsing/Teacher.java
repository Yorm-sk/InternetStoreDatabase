package com.solvd.xml_parser.classes_for_parsing;

import com.solvd.xml_parser.classes_for_parsing.Person;

public class Teacher extends Person {
    public Teacher() {
    }

    public Teacher(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Teacher{"+ super.toString() + "}";
    }
}
