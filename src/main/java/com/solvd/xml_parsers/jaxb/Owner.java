package com.solvd.xml_parsers.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Owner {
    private long id;
    private String name;
    private int amountOfCat;

    public Owner() {
    }

    public Owner(long id, String name, int amountOfCat) {
        this.id = id;
        this.name = name;
        this.amountOfCat = amountOfCat;
    }

    public Owner(String name, int amountOfCat) {
        this.name = name;
        this.amountOfCat = amountOfCat;
    }
    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public int getAmountOfCat() {
        return amountOfCat;
    }

    public void setAmountOfCat(int amountOfCat) {
        this.amountOfCat = amountOfCat;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountOfCat=" + amountOfCat +
                '}';
    }
}
