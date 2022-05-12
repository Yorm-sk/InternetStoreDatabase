package com.solvd.xml_parsers.sax.classes_for_parsing;

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
