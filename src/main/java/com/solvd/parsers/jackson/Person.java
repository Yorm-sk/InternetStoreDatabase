package com.solvd.parsers.jackson;

public class Person {
    private String name;
    private int height;
    private int weight;
    private FieldOfDrugs field;

    public Person() {
    }

    public Person(String name, int height, int weight, FieldOfDrugs field) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.field = field;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public FieldOfDrugs getField() {
        return field;
    }

    public void setField(FieldOfDrugs field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", field=" + field +
                '}';
    }
}
