package com.solvd.xml_parsers.jackson;

public class fieldOfDrugs {
    private int size;

    public fieldOfDrugs() {
    }

    public fieldOfDrugs(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "fieldOfDrugs{" +
                "size=" + size +
                '}';
    }
}
