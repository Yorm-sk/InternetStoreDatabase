package com.solvd.xml_parsers.jackson;

public class Dimas {
    private int height;
    private int weight;
    private FieldOfDrugs field;

    public Dimas() {
    }

    public Dimas(int height, int weight, FieldOfDrugs field) {
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

    @Override
    public String toString() {
        return "Dimas{" +
                "height=" + height +
                ", weight=" + weight +
                ", field=" + field +
                '}';
    }
}
