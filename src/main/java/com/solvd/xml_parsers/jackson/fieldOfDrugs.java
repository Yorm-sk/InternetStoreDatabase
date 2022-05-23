package com.solvd.xml_parsers.jackson;

import java.util.List;

public class fieldOfDrugs {
    private int size;
    List<String> drugs;

    public fieldOfDrugs() {
    }

    public fieldOfDrugs(int size, List<String> drugs) {
        this.size = size;
        this.drugs = drugs;
    }

    public List<String> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<String> drugs) {
        this.drugs = drugs;
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
                ", drugs=" + drugs +
                '}';
    }
}
